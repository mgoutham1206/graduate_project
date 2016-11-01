package edu.nwmissouri.geoapp.generalinfo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

//import edu.nwmissouri.geoapp.generalinfo.model.mineralproperties;
import edu.nwmissouri.geoapp.generalinfo.model.minerals;

public interface MineralTypesRepository extends MongoRepository<minerals, String>{
	
	List<minerals> findAll();
	
//	minerals findByMineralsIntro();
//	
//	minerals findByIntro();
	
	minerals findByEconomic(String economic);
	
	minerals findByIndustrial(String industrial);

}
