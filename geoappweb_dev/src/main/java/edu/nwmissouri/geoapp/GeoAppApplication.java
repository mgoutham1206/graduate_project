package edu.nwmissouri.geoapp;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeoAppApplication extends SpringBootServletInitializer {

	public static String ROOT = "upload-dir";
	public static void main(String[] args) {
		SpringApplication.run(GeoAppApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			return builder.sources(GeoAppApplication.class);
	}
	@Bean
	    CommandLineRunner init() {
	        return (String[] args) -> {
	            new File(ROOT).mkdir();
	        };
	    }
}
