package edu.nwmissouri.geoapp.service;

import java.util.List;
import java.util.Map;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface FeedbackService {

	public Map<String, Object> findFeedback(int assignmentId,int studentId);
	
	public List<TblSubmission> findPhase2Feedback(int assignmentId,int studentId);
}
