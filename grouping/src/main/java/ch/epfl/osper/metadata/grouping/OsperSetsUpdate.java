package ch.epfl.osper.metadata.grouping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by kryvych on 16/06/16.
 */

public class OsperSetsUpdate {

    protected static final Logger logger = LoggerFactory.getLogger(OsperSetsUpdate.class);

    public static void main(String[] args) throws Exception{
        if (args.length != 1) {
            System.out.println("Arguments: sets_directory_path");
            System.exit(1);
        }
        Path setsDir = Paths.get(args[0]);
        if (Files.notExists(setsDir)) {
            throw new IOException("Directory " + args[0] + " doesn't exist");
        }
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(GroupingMongoApplicationConfig.class);
        ctx.scan("ch.epfl.gsn.metadata");
        OsperSetLoader osperSetLoader = ctx.getBean(OsperSetLoader.class);

        int count = osperSetLoader.write(setsDir);
        logger.info("Updated " + count + " sets");
    }
}
