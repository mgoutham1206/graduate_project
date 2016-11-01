package edu.nwmissouri.geoapp.generalinfo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableMongoRepositories("edu.nwmissouri.geoapp.generalinfo.repository")
public class Config extends WebMvcConfigurerAdapter {

}
