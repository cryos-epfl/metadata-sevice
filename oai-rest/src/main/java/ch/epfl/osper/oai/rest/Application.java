package ch.epfl.osper.oai.rest;

import ch.epfl.osper.oai.OaiConfigurationImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OaiConfigurationImpl.class, RestConfiguration.class})

public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
