package ch.epfl.osper.oai.model;

import ch.epfl.osper.oai.interfaces.OaiSet;
import ch.epfl.osper.oai.interfaces.Record;
import com.google.common.collect.Sets;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * Created by kryvych on 24/09/15.
 */
@Document(collection = "oai_record")
public class OsperRecord implements Record, Serializable{
    @Id
    private BigInteger id;

    private  String oAIIdentifier;
    private String xmlRecord;
    private  String format;

    private Date dateStamp;
    private boolean isDeleted = false;

    private final String name;

    private Set<OaiSet> sets = Sets.newHashSet();

    public OsperRecord(String name, String xmlRecord, String format, Date dateStamp) {
        this.name = name;
        this.xmlRecord = xmlRecord;
        this.format = format;
        this.dateStamp = dateStamp;
    }

    public String getFormat() {
        return format;
    }

    public String getXmlRecord() {
        return xmlRecord;
    }

    public void setXmlRecord(String xmlRecord) {
        this.xmlRecord = xmlRecord;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }

    public void setoAIIdentifier(String oAIIdentifier) {
        this.oAIIdentifier = oAIIdentifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getOAIIdentifier() {
        return oAIIdentifier;
    }

    @Override
    public Date getDateStamp() {
        return dateStamp;
    }

    @Override
    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public Set<OaiSet> getSets() {
        return sets;
    }

    public void addSet(OaiSet set) {
        sets.add(set);
    }
}
