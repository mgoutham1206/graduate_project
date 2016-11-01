package edu.nwmissouri.geoapp.service;

import java.util.List;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface InstructorEvaluationPhase3Service {
	
	/** find all attributes from submission **/
	public List<TblSubmission> findAllInPhase3();
	/** save comment for submission is **/
	public boolean saveComments(String comments, String submissionId);
	boolean updateSubmission(TblSubmission submission);


}
