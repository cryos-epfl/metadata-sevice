package ch.epfl.osper.oai.impl;


import ch.epfl.osper.metadata.grouping.OsperSet;
import ch.epfl.osper.metadata.grouping.OsperSetRepository;
import ch.epfl.osper.oai.OaiIdentifierBuilder;
import ch.epfl.osper.oai.interfaces.DataAccessException;
import ch.epfl.osper.oai.interfaces.OaiSet;
import ch.epfl.osper.oai.interfaces.RecordAccessService;
import ch.epfl.osper.oai.model.OaiRecordRepository;
import ch.epfl.osper.oai.model.OsperRecord;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kryvych on 25/09/15.
 */
@Named
public class RecordAccessServiceImpl implements RecordAccessService<OsperRecord> {

    private final OaiRecordRepository repository;
    private final MongoTemplate mongoTemplate;
    private final OsperSetRepository osperSetRepository;

    private final OaiIdentifierBuilder identifierBuilder;

    @Inject
    public RecordAccessServiceImpl(OaiRecordRepository repository, MongoTemplate mongoTemplate, OsperSetRepository osperSetRepository, OaiIdentifierBuilder identifierBuilder) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
        this.osperSetRepository = osperSetRepository;
        this.identifierBuilder = identifierBuilder;
    }

    @Override
    public Set<OsperRecord> getRecords(Date from, Date to) throws DataAccessException {
        return getRecords(from, to, null, null);
    }

    //resumptionToken is ignored
    @Override
    public Set<OsperRecord> getRecords(Date from, Date to, String set, String resumptionToken) throws DataAccessException {
        Iterable<OsperRecord> foundRecords;
        if (from == null && to == null) {
            foundRecords = repository.findAll();

        } else if (from != null && to == null) {
            foundRecords = repository.findByDateStampGreaterThan(from);

        } else if (from == null) {
            return Sets.newHashSet(repository.findByDateStampLessThan(to));

        } else {
            foundRecords = repository.findByDateStampBetween(from, to);
        }

        Set<OsperRecord> osperRecords = filterRocordsForSet(set, foundRecords);

        for (OsperRecord osperRecord : osperRecords) {
            addSetsToRecord(osperRecord);
        }
        return osperRecords;

    }

    protected Set<OsperRecord> filterRocordsForSet(String set, Iterable<OsperRecord> foundRecords) {

        if(StringUtils.isNotEmpty(set)) {
            final OsperSet osperSet = osperSetRepository.findBySpec(set);
            if (osperSet == null) {
                return Sets.newHashSet();
            }

            Collection<OsperRecord> setRecords = Collections2.filter(Sets.newHashSet(foundRecords), new Predicate<OsperRecord>() {
                @Override
                public boolean apply(OsperRecord osperRecord) {
                    return osperSet.getSensors().contains(osperRecord.getName());
                }
            });
            return Sets.newHashSet(setRecords);
        } else {
            return Sets.newHashSet(foundRecords);
        }
    }

    protected void addSetsToRecord(OsperRecord record) {
        Iterable<OsperSet> osperSets = osperSetRepository.findBySensors(record.getName());
        if (osperSets == null) {
            return;
        }
        for (OsperSet osperSet : osperSets) {
            record.addSet(buildOaiSet(osperSet));
        }

    }

    @Override
    public OsperRecord getRecord(String identifier) throws DataAccessException {
        OsperRecord osperRecord = repository.findByName(identifierBuilder.extractSensorName(identifier));
        addSetsToRecord(osperRecord);
        return osperRecord;
    }

    @Override
    public boolean isValidResumptionToken(String resumptionToken) {
        return false;
    }

    @Override
    public Date getEarliestDatestamp() {

        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "dateStamp"));

        OsperRecord record = mongoTemplate.findOne(query, OsperRecord.class);
        if (record != null) {
            return record.getDateStamp();
        }
        throw new DataAccessException("No records available!");
    }

    @Override
    public boolean isSetSupported() {
        return true;
    }

    @Override
    public Set<OaiSet> getSets() throws DataAccessException {
        HashSet<OsperSet> osperSets = Sets.newHashSet(osperSetRepository.findAll());

        Collection<OaiSet> oaiSets = Collections2.transform(osperSets, new Function<OsperSet, OaiSet>() {
            @Override
            public OaiSet apply(OsperSet osperSet) {
                return buildOaiSet(osperSet);
            }
        });

        return Sets.newHashSet(oaiSets);

    }

    protected OaiSet buildOaiSet(OsperSet osperSet) {
        return new OaiSet(osperSet.getSpec(), osperSet.getDisplayName(), osperSet.getDescription());
    }
}
