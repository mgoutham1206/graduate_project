package edu.nwmissouri.geoapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.ImagesubmissionRepository;
import edu.nwmissouri.geoapp.service.InstructorEvaluationPhase1Service;

@Service
public class InstructorEvaluationPhase1Impl implements InstructorEvaluationPhase1Service {
	@Autowired
	public ImagesubmissionRepository imagesubmissionrepo;

	
	@Override
	public List<TblImagesubmission> findAllImagesPhase1() {
		// TODO Auto-generated method stub
		return imagesubmissionrepo.findAll();
	}

	
	public boolean saveComments(String comments, String submissionId) {
		// TODO Auto-generated method stub

		TblPhaseevaluation phaseevaluation = new TblPhaseevaluation();
		phaseevaluation.setComments(comments);
		// phaseevaluation.setTblSubmission(tblSubmission);
		// imagesubmissionrepo.save(arg0);
		return false;
	}

	/** get all image submission using submissionId **/
	
	public List<TblImagesubmission> getImageSubmissions(String submissionId) {
	
		List<TblImagesubmission> tblImgList = findAllImagesPhase1();
		
		List<TblImagesubmission>  mytbl= new ArrayList<>();
		for(TblImagesubmission  tbl :tblImgList){
			if(tbl.getTblSubmission().getSubmissionID() ==  Integer.parseInt(submissionId)){
				mytbl.add(tbl);	
			}
		}
		return mytbl;
	}

	@Override
	public boolean updateIsBest(String ImageId, String val) {
		// TODO Auto-generated method stub
		return false;
	}

	/** update image submissions **/
	@Override
	public boolean updateImageSubmission(TblImagesubmission imagesubmission) {
		// TODO Auto-generated method stub
		imagesubmissionrepo.save(imagesubmission);
		return false;
	}
}