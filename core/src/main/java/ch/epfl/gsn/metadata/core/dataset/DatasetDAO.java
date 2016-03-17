package ch.epfl.gsn.metadata.core.dataset;

import ch.epfl.gsn.metadata.core.dataset.model.OsperDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by kryvych on 04/03/16.
 */
@Named
public class DatasetDAO {

    protected static final Logger logger = LoggerFactory.getLogger(DatasetDAO.class);

    private final Properties configuration;

    @Inject
    public DatasetDAO(Properties configuration) {
        this.configuration = configuration;
    }

    public OsperDataset fetchDataset(String datasetName) {

        String datasetDirLocation = configuration.getProperty("dataset.metadata.dir");
        Path dataset = FileSystems.getDefault().getPath(datasetDirLocation, datasetName);
        if (Files.notExists(dataset)) {
            return null;
        }
        return null;
    }
}
