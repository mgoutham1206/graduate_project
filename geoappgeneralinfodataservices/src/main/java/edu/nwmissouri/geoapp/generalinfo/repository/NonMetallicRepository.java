package edu.nwmissouri.geoapp.generalinfo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.nwmissouri.geoapp.generalinfo.model.metallic;
//import edu.nwmissouri.geoapp.generalinfo.model.metallic;
import edu.nwmissouri.geoapp.generalinfo.model.nonmetallic;

public interface NonMetallicRepository extends MongoRepository<nonmetallic, String>{
	
	List<nonmetallic> findByMineral(String id);
	
	List<nonmetallic> findByCleavage(String cleavage);
	
	List<nonmetallic> findBySetsAndAngle(int sets, int angle);
	
	List<nonmetallic> findByStreakcolor(String streak);
	
	List<nonmetallic> findByColor(String color);
	
	@Query(value="{'hardnessmin' : {$gte:0.0}, 'hardnessmax' : {$lte:2.5} }")
	List<nonmetallic> findByHardnessRange0();
	
	@Query(value="{'hardnessmin' : {$gte:2.5}, 'hardnessmax' : {$lte:3.2} }")
	List<nonmetallic> findByHardnessRange1();
	
	@Query(value="{'hardnessmin' : {$gte:3.2}, 'hardnessmax' : {$lte:5.0} }")
	List<nonmetallic> findByHardnessRange2();
	
	@Query(value="{'hardnessmin' : {$gte:5.0}, 'hardnessmax' : {$lte:5.5} }")
	List<nonmetallic> findByHardnessRange3();
	
	@Query(value="{'hardnessmin' : {$gte:5.5}, 'hardnessmax' : {$lte:7.2} }")
	List<nonmetallic> findByHardnessRange4();
	
	@Query(value="{'hardnessmin' : {$gte:7.2}, 'hardnessmax' : {$lte:10.0} }")
	List<nonmetallic> findByHardnessRange5();
	
	@Query(value="{'sgmin' : {$gte:0.0}, 'sgmax' : {$lte:2.5} }")
	List<nonmetallic> findBySgRange0();
	
	@Query(value="{'sgmin' : {$gte:2.5}, 'sgmax' : {$lte:3.5} }")
	List<nonmetallic> findBySgRange1();
	
	@Query(value="{'sgmin' : {$gte:3.5}, 'sgmax' : {$lte:4.5} }")
	List<nonmetallic> findBySgRange2();
	
	@Query(value="{'sgmin' : {$gte:4.5}, 'sgmax' : {$lte:7.0} }")
	List<nonmetallic> findBySgRange3();
	
	@Query(value="{'sgmin' : {$gte:7.0}, 'sgmax' : {$lte:10.0} }")
	List<nonmetallic> findBySgRange4();
	
	@Query(value="{'sgmin' : {$gte:10.0}, 'sgmax' : {$lte:20.0} }")
	List<nonmetallic> findBySgRange5();

}
