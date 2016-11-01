package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.NamedQuery;
import edu.nwmissouri.geoapp.model.TblSubmission;

@Repository

public interface SubmissionRepository  extends JpaRepository<TblSubmission, Integer> {
	
//	@Query("Select t from tbl_imagesubmission t where t.IsBest=:isBest")
//	Map<String,Object> findByIsBest(@Param("isBest") String isBest);

	@Query("select s from TblSubmission s WHERE s.submissionID= ?1")
	public List<TblSubmission> findBySubmissionID(int submissionID);
	
	@Query("select s from TblSubmission s WHERE s.tblAssignment.assignID= ?1")
	public List<TblSubmission> findByTblAssignment(int assignmentID);
	
	@Query("SELECT s.submissionID, s.tblUser.userID , p.isAccepted, p.tblPhasetype.phaseID FROM TblSubmission s, TblPhaseevaluation p where s.submissionID = p.tblSubmission.submissionID and s.tblAssignment.assignID=?1")
	public String[] findSubmissionsByAssignmentID(int assignmentID);
	
		
}
