package edu.nwmissouri.geoapp.manager;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.nwmissouri.geoapp.controller.form.BASE64DecodedMultipartFile;
import edu.nwmissouri.geoapp.controller.form.PhaseOneForm;
import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.service.ImageSubmissionService;
import edu.nwmissouri.geoapp.service.PhaseEvaluationService;
import edu.nwmissouri.geoapp.service.StudentService;
import edu.nwmissouri.geoapp.web.api.util.PhaseOneUtil;
//import scala.remote;

@Service
public class PhaseOneManager {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PhaseEvaluationService phaseEvaluationService;
	
	@Autowired 
	private ImageSubmissionService imageSubmissionService;
	
	
	public PhaseOneForm getSubmission(Integer assignId, Integer userId, String imagePath) throws IOException
	{
		PhaseOneForm phaseOneForm = new PhaseOneForm();
		TblSubmission tbs=studentService.getSubmission(assignId,userId);
		 phaseOneForm.setAssignID(assignId);
		 phaseOneForm.setUserId(userId);
		if(tbs != null )
		{
			phaseOneForm.setSubmitId(tbs.getSubmissionID());
			phaseOneForm.setDescription(tbs.getDescription());
			phaseOneForm.setImagesPath(imagePath);
			List<MultipartFile> images=new ArrayList<MultipartFile>();
			List<String> comments=new ArrayList<>();
			List<Integer> imageIDs = new ArrayList<>();
			List<String> isAccepteds = new ArrayList<>();
			System.out.println(imagePath+"\\");
			if(tbs.getTblImagesubmissions()!=null ){
				File filetemp= new File(imagePath+"\\temp\\");
				if(filetemp.exists()){
					FileUtils.cleanDirectory(filetemp);
				}
				
				for (TblImagesubmission tblImagesubmission : tbs.getTblImagesubmissions()) {
					phaseOneForm.setLatitude(tblImagesubmission.getLatitude());
					phaseOneForm.setLongitude(tblImagesubmission.getLongitude());
					//MultipartFile image=new BASE64DecodedMultipartFile(tblImagesubmission.getImage());
					//images.add(image);
					File file = new File(imagePath+"\\temp\\"+tblImagesubmission.getImageID());
					System.out.println(imagePath+"\\"+tblImagesubmission.getImageID());
					if(file.exists()){
						file.delete();
					}
					FileUtils.writeByteArrayToFile(file, tblImagesubmission.getImage());
					imageIDs.add(tblImagesubmission.getImageID());
					comments.add(tblImagesubmission.getComments());
					isAccepteds.add(tblImagesubmission.getIsAccepted());
					}
				phaseOneForm.setImageIDs(imageIDs);
				phaseOneForm.setIsAccepted(isAccepteds);
			if(tbs.getTblPhaseevaluations()!=null){
				for (TblPhaseevaluation tblPhaseevaluation : tbs.getTblPhaseevaluations()) {
					if(tblPhaseevaluation.getTblPhasetype().getPhaseOrder()==1){
						phaseOneForm.setComment(tblPhaseevaluation.getComments());
					}
				}
			}
			}
			phaseOneForm.setEvalComments(comments);
			phaseOneForm.setImages(images);
		}
		return phaseOneForm;
	}
	
	public void saveSubmission(PhaseOneForm phaseOneForm)
	{
		
		
        List<MultipartFile> imageFiles = phaseOneForm.getImages();
        if(phaseOneForm.getImages() != null ){
        	TblSubmission tblSubmission = new TblSubmission();
        	if(phaseOneForm.getSubmitId()!=null){
        	phaseEvaluationService.deletePhaseOneEvaluation(phaseOneForm.getSubmitId(), 1);
        	tblSubmission.setSubmissionID(phaseOneForm.getSubmitId());
        	}

	        TblAssignment tblAssignment = new TblAssignment();
	        tblAssignment.setAssignID(phaseOneForm.getAssignID());
	        tblSubmission.setTblAssignment(tblAssignment);
	        
	        TblUser tblUser = new TblUser();
	        tblUser.setUserID(phaseOneForm.getUserId());
	        tblSubmission.setTblUser(tblUser);

	        
	        tblSubmission.setDescription(phaseOneForm.getDescription());
	        tblSubmission.setCreatedDate(new Timestamp(new Date().getTime()));
	        tblSubmission.setUpdatedDate(new Timestamp(new Date().getTime()));
	        
	        
	        List<TblImagesubmission> tblImagesubmissions = new ArrayList<TblImagesubmission>();
	        
        	for (MultipartFile imageFile : imageFiles) {
        		if(imageFile != null){
        		if(!(imageFile.isEmpty())){
        			
        	       
				byte[] image = null;
				try {
					image = imageFile.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
				TblImagesubmission tblis=new TblImagesubmission();
				tblis.setIsBest("");
				tblis.setIsAccepted("");
				tblis.setIsShow("");
				tblis.setImageLocation("");
				tblis.setLatitude(phaseOneForm.getLatitude());
				tblis.setLongitude(phaseOneForm.getLongitude());
				tblis.setTblSubmission(tblSubmission);
				tblis.setImage(image);
				tblis.setCreatedDate(new Timestamp(new Date().getTime()));
				tblis.setUpdatedDate(new Timestamp(new Date().getTime()));
				tblImagesubmissions.add(tblis); 
        		}
			}
        	}
        	
        tblSubmission.setTblImagesubmissions(tblImagesubmissions);
        this.studentService.saveSubmition(tblSubmission);
        System.out.println("Succesfully saved");
        }
	}
	
	public void deleteImage(Integer imageID)
	{
		studentService.deleteImageSubmission(imageID);
	}
}
