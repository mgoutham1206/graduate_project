package edu.nwmissouri.geoapp.serviceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.ImagesubmissionRepository;
import edu.nwmissouri.geoapp.repository.MineralsubmissionRepository;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.repository.SubmissionRepository;
import edu.nwmissouri.geoapp.service.SubmissionService;

	

@Service
public class SubmissionServiceImpl implements SubmissionService {
	
	 @Autowired
	public SubmissionRepository submissionrepo;
	 
	 @Autowired
	ImagesubmissionRepository phase1repo;
	 
	 @Autowired
	 MineralsubmissionRepository phase2repo;
	 
	 @Autowired
	 PhaseevaluationRepository phaseEval;

	@Override
	public List<TblSubmission> findBySubmissionID(int submissionID) {
		// TODO Auto-generated method stub
		return submissionrepo.findBySubmissionID(submissionID);
	}

	@Override
	public List<TblSubmission> findByAssignmentID(int assignmentID) {
		
		return submissionrepo.findByTblAssignment(assignmentID);
	}

	@Override
	public String[] findSubmissionsByAssignmentID(int assignmentID) {
		// TODO Auto-generated method stub
		return submissionrepo.findSubmissionsByAssignmentID(assignmentID);
	}

	@Override
	public List<String> findPhasesBySubmissionID(int submissionID) {
		// TODO Auto-generated method stub
		List<String> phasesinfo=new ArrayList<>(); 
//		List<List<String>> totalPhasesinfo=new ArrayList<>();
		 int i=0;
		String phaseInfo="";
		List<TblImagesubmission> phase1= phase1repo.findImagesBySubmissionId(submissionID);				
		phaseInfo+="1,";
		if(phase1.isEmpty()){
			phaseInfo+="no,";
		}			
		else phaseInfo+="yes,";
		
		List<TblPhaseevaluation> phaseevaluation=phaseEval.findFeedbackBySubmissionId(submissionID);	
	    for(TblPhaseevaluation tblphase:phaseevaluation){
	    	if(tblphase.getId().getPhase()==1){
	    		if(tblphase.getIsAccepted().equals("Y")){
	    			i=1;
	    			phaseInfo+="Y,"+submissionID;
	    		}	    		
	    		else{i=1;
	    			phaseInfo+="N,"+submissionID;}
	    	}
		}
	    	if(i==0)
	    		phaseInfo+="N,"+submissionID;
	    
	    phasesinfo.add(phaseInfo);
		
	    
	    i=0;
	    phaseInfo="";
		List<TblMineralsubmission> phase2= phase2repo.findBySubmissionID(submissionID);		
		phaseInfo+="2,";
		if(phase2.isEmpty()){
			phaseInfo+="no,";
		}			
		else phaseInfo+="yes,";
		
		for(TblPhaseevaluation tblphase:phaseevaluation){
			if(tblphase.getId().getPhase()==2){
	    		if(tblphase.getIsAccepted().equals("Y")){
	    			i=1;
	    			phaseInfo+="Y,"+submissionID;
	    		}	    		
	    		else{i=1;
	    			phaseInfo+="N,"+submissionID;}
	    	}
		}
	    	if(i==0)
	    		phaseInfo+="N,"+submissionID;
	    	phasesinfo.add(phaseInfo);
		
		
		 phaseInfo="";
		List<TblSubmission> submlist=submissionrepo.findBySubmissionID(submissionID);
		phaseInfo+="3,";
		for(TblSubmission sub3:submlist){
			if(sub3.getRockName() ==  null &&sub3.getRockType()==null ||
					
					sub3.getRockName().equals("")&&sub3.getRockType().equals("")	){
				System.out.println("***********rock"+sub3.getRockName());
				phaseInfo+="no,";				
			}
			else {
				phaseInfo+="yes,";
				System.out.println("***********rock"+sub3.getRockName());
			}
		}
		i=0;
		for(TblPhaseevaluation tblphase:phaseevaluation){
	    	if(tblphase.getId().getPhase()==3){
	    		if(tblphase.getIsAccepted().equals("Y")){
	    			i=1;
	    			phaseInfo+="Y,"+submissionID;
	    		}	    		
	    		else{i=1;
	    			phaseInfo+="N,"+submissionID;}
	    	}
		}
	    	if(i==0)
	    		phaseInfo+="N,"+submissionID;
	    
		phasesinfo.add(phaseInfo);
		
		System.out.println(phasesinfo+"*****************************");
		
		
		return phasesinfo;
	}
	
	
	@Override
	public List<String> findStudentsByAssignmentID(int assignmentID) {
		// TODO Auto-generated method stub
		List<TblSubmission> submissions=submissionrepo.findByTblAssignment(assignmentID);
		List<String> stusub=new ArrayList<>();
		for(TblSubmission sub:submissions){
		System.out.println(sub.getTblUser().getUserID());
			stusub.add(sub.getSubmissionID()+","+sub.getTblUser().getUserID()+","+sub.getTblUser().getName());
		}
		
		return stusub;
	}
	

	

//Subba student id code
//	@Override
//	public Map<String, Object> findByStudentID(int studentID) {
//		Map<String,Object> StudentIDMap = new HashMap<String,Object>();
//		List<TblUser> tblusr = userrepo.findAll();
//		List<TblImagesubmission> tblimgsubmissionlist = new ArrayList<TblImagesubmission>();  
//		TblImagesubmission tsbl = null;
//		for(TblUser s:tblusr){			
////			System.out.println(s.getRockType());
//			if((s.getTblStudent().getStudentID())==studentID){
//				
//				tsbl = imagesubmissionServiceImpl.getImageInfoBySubmissionID(s.getSubmissionID());
//				//				System.out.println(tsbl);
//				tblimgsubmissionlist.add(tsbl);
//			}
//
//		}
//		StudentIDMap.put(StudentID, tblimgsubmissionlist);
//
//		return StudentIDMap;
//	}

	

	

}