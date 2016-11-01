package edu.nwmissouri.geoapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface PhaseevaluationRepository  extends JpaRepository<TblPhaseevaluation, Integer> {

	@Query("SELECT p from TblSubmission p where p.tblAssignment.assignID=?1 AND p.tblUser.userID=?2")
	public List<TblSubmission> findByAssignmentId(int assignmentId,int studentId);
	
	@Query("SELECT p from TblPhaseevaluation p where p.tblSubmission.submissionID=?1")
	public List<TblPhaseevaluation> findFeedbackBySubmissionId(int submissionId);
	
	@Query("SELECT p from TblSubmission p where p.tblAssignment.assignID=?1 AND p.tblUser.userID=?2")
	public List<TblSubmission> findPhasetwoFeedback(int assignmentId,int studentId);
	
	
	@Query("SELECT p from TblPhaseevaluation p where p.tblSubmission.submissionID=?1 and p.tblPhasetype.phaseID = ?2")
	public TblPhaseevaluation findFeedbackForPhase2(int submissionId, int Phase);
	
	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query(value ="insert into tbl_phaseevaluation (SubmissionID,Phase,IsAccepted,Comments,Points) values(?1,?2,?3,?4,?5)", nativeQuery = true)
	public void insertPhaseData(int SubmissionID,int phase, String IsAccepted, String Comments, float Points );
	
	
	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query(value ="update  tbl_phaseevaluation  SET IsAccepted = ?3, Comments = ?4, Points = ?5 where SubmissionID=?1 and phase=?2 ", nativeQuery = true)
	public void updatePhaseData(int SubmissionID,int phase, String IsAccepted, String Comments, float Points );
	
	
	@Query("delete  from TblPhaseevaluation i where i.id.submissionID = ?1 and i.id.phase = ?2")
	@Modifying
	@Transactional
	public void deletePhaseOneEvalForSubmission(int submissionId,int phase);
	
	@Query("SELECT p from TblPhaseevaluation p where p.tblSubmission.submissionID=?1")
	@Modifying
	@Transactional
	public List<TblPhaseevaluation> findTotalPoints(int submissionId);
	
}
