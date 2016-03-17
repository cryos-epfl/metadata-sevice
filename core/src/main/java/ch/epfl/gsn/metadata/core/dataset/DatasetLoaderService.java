package ch.epfl.gsn.metadata.core.dataset;

import ch.epfl.gsn.metadata.core.dataset.model.ContributingSensor;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;


/**
 * Created by kryvych on 07/03/16.
 */
public class DatasetLoaderService {

    protected static final Logger logger = LoggerFactory.getLogger(DatasetLoaderService.class);

    protected static final String DIF_FILE = "dif.xml";
    protected static final String MEMBERS_FILE = "members.json";
    protected static final String VERSIONS_FILE = "versions.json";
    protected static final String INFO_FILE = "info.json";

    public int load(Path directory) {
        int count = 0;
        try (DirectoryStream<Path> allSets = Files.newDirectoryStream(directory)) {
            for (Path datasetDir : allSets) {
                String datasetName = datasetDir.getFileName().toString().toLowerCase();
                Path difFile = FileSystems.getDefault().getPath(datasetDir.toString(), DIF_FILE);
                if (Files.notExists(difFile)) {
                    logger.warn("DIF file is missing: " + datasetName);
                    continue;
                }
                String dif = readFile(difFile);
                Path membersFile = FileSystems.getDefault().getPath(datasetDir.toString(), MEMBERS_FILE);
                if (Files.exists(membersFile)) {
                    String members = readFile(membersFile);
                    Set<ContributingSensor> contributingSensors = parseContributingSensors(members);

                } else {
                    logger.warn("Members file is missing: " + datasetName);
                    //ToDo: validate there there is a link to data file in DIF
                }




                count++;
            }
        } catch (IOException e) {
            logger.error("Problem reading metadata file ", e);
        }

        return count;
    }

    protected Set<ContributingSensor> parseContributingSensors(String members) throws IOException {
        Gson gson = new Gson();
        ContributingSensor[] contributingSensors = gson.fromJson(members, ContributingSensor[].class);
        return Sets.newHashSet(contributingSensors);
    }

    protected String readFile(Path path)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, Charset.defaultCharset());
    }
}
