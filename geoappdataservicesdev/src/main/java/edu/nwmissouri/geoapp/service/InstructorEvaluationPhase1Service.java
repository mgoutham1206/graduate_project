package edu.nwmissouri.geoapp.service;

import java.util.List;

import edu.nwmissouri.geoapp.model.TblImagesubmission;

public interface InstructorEvaluationPhase1Service {
	
	
	/** find all image submissions **/
	public List<TblImagesubmission> findAllImagesPhase1();
	/** save comment for submission is **/
	public boolean saveComments(String comments, String submissionId);
	/** get all image submission using submissionId **/
	public List<TblImagesubmission> getImageSubmissions(String submissionId);
	/** update IsBest for each Image ID **/
	public boolean updateIsBest(String ImageId, String val);
	/** update image submissions **/
	public boolean updateImageSubmission(TblImagesubmission imagesubmission);

	
	
}
