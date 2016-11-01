package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblPoolquestion;

public interface PoolquestionRepository extends JpaRepository<TblPoolquestion, Integer> {
	
	@Query("select question from TblPoolquestion i where i.tblPool.poolID = ?1")
		public List<String> getPoolQuestions(int poolID);


}
