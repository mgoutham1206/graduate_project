package edu.nwmissouri.geoapp.web.api;

import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblPhasetype;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.serviceImpl.AsssignmentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.ImageSubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StudentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;
import edu.nwmissouri.geoapp.web.api.util.PhaseOneForm;
import edu.nwmissouri.geoapp.web.api.util.PhaseOneUtil;

@RestController
@RequestMapping("/ds/phaseOne")
public class ImageSubmissionRestController{

		
	@Autowired
	private ImageSubmissionServiceImpl imageSubmissionServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AsssignmentServiceImpl assignmentServiceImpl;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	private static final int PHASE_ID = 1;
	
	@RequestMapping(method=RequestMethod.POST, value="/submit")
	public PhaseOneForm submitPhaseOne(@RequestBody PhaseOneForm phaseOneForm){
		
			if(phaseOneForm != null){
				
			if(phaseOneForm.getStudentId() != 0 && phaseOneForm.getAssignmentId() != 0)
			{
				try {
					TblSubmission tblSubmission = studentServiceImpl.getSubmission(phaseOneForm.getAssignmentId(), phaseOneForm.getStudentId());
					if(tblSubmission == null)
					{
						System.out.println("submit phase one method");
						TblUser user = userServiceImpl.findUserbyuserId(phaseOneForm.getStudentId());
						System.out.println("user details ==="+user.getUserID());
						TblAssignment assignment = assignmentServiceImpl.findAssignmentById(phaseOneForm.getAssignmentId());
						List<TblImagesubmission> listImages = null;
						 tblSubmission = new TblSubmission();
						listImages = PhaseOneUtil.getImagesAsBytes(phaseOneForm,tblSubmission);			
						System.out.println("==after get Images=====");
						
						tblSubmission.setTblUser(user);
						tblSubmission.setTblImagesubmissions(listImages);
						tblSubmission.setDescription("phase one submission");
						tblSubmission.setTblAssignment(assignment);
						tblSubmission.setDescription(null);
						tblSubmission.setRockType(null);
						tblSubmission.setRockName(null);
						System.out.println("before submission phase one===");
						tblSubmission = imageSubmissionServiceImpl.submitPhaseOne(tblSubmission);
						System.out.println("after submission phase one==="+tblSubmission.getSubmissionID());
						phaseOneForm.setSubmissionId(tblSubmission.getSubmissionID());
				}
			}catch (Exception e) {
				
					e.getMessage();
					return phaseOneForm; 
			}
			return phaseOneForm; 
		} 
	}
		return phaseOneForm;
	}
		
	@RequestMapping(value="/retrieveImages",method = RequestMethod.POST)
	public PhaseOneForm retrieveImagesForPhaseOne(@RequestBody PhaseOneForm phaseOneForm){
		
		if(phaseOneForm != null){
			System.out.println("retrieveImagesForPhaseOne submissionid"+phaseOneForm.getSubmissionId());
		if(phaseOneForm.getSubmissionId() != 0){
			try{
				List<TblImagesubmission> imageList = imageSubmissionServiceImpl.fetchImagesforPhaseOne(phaseOneForm.getSubmissionId());
				List<String> imageasStringList = PhaseOneUtil.getImagesAsString(imageList);
				System.out.println("after imageasstring"+imageasStringList.size());
				phaseOneForm.setImageList(imageasStringList);
				phaseOneForm.setLatitude(imageList.get(0).getLatitude());
				phaseOneForm.setLongitude(imageList.get(0).getLongitude());
				phaseOneForm.setDescription(imageList.get(0).getDescription());
			}catch(Exception e){
				System.out.println(e.getMessage());
				return phaseOneForm;
			}
			
			return phaseOneForm;
		 }
		}
		return phaseOneForm;
	}
	
