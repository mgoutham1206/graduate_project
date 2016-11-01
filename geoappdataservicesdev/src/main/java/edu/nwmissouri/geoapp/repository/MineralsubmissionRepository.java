package edu.nwmissouri.geoapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblMineralsubmission;

public interface MineralsubmissionRepository extends JpaRepository<TblMineralsubmission, Integer> {

	@Query("SELECT p FROM TblMineralsubmission p WHERE p.tblSubmission.submissionID = ?1")
	public List<TblMineralsubmission> findBySubmissionID(int subID);
	
	@Query("SELECT p FROM TblMineralsubmission p, TblSubmission s WHERE s.submissionID = ?1 and p.mineralID = ?2")
	public List<TblMineralsubmission> findBySubmissionIDAndMineralID(int subID, int mineralID);
	
	@Query("SELECT p FROM TblMineralsubmission p WHERE  p.mineralID = ?1")
	public TblMineralsubmission findByMineralID(Integer mineralID);	
}



