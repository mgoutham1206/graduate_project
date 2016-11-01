package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblPoolquestion;
import edu.nwmissouri.geoapp.model.TblPoolquestionoption;

public interface PoolquestionoptionRepository extends JpaRepository<TblPoolquestionoption, Integer> {

	
	@Query("select choice, fractionCorrect from TblPoolquestionoption")
	public List<TblPoolquestionoption> getQuestionChoices( );
}
