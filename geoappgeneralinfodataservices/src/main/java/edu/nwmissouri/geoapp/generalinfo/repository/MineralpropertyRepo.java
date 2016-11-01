package edu.nwmissouri.geoapp.generalinfo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nwmissouri.geoapp.generalinfo.model.mineralproperties;

public interface MineralpropertyRepo extends MongoRepository<mineralproperties, String> {
	
	List<mineralproperties> findAll();
	

}
