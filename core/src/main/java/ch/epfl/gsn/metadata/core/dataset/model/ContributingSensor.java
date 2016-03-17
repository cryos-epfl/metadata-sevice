package ch.epfl.gsn.metadata.core.dataset.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kryvych on 03/03/16.
 */
public class ContributingSensor {
    private String name;
    private Set<String> parameters = new HashSet<>();
    private String from;
    private String to;

    public ContributingSensor(String name) {
        this.name = name;
    }

    public ContributingSensor(String name, Set<String> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public ContributingSensor(String name, Set<String> parameters, String from, String to) {
        this.name = name;
        this.parameters = parameters;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public Set<String> getParameters() {
        return parameters;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContributingSensor that = (ContributingSensor) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
