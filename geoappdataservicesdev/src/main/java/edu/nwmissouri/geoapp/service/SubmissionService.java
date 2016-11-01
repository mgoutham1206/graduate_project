package edu.nwmissouri.geoapp.service;

import java.util.List;
import javax.persistence.NamedQuery;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface SubmissionService {
	
//	Map<String,Object> findByRockType(String rockType);
	
	public List<TblSubmission> findBySubmissionID(int submissionID);
	
	public List<TblSubmission> findByAssignmentID(int assignmentID);
	
	 
	public String[] findSubmissionsByAssignmentID(int assignmentID);
	
	public List<String> findPhasesBySubmissionID(int submissionID);
	
	public List<String> findStudentsByAssignmentID(int assignmentID);
}