	@RequestMapping(value="/findSubmissionId",method = RequestMethod.POST)
	public PhaseOneForm findSubmissionId(@RequestBody PhaseOneForm phaseOneForm){
		
		if(phaseOneForm != null){
			
			if(phaseOneForm.getStudentId() != 0 && phaseOneForm.getAssignmentId() != 0)
			{
				try{
					TblSubmission tblSubmission = studentServiceImpl.getSubmission(phaseOneForm.getAssignmentId(), phaseOneForm.getStudentId());
					
					if(tblSubmission != null){
						
						phaseOneForm.setSubmissionId(tblSubmission.getSubmissionID());
					}else{
						
						phaseOneForm.setSubmissionId(0);
					}
					
				}catch(Exception e){
					System.out.println(e.getMessage());
					return phaseOneForm;
				}
				
				return phaseOneForm;
			}
		}
		return phaseOneForm;
		
	}
	
	@RequestMapping(value="/resubmit",method = RequestMethod.POST)
	public PhaseOneForm updatePhaseOne(@RequestBody PhaseOneForm phaseOneForm){
		
		List<TblImagesubmission> listImages = null;
		TblPhasetype phaseType = null;
		if(phaseOneForm != null){
		if(phaseOneForm.getStudentId() != 0 && phaseOneForm.getAssignmentId() != 0){
			System.out.println(phaseOneForm.getStudentId()+"--------"+phaseOneForm.getAssignmentId());
			try{
				TblSubmission tblSubmission = studentServiceImpl.getSubmission(phaseOneForm.getAssignmentId(), phaseOneForm.getStudentId());
				System.out.println("submissionID"+tblSubmission.getSubmissionID());
				
				if(tblSubmission != null){
					
					phaseOneForm.setSubmissionId(tblSubmission.getSubmissionID());
					imageSubmissionServiceImpl.deleteImagesforResubmission(tblSubmission.getSubmissionID());
					listImages = PhaseOneUtil.getImagesAsBytes(phaseOneForm,tblSubmission);
					
					imageSubmissionServiceImpl.savenewSubmission(listImages);
					phaseType = imageSubmissionServiceImpl.getPhaseType(PHASE_ID);
					
					if(phaseType != null){
						System.out.println(phaseType.getPhaseName()+"---"+phaseType.getPhaseOrder());
						imageSubmissionServiceImpl.deletePhaseOneEvaluation(tblSubmission.getSubmissionID(),phaseType.getPhaseOrder());
					}
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				return phaseOneForm;
			}
			return phaseOneForm;
			
		}
	}
		
		return phaseOneForm;
	}
	
	@RequestMapping(value="/getstatus",method = RequestMethod.POST)
	public PhaseOneForm checkPhaseOneStatus(@RequestBody PhaseOneForm phaseOneForm){
		
		if(phaseOneForm != null){
		
		if(phaseOneForm.getSubmissionId() != 0)
		{
			System.out.println("In checkPhaseOneStatus"+phaseOneForm.getSubmissionId());
			try{
				List<TblPhaseevaluation> phaseEvaluationList = imageSubmissionServiceImpl.getPhaseOneStatus(phaseOneForm.getSubmissionId());
				System.out.println("evaluation list"+phaseEvaluationList.size());
				if(phaseEvaluationList.isEmpty()){
					System.out.println("in is Empty");
					phaseOneForm.setIsEvaluated("false");
				}else if(!phaseEvaluationList.isEmpty()){
					System.out.println("in not is Empty");
					phaseOneForm.setIsEvaluated("true");
					TblPhaseevaluation phaseevaluation = phaseEvaluationList.get(0);
					if(phaseevaluation.getIsAccepted().equalsIgnoreCase("Y")){
						phaseOneForm.setIsAcceptedPhaseOne("true");
					}else if(phaseevaluation.getIsAccepted().equalsIgnoreCase("N")){
						phaseOneForm.setIsAcceptedPhaseOne("false");
					}
				}
				
			}catch(Exception e){
					System.out.println(e.getMessage());
					return phaseOneForm;
				}
			
			
			return phaseOneForm;
		}
	}
				
		System.out.println(phaseOneForm.getIsAcceptedPhaseOne()+"===="+phaseOneForm.getIsEvaluated());
		return phaseOneForm;
		
	}
	
}
