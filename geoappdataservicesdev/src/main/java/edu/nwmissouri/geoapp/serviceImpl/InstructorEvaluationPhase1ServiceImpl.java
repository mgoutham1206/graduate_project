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
public class InstructorEvaluationPhase1ServiceImpl implements InstructorEvaluationPhase1Service {
	@Autowired
	public ImagesubmissionRepository imagesubmissionRepo;

	@Override
	public List<TblImagesubmission> findAllImagesPhase1() {
		return imagesubmissionRepo.findAll();
	}

	public boolean saveComments(String comments, String submissionId) {
		TblPhaseevaluation phaseEvaluation = new TblPhaseevaluation();
		phaseEvaluation.setComments(comments);
		// phaseevaluation.setTblSubmission(tblSubmission);
		// imagesubmissionrepo.save(arg0);
		return false;
	}

	/** get all image submission using submissionId **/

	public List<TblImagesubmission> getImageSubmissions(String submissionIdStr) {
		int submissionId = Integer.parseInt(submissionIdStr);
		List<TblImagesubmission> imagesList = imagesubmissionRepo.findImagesBySubmissionId(submissionId);
		return imagesList;
	}

	@Override
	public boolean updateIsBest(String ImageId, String val) {
		return false;
	}

	/** update image submissions **/
	@Override
	public boolean updateImageSubmission(TblImagesubmission imagesubmission) {
		imagesubmissionRepo.save(imagesubmission);
		return false;
	}
}