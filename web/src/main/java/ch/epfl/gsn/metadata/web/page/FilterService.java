package ch.epfl.gsn.metadata.web.page;

import ch.epfl.gsn.metadata.core.model.ObservedProperty;
import ch.epfl.gsn.metadata.core.model.VirtualSensorMetadata;
import ch.epfl.gsn.metadata.core.repositories.VirtualSensorMetadataRepository;
import ch.epfl.osper.metadata.grouping.OsperSet;
import ch.epfl.osper.metadata.grouping.OsperSetRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by kryvych on 05/08/16.
 */
@Named
public class FilterService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VirtualSensorMetadataRepository sensorMetadataRepository;

    private final OsperSetRepository setRepository;

    @Inject
    public FilterService(VirtualSensorMetadataRepository sensorMetadataRepository, OsperSetRepository setRepository) {
        this.sensorMetadataRepository = sensorMetadataRepository;
        this.setRepository = setRepository;
    }

    public FilterModel buildFilterModel() {

        FilterModel model = new FilterModel();

        Iterable<OsperSet> osperSets = setRepository.findAll();
        for (OsperSet osperSet : osperSets) {
            String groupName = osperSet.getName();
            model.addGroup(groupName);
            for (String sensor : osperSet.getSensors()) {
                String sensorName = sensor.toLowerCase().trim();
                VirtualSensorMetadata sensorMetadata = sensorMetadataRepository.findOneByName(sensorName);
                if (sensorMetadata == null) {
                    logger.info("Sensor " + sensorName + " from group " + groupName + " not found");
                    continue;
                }
                model.addSensor(groupName, sensorMetadata.getName());
                for (ObservedProperty observedProperty : sensorMetadata.getObservedProperties()) {
                    model.addParameter(groupName, observedProperty.getName());
                    if (sensorMetadata.isPublic()) {
                        model.addPublicSensor(groupName, sensorMetadata.getName());
                        model.addPublicParameter(groupName, observedProperty.getName());
                    }
                }
            }
        }

        Iterable<VirtualSensorMetadata> sensorMetadataItems = sensorMetadataRepository.findAll();
        for (VirtualSensorMetadata sensorMetadataItem : sensorMetadataItems) {
            model.addSensorName(sensorMetadataItem.getName());
            for (ObservedProperty observedProperty : sensorMetadataItem.getObservedProperties()) {
                if (StringUtils.isNotEmpty(observedProperty.getName())) {
                    if (sensorMetadataItem.isPublic()) {
                        model.addPublicParameterName(observedProperty.getName());
                        model.addPublicSensorName(sensorMetadataItem.getName());
                    }
                    model.addParameterName(observedProperty.getName());
                }
            }
        }
        return model;
    }
}

