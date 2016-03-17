package ch.epfl.gsn.metadata.core.dataset.model;

import ch.epfl.gsn.metadata.core.dataset.model.ContributingSensor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Set;

/**
 * Created by kryvych on 01/03/16.
 */
@Document(collection = "dataset")
public class OsperDataset {

    @Id
    private BigInteger id;

    @Indexed
    private String name;

    private Set<ContributingSensor> sensors;

    private String dif;

    private String info;

    public OsperDataset(String name, Set<ContributingSensor> sensors, String dif) {
        this.name = name;
        this.sensors = sensors;
        this.dif = dif;
    }

    public String getName() {
        return name;
    }

    public Set<ContributingSensor> getSensors() {
        return sensors;
    }

    public String getDif() {
        return dif;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
