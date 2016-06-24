package ch.epfl.osper.oai;

import ch.epfl.gsn.metadata.mongodb.MongoApplicationConfig;
import ch.epfl.osper.metadata.grouping.GroupingMongoApplicationConfig;
import ch.epfl.osper.oai.impl.*;
import ch.epfl.osper.oai.interfaces.Converter;
import ch.epfl.osper.oai.interfaces.MetadataFormat;
import ch.epfl.osper.oai.interfaces.OaiConfiguration;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Set;

/**
 * Created by kryvych on 18/09/15.
 */
@Configuration
@ComponentScan("ch.epfl.osper.oai")
@EnableMongoRepositories(basePackages = {"ch.epfl.osper.oai.model"})
@Import({MongoApplicationConfig.class, PropertiesConfiguration.class, GroupingMongoApplicationConfig.class})
public class OaiConfigurationImpl implements OaiConfiguration {

    @Autowired
    private DifConverter difConverter;

    @Autowired
    private DifFormat difFormat;

    @Autowired
    private Iso1Format iso1Format;
    @Autowired
    private Iso2Format iso2Format;
    @Autowired
    private Iso1Converter iso1Converter;
    @Autowired
    private Iso2Converter iso2Converter;

    @Autowired
    private DcOaiFormat dcOaiFormat;

    private Set<Converter> converters = Sets.newHashSet();

    private Set<MetadataFormat> formats = Sets.newHashSet();

    public OaiConfigurationImpl() {
        converters.add(difConverter);
        converters.add(iso1Converter);
        converters.add(iso2Converter);

        formats.add(difFormat);
        formats.add(dcOaiFormat);
        formats.add(iso1Format);
        formats.add(iso2Format);

    }

    @Override
    @Bean
    public Set<Converter> converters() {

        return converters;
    }

    @Override
    @Bean
    public Set<MetadataFormat> formats() {
        return formats;
    }
}
