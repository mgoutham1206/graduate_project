package edu.nwmissouri.geoapp.generalinfo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nwmissouri.geoapp.generalinfo.model.rocktypes;

public interface RockTypesRepository extends MongoRepository<rocktypes, String>{

	List<rocktypes> findAll();
	
	rocktypes findBySedimentary();
	
	rocktypes findByIgneous();
	
	rocktypes findByMetamorphic();
}
