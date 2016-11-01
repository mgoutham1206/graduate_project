package edu.nwmissouri.geoapp.service;

import java.util.List;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;

public interface InstructorEvaluationPhase2Service {
	
	/** find all minerals submissions **/
	public List<TblMineralsubmission> findAllInPhase2();
	/** save comment for submission is **/
	public boolean saveComments(String comments, String submissionId);
	boolean updateMineralSubmission(TblMineralsubmission mineralsubmission);

}
