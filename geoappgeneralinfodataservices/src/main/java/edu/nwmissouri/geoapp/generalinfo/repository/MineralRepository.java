package edu.nwmissouri.geoapp.generalinfo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.nwmissouri.geoapp.generalinfo.model.*;


public interface MineralRepository extends MongoRepository<metallic, String> {
	
	//@Query("select m from metallic m")
	//List<String> findBycleavage(String cleavage);
	
	List<metallic> findByMineral(String id);
	
	List<metallic> findByCleavage(String cleavage);
	
	
	List<metallic> findBySetsAndAngle(int sets, int angle);
	
	List<metallic> findByStreakcolor(String streak);
	
	List<metallic> findByColor(String color);
	
	@Query(value="{'hardnessmin' : {$gte:0.0}, 'hardnessmax' : {$lte:2.5} }")
	List<metallic> findByHardnessRange0();
	
	@Query(value="{'hardnessmin' : {$gte:2.5}, 'hardnessmax' : {$lte:3.2} }")
	List<metallic> findByHardnessRange1();
	
	@Query(value="{'hardnessmin' : {$gte:3.2}, 'hardnessmax' : {$lte:5.0} }")
	List<metallic> findByHardnessRange2();
	
	@Query(value="{'hardnessmin' : {$gte:5.0}, 'hardnessmax' : {$lte:5.5} }")
	List<metallic> findByHardnessRange3();
	
	@Query(value="{'hardnessmin' : {$gte:5.5}, 'hardnessmax' : {$lte:7.0} }")
	List<metallic> findByHardnessRange4();
	
	@Query(value="{'hardnessmin' : {$gte:7.0}, 'hardnessmax' : {$lte:10.0} }")
	List<metallic> findByHardnessRange5();
	
	@Query(value="{'sgmin' : {$gte:0.0}, 'sgmax' : {$lte:2.5} }")
	List<metallic> findBySgRange0();
	
	@Query(value="{'sgmin' : {$gte:2.5}, 'sgmax' : {$lte:3.5} }")
	List<metallic> findBySgRange1();
	
	@Query(value="{'sgmin' : {$gte:3.5}, 'sgmax' : {$lte:4.5} }")
	List<metallic> findBySgRange2();
	
	@Query(value="{'sgmin' : {$gte:4.5}, 'sgmax' : {$lte:7.0} }")
	List<metallic> findBySgRange3();
	
	@Query(value="{'sgmin' : {$gte:7.0}, 'sgmax' : {$lte:10.0} }")
	List<metallic> findBySgRange4();
	
	@Query(value="{'sgmin' : {$gte:10.0}, 'sgmax' : {$lte:20.0} }")
	List<metallic> findBySgRange5();
}
