package edu.nwmissouri.geoapp.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblPhasetype;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.serviceImpl.ImageSubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.RockSubmissionServiceImpl;

@RestController
@RequestMapping("/ds/submission/phaseThree")
public class RockSubmissionRestController {
	
	private static final int PHASE_ID = 3;
	
	@Autowired
	private RockSubmissionServiceImpl rockSubmissionServiceImpl;
	
	@Autowired
	private ImageSubmissionServiceImpl imageSubmissionServiceImpl;
	
	@RequestMapping(method=RequestMethod.POST, value="/updateRock/{studentId}/{assignmentId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void insertPhaseThree(@PathVariable("studentId") Integer studentId, 
			@PathVariable("assignmentId") Integer assignmentId, @RequestBody TblSubmission submisson){
		
		TblPhasetype phaseType = null;
		TblPhaseevaluation phaseevaluation = null;
		try{
			TblSubmission sub = rockSubmissionServiceImpl.update(assignmentId, studentId);
			sub.setRockName(submisson.getRockName());
			sub.setRockType(submisson.getRockType());
			
			rockSubmissionServiceImpl.saveRockInfo(sub);
			
			// delete phase3 evaluation if present.
			phaseType = imageSubmissionServiceImpl.getPhaseType(PHASE_ID);
			
			if(phaseType != null){
				phaseevaluation = rockSubmissionServiceImpl.findPhaseThreeEvaluation(sub.getSubmissionID(), phaseType.getPhaseOrder());
				if(phaseevaluation != null){
					rockSubmissionServiceImpl.deletePhaseThreeEvaluation(sub.getSubmissionID(), phaseType.getPhaseOrder());
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/retrieve/{studentId}/{assignmentId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public TblSubmission retrievePhaseThree(@PathVariable("studentId") Integer studentId, 
			@PathVariable("assignmentId") Integer assignmentId){
		
		TblSubmission tblSubmission = null;
		try{
			
			tblSubmission = rockSubmissionServiceImpl.update(assignmentId, studentId);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return tblSubmission;
		}
		
		if(tblSubmission != null){
			if(tblSubmission.getRockName() != null && tblSubmission.getRockType() != null){
				return tblSubmission;
			}
		}
		return tblSubmission;
		
	}
	
	
	
	
}
