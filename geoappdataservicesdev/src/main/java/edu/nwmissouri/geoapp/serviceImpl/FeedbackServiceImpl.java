
package edu.nwmissouri.geoapp.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.AttributeevaluationRepository;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.repository.SubmissionRepository;
import edu.nwmissouri.geoapp.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	PhaseevaluationRepository phaseevaluationRepository;
	
	SubmissionRepository submissionRepo;
	AttributeevaluationRepository attributeevaluationRepository;
	
	//Retrieve Phase1 feedback
	@Override
	public Map<String, Object> findFeedback(int assignmentId,int studentId) {
		List<TblSubmission> tablesubmitt=phaseevaluationRepository.findByAssignmentId(assignmentId,studentId);
		
		System.out.println("***************"+tablesubmitt);
		Map<String,Object> map = new HashMap<String,Object>();				
		List<TblPhaseevaluation> tblPhaseevaluation1=phaseevaluationRepository.findFeedbackBySubmissionId(tablesubmitt.get(0).getSubmissionID());
		map.put("assignment",assignmentId);
		map.put("feedback", tblPhaseevaluation1);
		return map;
	}
	
	//Retrieve Phase two feedback
	@Override
	public List<TblSubmission> findPhase2Feedback(int assignmentId,int studentId){
		return phaseevaluationRepository.findPhasetwoFeedback(assignmentId, studentId);
	}
}