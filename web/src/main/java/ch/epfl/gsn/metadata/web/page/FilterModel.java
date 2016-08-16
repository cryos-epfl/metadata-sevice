package ch.epfl.gsn.metadata.web.page;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class FilterModel {

    private Map<String, Set<String>> publicSensorsOfGroup = Maps.newHashMap();
    private Map<String, Set<String>> allSensorsOfGroup = Maps.newHashMap();

    private Map<String, Set<String>> publicParametersOfGroup = Maps.newHashMap();
    private Map<String, Set<String>> allParametersOfGroup = Maps.newHashMap();

    private Set<String> groups = Sets.newHashSet();

    private Set<String> allSensorNames = Sets.newHashSet();
    private Set<String> publicSensorNames = Sets.newHashSet();

    private Set<String> allParameters = Sets.newHashSet();
    private Set<String> publicParameters = Sets.newHashSet();


    public void addPublicSensor(String group, String sensor) {
        addToMap(group, sensor, publicSensorsOfGroup);
        addToMap(group, sensor, allSensorsOfGroup);

    }

    public void addSensor(String group, String sensor) {
        addToMap(group, sensor, allSensorsOfGroup);
    }

    public void addPublicParameter(String group, String parameter) {
        addToMap(group, parameter, publicParametersOfGroup);
        addToMap(group, parameter, allParametersOfGroup);

    }

    public void addParameter(String group, String parameter) {
        addToMap(group, parameter, allParametersOfGroup);
    }

    public void addGroup(String group) {
        groups.add(group);
    }

    public Map<String, Set<String>> getPublicSensorsOfGroup() {
        return publicSensorsOfGroup;
    }

    public Map<String, Set<String>> getAllSensorsOfGroup() {
        return allSensorsOfGroup;
    }

    public Map<String, Set<String>> getPublicParametersOfGroup() {
        return publicParametersOfGroup;
    }

    public Map<String, Set<String>> getAllParametersOfGroup() {
        return allParametersOfGroup;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public String getJsonString() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(HashMultimap.class, new MapTypeAdapter());

        Gson gson = gsonBuilder.create();
        return gson.toJson(this);
    }

    public Set<String> getAllSensorNames() {
        return allSensorNames;
    }

    public void addSensorName(String sensorName) {
        allSensorNames.add(sensorName);
    }

    public void addParameterName(String parameterName){
        allParameters.add(parameterName);
    }

    public void addPublicSensorName(String sensorName) {
        publicSensorNames.add(sensorName);
    }

    public void addPublicParameterName(String parameterName){
        publicParameters.add(parameterName);
    }

    protected void addToMap(String group, String parameter, Map<String, Set<String>> map) {
        Set<String> sensors = map.get(group);
        if (sensors == null) {
            sensors = Sets.newHashSet();
            map.put(group, sensors);
        }
        sensors.add(parameter);
    }
}

class MapTypeAdapter extends TypeAdapter<HashMultimap> {
    @Override
    public void write(JsonWriter jsonWriter, HashMultimap o) throws IOException {
        Gson gson = new Gson();
        jsonWriter.beginObject();
        jsonWriter.value(gson.toJson(o.asMap()));
        jsonWriter.endObject();
    }

    @Override
    public HashMultimap read(JsonReader jsonReader) throws IOException {
        return null;
    }
}