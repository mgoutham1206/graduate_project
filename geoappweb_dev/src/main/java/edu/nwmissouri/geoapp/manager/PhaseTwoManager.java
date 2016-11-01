package edu.nwmissouri.geoapp.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.controller.form.Mineral;
import edu.nwmissouri.geoapp.controller.form.PhaseTwoForm;
import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblAttributetype;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.service.AttributeEvaluationService;
import edu.nwmissouri.geoapp.service.LusterService;
import edu.nwmissouri.geoapp.service.MineralSubmissionService;
import edu.nwmissouri.geoapp.service.PhaseEvaluationService;
import edu.nwmissouri.geoapp.service.StudentService;

@Service
public class PhaseTwoManager {
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private MineralSubmissionService mineralSubmissionService;
	
	@Autowired
	private AttributeEvaluationService attributeEvaluationService;
	
	@Autowired
	private PhaseEvaluationService phaseEvaluationService;
	
	private Random randomGenarator = new Random(100000);
	public PhaseTwoForm getSubmission(Integer assignId, Integer userId)
	{
		PhaseTwoForm phaseTwoForm = new PhaseTwoForm();
		phaseTwoForm.setAssignID(assignId);
		TblSubmission tbs=studentService.getSubmission(assignId,userId);
		if(tbs!=null){
		phaseTwoForm.setSubmitId(tbs.getSubmissionID());
		
		if(tbs.getTblMineralsubmissions()!=null && tbs.getTblMineralsubmissions().size()>0)
		{
			List<Mineral> minerals=new ArrayList<>();
			for (TblMineralsubmission tblMineralsubmission : tbs.getTblMineralsubmissions()) {
				Mineral mineral=new Mineral();
				mineral.setMineralId(tblMineralsubmission.getMineralID());
				mineral.setLuster(tblMineralsubmission.getLuster());
				mineral.setHardness(tblMineralsubmission.getHardness());
				mineral.setGeometry(tblMineralsubmission.getGeometry());
				mineral.setColor(tblMineralsubmission.getColor());
				mineral.setSpecificGravity(tblMineralsubmission.getSpecificGravity());
				mineral.setStreakColor(tblMineralsubmission.getStreakColor());
				mineral.setMinName(tblMineralsubmission.getName());
				mineral.setTempMinId(randomGenarator.nextInt());
				if(tbs.getTblPhaseevaluations()!=null){
					for (TblPhaseevaluation tblPhaseevaluation : tbs.getTblPhaseevaluations()) {
							 if(tblPhaseevaluation.getTblPhasetype().getPhaseID()==2)
							 {
							phaseTwoForm.setComment(tblPhaseevaluation.getComments());
							if(tblMineralsubmission.getTblAttributeevaluations()!=null)
							{
								mineral.setEvaluation(tblMineralsubmission.getTblAttributeevaluations());
							}
							}
						}
					}
				minerals.add(mineral);
				
			}
			phaseTwoForm.setMinerals(minerals);
			}
		}
		return phaseTwoForm;
	}
	
	
	public void saveMinerals(PhaseTwoForm phaseTwoForm)
	{
		List<Mineral> minerals =phaseTwoForm.getMinerals();
		if(minerals!=null)
			{
			List<TblMineralsubmission> tblMineralsubmissions=new ArrayList<TblMineralsubmission>();
			TblSubmission tbs=new TblSubmission();
			tbs.setSubmissionID(phaseTwoForm.getSubmitId());
			phaseEvaluationService.deletePhaseOneEvaluation(phaseTwoForm.getSubmitId(), 2);
			for (Mineral mineral : minerals) {
					if(mineral.getMineralId()!=null){
						attributeEvaluationService.deleteAttributeComments(mineral.getMineralId());
					}
					TblMineralsubmission tblMineralsubmission=new TblMineralsubmission();
					tblMineralsubmission.setTblSubmission(tbs);						
					tblMineralsubmission.setMineralID(mineral.getMineralId());
					tblMineralsubmission.setLuster(mineral.getLuster());
					tblMineralsubmission.setColor(mineral.getColor());
					tblMineralsubmission.setHardness(mineral.getHardness());
					tblMineralsubmission.setGeometry(mineral.getGeometry());
					tblMineralsubmission.setSpecificGravity(mineral.getSpecificGravity());
					tblMineralsubmission.setStreakColor(mineral.getStreakColor());
					tblMineralsubmission.setName(mineral.getMinName());
					tblMineralsubmissions.add(tblMineralsubmission);
					tblMineralsubmission.setCreatedBy("");
					tblMineralsubmission.setDisplayOrder(0);
					tblMineralsubmission.setUpdatedBy("");
				}
			if(phaseTwoForm.getDeleteMineralSet()!=null)
			{
				for(Integer minID:phaseTwoForm.getDeleteMineralSet()){
					mineralSubmissionService.deleteMineral(minID);
					
				}
			}
			this.studentService.saveMineralSubmission(tblMineralsubmissions);
			}
		
	
	}
	
