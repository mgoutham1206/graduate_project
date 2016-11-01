package edu.nwmissouri.geoapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.RockSubmissionRepository;
import edu.nwmissouri.geoapp.service.InstructorEvaluationPhase3Service;

@Service

public class InstructorEvaluationPhase3ServiceImpl implements InstructorEvaluationPhase3Service{
	
	@Autowired
		public RockSubmissionRepository submissionrepo;
		@Override
		public List<TblSubmission> findAllInPhase3() {
			//generte phase3
			// TODO Auto-generated method stub
			return submissionrepo.findAll();
			 
		}
		
//		@Override
//		public boolean saveComments(String comments, Integer submissionId) {
//			// TODO Auto-generated method stub
//			
//			TblPhaseevaluation phaseevaluation = new TblPhaseevaluation();
//			
//			phaseevaluation.setComments(comments);
//			
//			//phaseevaluation.setTblSubmission(tblSubmission);
//			
//			
//			//imagesubmissionrepo.save(arg0);
//			
//			
//			return false;
//		}



//		@Override
//		public TblSubmission getImageSubmission(Integer submissionId) {
//			// TODO Auto-generated method stub
//			
//			//imagesubmissionrepo.get
//			return null;
//		}
		
//		public List<TblSubmission> getSubmissions(String submissionId) {
//			
//			List<TblSubmission> tblsubmissionList = findAllInPhase3();
//			
//			List<TblSubmission>  mytbl= new ArrayList<>();
//			for(TblSubmission  tbl :tblsubmissionList){
//				if(tbl.tblgetSubmissionID() ==  Integer.parseInt(submissionId)){
//					mytbl.add(tbl);	
//				}
//			}
//			return mytbl;
//		}

		@Override
		public boolean saveComments(String comments, String submissionId) {
			// TODO Auto-generated method stub
			TblPhaseevaluation phaseevaluation = new TblPhaseevaluation();
			phaseevaluation.setComments(comments);
			// phaseevaluation.setTblSubmission(tblSubmission);
			// imagesubmissionrepo.save(arg0);
			return false;
		}

		@Override
		public boolean updateSubmission(TblSubmission submission) {
			// TODO Auto-generated method stub
			submissionrepo.save(submission);
			return false;
		}

	}

