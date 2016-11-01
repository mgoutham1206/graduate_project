package edu.nwmissouri.geoapp.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.nwmissouri.geoapp.GeoAppServicesApplication;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GeoAppServicesApplication.class)
@ComponentScan("edu.nwmissouri.geoapp.*")
public abstract class GeoAppUnitTesting {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
}