	public void saveMineral(Mineral mineral){
		
		phaseEvaluationService.deletePhaseOneEvaluation(mineral.getSubmitId(), 2);
		TblSubmission tbs=new TblSubmission();
		tbs.setSubmissionID(mineral.getSubmitId());
		TblMineralsubmission tblMineralsubmission=new TblMineralsubmission();
		tblMineralsubmission.setTblSubmission(tbs);
		//if(mineral.getMineralId() != 0){
			 tblMineralsubmission.setMineralID(mineral.getMineralId());
		 //}
		tblMineralsubmission.setLuster(mineral.getLuster());
		tblMineralsubmission.setColor(mineral.getColor());
		tblMineralsubmission.setHardness(mineral.getHardness());
		tblMineralsubmission.setGeometry(mineral.getGeometry());
		tblMineralsubmission.setSpecificGravity(mineral.getSpecificGravity());
		tblMineralsubmission.setStreakColor(mineral.getStreakColor());
		tblMineralsubmission.setName(mineral.getMinName());
		tblMineralsubmission.setCreatedBy("");
		tblMineralsubmission.setDisplayOrder(0);
		tblMineralsubmission.setUpdatedBy("");
		mineralSubmissionService.saveMineralsubmission(tblMineralsubmission);
	}
	
	public Mineral getMineral(PhaseTwoForm phaseTwoForm, Integer tempMinId)
	{
		if(tempMinId!=null){
		for(Mineral mineral:phaseTwoForm.getMinerals()){
			if(tempMinId.equals(mineral.getTempMinId())){
				return mineral;
			}
		}
		}
		/*TblMineralsubmission tblMineralsubmission=mineralSubmissionService.getMineral(mineralId);
		Mineral mineral=new Mineral();
		if(tblMineralsubmission!=null){
		mineral.setSubmitId(tblMineralsubmission.getTblSubmission().getSubmissionID());
		mineral.setMineralId(tblMineralsubmission.getMineralID());
		mineral.setLuster(tblMineralsubmission.getLuster());
		mineral.setHardness(tblMineralsubmission.getHardness());
		mineral.setGeometry(tblMineralsubmission.getGeometry());
		mineral.setColor(tblMineralsubmission.getColor());
		mineral.setSpecificGravity(tblMineralsubmission.getSpecificGravity());
		mineral.setStreakColor(tblMineralsubmission.getStreakColor());
		mineral.setMinName(tblMineralsubmission.getName());
		if(tblMineralsubmission.getTblAttributeevaluations()!=null){
			mineral.setEvaluation(tblMineralsubmission.getTblAttributeevaluations()) ;
		}
		}*/
		Mineral mineral = new Mineral();
		mineral.setTempMinId(randomGenarator.nextInt());
		return mineral;
	}
	
	public void deleteMineral(PhaseTwoForm phaseTwoForm,Integer tempMinId)
	{
		//attributeEvaluationService.deleteAttributeComments();
		for(Mineral mineral:phaseTwoForm.getMinerals())
		{
			
			if( (tempMinId != null && tempMinId.equals(mineral.getTempMinId())))
			{
				phaseTwoForm.getMinerals().remove(mineral);
				if(mineral.getMineralId() != null){
					phaseTwoForm.getDeleteMineralSet().add(mineral.getMineralId());
				}
				break;
			}
		}
		//mineralSubmissionService.deleteMineral(mineralID);
	}
}
