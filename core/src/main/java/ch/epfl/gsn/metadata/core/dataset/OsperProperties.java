package ch.epfl.gsn.metadata.core.dataset;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by kryvych on 04/03/16.
 */
@Configuration
public class OsperProperties {

    @Bean(name = "configuration")
    public PropertiesFactoryBean configuration() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("configuration.properties"));
        return bean;
    }
}
