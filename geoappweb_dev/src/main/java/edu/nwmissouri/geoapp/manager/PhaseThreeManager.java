package edu.nwmissouri.geoapp.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.controller.form.PhaseThreeForm;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblRocktype;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.service.PhaseEvaluationService;
import edu.nwmissouri.geoapp.service.RockTypeService;
import edu.nwmissouri.geoapp.service.StudentService;

@Service
public class PhaseThreeManager {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private RockTypeService rockTypeService;
	
	@Autowired
	private PhaseEvaluationService phaseEvaluationService;

	public void saveRockSubmission(PhaseThreeForm phaseThreeForm)
	{
		TblSubmission tbs=this.studentService.getSubmission(phaseThreeForm.getSubmitId());
		phaseEvaluationService.deletePhaseOneEvaluation(phaseThreeForm.getSubmitId(), 3);
		tbs.setRockName(phaseThreeForm.getRockName());
		tbs.setRockType(phaseThreeForm.getRockType());
		tbs.setCreatedBy("");
		tbs.setUpdatedBy("");
		this.studentService.saveSubmition(tbs);
	}

	public PhaseThreeForm getSubmission(Integer assignId, Integer userId) {
		TblSubmission tblSubmission=this.studentService.getSubmission(assignId,userId);
		PhaseThreeForm phaseThreeForm=new PhaseThreeForm();
		phaseThreeForm.setRockName(tblSubmission.getRockName());
		phaseThreeForm.setRockType(tblSubmission.getRockType());
		phaseThreeForm.setSubmitId(tblSubmission.getSubmissionID());
		if(tblSubmission.getTblPhaseevaluations()!=null){
			for (TblPhaseevaluation tblPhaseevaluation : tblSubmission.getTblPhaseevaluations()) {
				if(tblPhaseevaluation.getTblPhasetype().getPhaseOrder()==3){
					phaseThreeForm.setEvalComments(tblPhaseevaluation.getComments());
					}
				}
			}
		phaseThreeForm.setRocktypes(rockTypeService.findAll());
		phaseThreeForm.setAssignID(assignId);
		phaseThreeForm.setStudentID(userId);
		return phaseThreeForm;
	}
}
