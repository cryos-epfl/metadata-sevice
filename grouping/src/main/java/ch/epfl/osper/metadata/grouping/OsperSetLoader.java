package ch.epfl.osper.metadata.grouping;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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

/**
 * Created by kryvych on 09/06/16.
 */
@Named
public class OsperSetLoader {
    private OsperSetRepository repository;

    protected static final Logger logger = LoggerFactory.getLogger(OsperSetLoader.class);

    @Inject
    public OsperSetLoader(OsperSetRepository repository) {
        this.repository = repository;
    }

    public int write(Path directory) {
        int count = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            repository.deleteAll();
            for (Path file : stream) {

                if (writeSet(file)) {
                    count++;
                }

            }
        } catch (IOException e) {
            logger.error("Problem reading metadata file ", e);
        }

        return count;
    }

    protected boolean writeSet(Path file) throws IOException {
        String filename = file.getFileName().toString().toLowerCase();
        if (!"json".equals(FilenameUtils.getExtension(filename))) {
            logger.info("file " + filename + " will not be loaded, since it's not json");
            return false;
        }
        String fileName = FilenameUtils.removeExtension(filename);

        String content = readFile(file, Charset.defaultCharset());

        try {
            OsperSet osperSet = parseOsperSet(content);
            osperSet.setName(fileName);

            repository.save(osperSet);
            return true;

        } catch (JsonSyntaxException e) {
            logger.error("cannot parse " + fileName, e);
            return false;
        }
    }

    protected OsperSet parseOsperSet(String content) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(content, OsperSet.class);

    }

    protected String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }
}