package ch.epfl.osper.oai.load;

import ch.epfl.osper.oai.OaiIdentifierBuilder;
import ch.epfl.osper.oai.model.OaiRecordRepository;
import ch.epfl.osper.oai.model.OsperRecord;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by kryvych on 25/09/15.
 */
@Named
public class RecordLoadService {

    private OaiRecordRepository repository;

    private final OaiIdentifierBuilder identifierBuilder;

    protected static final Logger logger = LoggerFactory.getLogger(RecordLoadService.class);

    @Inject
    public RecordLoadService(OaiRecordRepository repository, OaiIdentifierBuilder identifierBuilder) {
        this.repository = repository;
        this.identifierBuilder = identifierBuilder;
    }

    public int write(Path directory) {
        int count = 0;
        Set<String> recordNames = Sets.newHashSet();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file : stream) {
                String fileNameWithExtention = file.getFileName().toString().toLowerCase();
                if (!"xml".equals(FilenameUtils.getExtension(fileNameWithExtention))) {
                    logger.info("File name " + fileNameWithExtention + " is not xml!");
                    continue;
                }

                String fileName = FilenameUtils.removeExtension(fileNameWithExtention);

                List<String> nameAndType = Splitter.on("-").splitToList(fileName);
                if(nameAndType.size() != 2) {
                    logger.info("File name " + fileName + " is not correctly formatted!");
                    continue;
                }

                String name = nameAndType.get(0).toLowerCase();
                String type = nameAndType.get(1).toLowerCase();

                recordNames.add(name);

                String content = readFile(file, Charset.defaultCharset());

                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                Date creationDate =
                        new Date(attr.creationTime().to(TimeUnit.MILLISECONDS));


                OsperRecord osperRecord = repository.findByName(name);
                if(osperRecord != null) {
                    if(osperRecord.getDateStamp().before(creationDate)){
                        osperRecord.setXmlRecord(content);
                        osperRecord.setDateStamp(creationDate);
                        logger.info("Updated record " + name);
                    }
                    logger.info("Existing record " + name);
                } else {
                    osperRecord = new OsperRecord(name, content, type, creationDate);
                    logger.info("New record " + name);
                }

                osperRecord.setoAIIdentifier(identifierBuilder.buildId(name));
                repository.save(osperRecord);

                count++;
            }
        } catch (IOException e) {
            logger.error("Problem reading metadata file ", e);
        }

        updateDeleted(recordNames);
        return count;
    }

    protected void updateDeleted(Set<String> presentRecords) {
        int count = 0;
        Iterable<OsperRecord> records = repository.findAll();
        for (OsperRecord record : records) {
            if(!presentRecords.contains(record.getName())) {
                record.setIsDeleted(true);
                record.setDateStamp(new Date());
                repository.save(record);
                count ++;
            }
        }
        logger.info("Chenged status to 'deleted' for " + count + " records");
    }

    protected String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }
}
