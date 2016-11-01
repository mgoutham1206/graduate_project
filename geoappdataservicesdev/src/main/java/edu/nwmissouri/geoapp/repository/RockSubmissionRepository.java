package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface RockSubmissionRepository  extends JpaRepository<TblSubmission, Integer> {
	
	@Query("select s from TblSubmission s where s.tblAssignment.assignID = ?1 and s.tblUser.userID = ?2")
	TblSubmission findByAssignmentAndStudentId(int assignId, int userId);
	
	
}
