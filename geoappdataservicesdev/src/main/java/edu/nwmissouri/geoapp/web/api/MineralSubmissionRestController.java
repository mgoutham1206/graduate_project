package edu.nwmissouri.geoapp.web.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblAttributeevaluationPK;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblPhaseevaluationPK;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.AttributeevaluationRepository;
import edu.nwmissouri.geoapp.serviceImpl.AttributeEvaluationServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.MineralSubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.PhaseEvaluationServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.RockSubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.SubmissionServiceImpl;
import edu.nwmissouri.geoapp.web.api.util.PhaseTwoFeedbackForm;
import edu.nwmissouri.geoapp.web.api.util.PhaseTwoStatus;

@RestController
@RequestMapping("/ds/submission/phaseTwo")
public class MineralSubmissionRestController {
	
	@Autowired
	MineralSubmissionServiceImpl mineralService;	
	
	@Autowired
	private RockSubmissionServiceImpl rockSubmissionServiceImpl;
	
	@Autowired
	private AttributeEvaluationServiceImpl attributeevaluationService;
	
	@Autowired
	AttributeevaluationRepository attributeEvaluationRepository1;
	
	@Autowired
	PhaseEvaluationServiceImpl phaseEvaluationServiceImpl;
	
	@Autowired
	SubmissionServiceImpl submissionImpl;
	
	//Get All Minerals By Submission ID
	@RequestMapping(method=RequestMethod.GET, value="/getAllMinerals/{submissionId}" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<TblMineralsubmission> getAllMinerals(@PathVariable int submissionId){		
		return mineralService.findBySubmissionID(submissionId);		
	}
	
	
	//Get Submission ID by StudentID and AssignmentID
	
	@RequestMapping(method=RequestMethod.GET, value="/getSubmissionID/{studentId}/{assignmentId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public TblSubmission insertPhaseThree(@PathVariable("studentId") Integer studentId, @PathVariable("assignmentId") Integer assignmentId){
		return rockSubmissionServiceImpl.update(assignmentId, studentId);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getPhase2Status/{submissionId}" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PhaseTwoStatus getPhaseTwoStatus(@PathVariable int submissionId){	
		
		PhaseTwoStatus p = new PhaseTwoStatus();
		p.setStatus("StartSubmitting");	
		
		List<TblMineralsubmission> m = mineralService.findBySubmissionID(submissionId);
		if(m.isEmpty())
		{
			p.setMineralExists(false);
			p.setStatus("StartSubmitting");		
			p.setPhase3Status("CompletePhase2");
		}
		else
		{
			p.setMineralExists(true);
			p.setMinerals(m);
			TblPhaseevaluation phase2 = phaseEvaluationServiceImpl.phaseevaluationRepository.findFeedbackForPhase2(submissionId,2);
			System.out.println(phase2);
			if(phase2 == null){
				p.setPhaseEvaluationExists(false);
				p.setStatus("WaitingForFeedback");	
				p.setPhase3Status("CompletePhase2");
			}
			else
			{
				p.setPhaseEvaluationExists(true);
				
				    System.out.println(phase2.getIsAccepted() + "  " + phase2.getTblPhasetype().getPhaseID() );
					if(phase2.getTblPhasetype().getPhaseID() == 2)
					{
						p.setIsAccepted(phase2.getIsAccepted());
						if(phase2.getIsAccepted().equalsIgnoreCase("n")){
							p.setStatus("Resubmit");	
							p.setPhase3Status("CompletePhase2");
						}
						else
						{
							p.setStatus("Completed");	
							p.setPhase3Status("StartSubmitting");
							
							//Phase3 Status
							List<TblSubmission> data = submissionImpl.submissionrepo.findBySubmissionID(submissionId);
							if(data.get(0).getRockName() == null)
							{
								p.setPhase3Status("StartSubmitting");
							}
							else
							{
								TblPhaseevaluation phase3 = phaseEvaluationServiceImpl.phaseevaluationRepository.findFeedbackForPhase2(submissionId,3);
								System.out.println(phase3);
								if(phase3 == null)
								{
									p.setPhase3Status("WaitingForFeedback");
								}
								else
								{
									if(phase3.getIsAccepted().equalsIgnoreCase("n")){
										p.setPhase3Status("Resubmit");										
									}
									else
									{
										p.setPhase3Status("Completed");	
									}
								}
							
							
							}
							//Phase3 Status
						}					
					}

		}
		}
		 return p;
	}
	
	
	
	//@RequestMapping( value="/submit/{}",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method=RequestMethod.POST, value="/submit/{submissionID}", produces=MediaType.APPLICATION_JSON_VALUE)
	public void saveMinerals(@PathVariable("submissionID") Integer subID, @RequestBody List<TblMineralsubmission> Listmineral){
	try {
	
			TblSubmission sub = new TblSubmission();
			sub.setSubmissionID(subID);		
			for(TblMineralsubmission m : Listmineral)
			{
				TblMineralsubmission mineral = new TblMineralsubmission();
				mineral.setTblSubmission(sub);
				mineral.setColor(m.getColor());
				mineral.setGeometry(m.getGeometry());
				mineral.setHardness(m.getHardness());
				mineral.setLuster(m.getLuster());
				mineral.setName(m.getName());
				mineral.setSpecificGravity(m.getSpecificGravity());
				mineral.setStreakColor(m.getStreakColor());		
				mineral.setDisplayOrder(m.getDisplayOrder());
				mineralService.saveMineralsubmission(mineral);
				
			}
			
	} catch (Exception e) {
						
			
}
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/update/{submissionID}", produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateMinerals(@PathVariable("submissionID") Integer subID, @RequestBody List<TblMineralsubmission> Listmineral)
	{
	try {
		 
		   //deleting all minerals  and from evaluation
	  	  List<TblMineralsubmission> minerals = getAllMinerals(subID);
           for(TblMineralsubmission m : minerals)
			{
				TblMineralsubmission mineral = new TblMineralsubmission();
				mineral.setMineralID(m.getMineralID());
				mineralService.deleteMineral(m.getMineralID());
				TblPhaseevaluationPK pk = new TblPhaseevaluationPK();
				pk.setSubmissionID(subID);
				pk.setPhase(2);
				phaseEvaluationServiceImpl.phaseevaluationRepository.deletePhaseOneEvalForSubmission(subID, 2);
				
			}
			
			///Saving All minerals	
			TblSubmission sub = new TblSubmission();
			sub.setSubmissionID(subID);		
			for(TblMineralsubmission m : Listmineral)
			{
				TblMineralsubmission mineral = new TblMineralsubmission();
				mineral.setTblSubmission(sub);
				mineral.setColor(m.getColor());
				mineral.setGeometry(m.getGeometry());
				mineral.setHardness(m.getHardness());
				mineral.setLuster(m.getLuster());
				mineral.setName(m.getName());
				mineral.setSpecificGravity(m.getSpecificGravity());
				mineral.setStreakColor(m.getStreakColor());		
				mineral.setDisplayOrder(m.getDisplayOrder());
				mineralService.saveMineralsubmission(mineral);
				
			}			
			
		} 	catch (Exception e) { }
	
	}
	


}
