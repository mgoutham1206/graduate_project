package edu.nwmissouri.geoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("edu.nwmissouri.geoapp.*")
public class GeoAppServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoAppServicesApplication.class, args);
	}
}
