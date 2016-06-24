package ch.epfl.osper.metadata.grouping;


import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Set;

/**
 * Created by kryvych on 09/06/16.
 */
@Document(collection = "dataset")
public class OsperSet {

    @Id
    private BigInteger id;

    private String name;

    @SerializedName("setName")
    private String displayName;

    @SerializedName("setSpec")
    private String spec;

    private String description;

    private Set<String> sensors;

    public OsperSet(String name, String spec, String displayName, String description, Set<String> sensors) {
        this.spec = spec;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.sensors = sensors;
    }


    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSpec() {
        return spec;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getSensors() {
        return sensors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OsperSet osperSet = (OsperSet) o;

        if (!name.equals(osperSet.name)) return false;
        if (displayName != null ? !displayName.equals(osperSet.displayName) : osperSet.displayName != null)
            return false;
        if (spec != null ? !spec.equals(osperSet.spec) : osperSet.spec != null) return false;
        if (description != null ? !description.equals(osperSet.description) : osperSet.description != null)
            return false;
        return !(sensors != null ? !sensors.equals(osperSet.sensors) : osperSet.sensors != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (spec != null ? spec.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sensors != null ? sensors.hashCode() : 0);
        return result;
    }


}
