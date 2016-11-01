package edu.nwmissouri.geoapp.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.serviceImpl.FeedbackServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.MineralSubmissionServiceImpl;
import edu.nwmissouri.geoapp.web.api.util.PhaseTwoFeedbackForm;

@RestController
@RequestMapping("/rest/student")
public class StudentFeedbackRestController {
	@Autowired
	private FeedbackServiceImpl feedbackServiceImpl;
	@Autowired
	private MineralSubmissionServiceImpl mssi;

	@RequestMapping(value = "/getPhaseevaluationByAssignmentId/{assignId}/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> getPhaseevaluation(@PathVariable String assignId,@PathVariable String studentId) {
		
		System.out.println("************" + assignId);
		int assignmentId=Integer.parseInt(assignId);
		int studId=Integer.parseInt(studentId);
		return feedbackServiceImpl.findFeedback(assignmentId,studId);
	}
	
	@RequestMapping(value = "/getPhaseTwoFeedback/{assignId}/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PhaseTwoFeedbackForm> getPhaseTwoFeedback(@PathVariable String assignId,@PathVariable String studentId) {
		List<TblMineralsubmission> tm=new ArrayList<>();
		List<TblAttributeevaluation> at=new ArrayList<>();
		List<PhaseTwoFeedbackForm> feedbackTwo=new ArrayList<>();
		int assignmentId=Integer.parseInt(assignId);
		int studId=Integer.parseInt(studentId);
		List<TblSubmission> submission=feedbackServiceImpl.findPhase2Feedback(assignmentId, studId);
		for(TblSubmission s:submission){	
		tm = s.getTblMineralsubmissions();
		}
		for(TblMineralsubmission m:tm){
			at.addAll(m.getTblAttributeevaluations());
		}
		 
		for(TblAttributeevaluation obj:at){
			int mId=obj.getTblMineralsubmission().getMineralID();
			String mName=mssi.findByMineralID(mId).getName();
			String aName=obj.getTblAttributetype().getAttributeName();
			
			feedbackTwo.add(new PhaseTwoFeedbackForm(mId, mName,aName,obj.getIsAccepted(),obj.getComments()));
		}
		
		return feedbackTwo;
	}
}
