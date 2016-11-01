package edu.nwmissouri.geoapp.service;

import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface RockSubmissionService {
	
	public void saveRockInfo(TblSubmission tblSubmission);
	
	public TblSubmission update(int AssignmentID, int StudentID);
	
	public TblPhaseevaluation findPhaseThreeEvaluation(int submissionId,int phase);
	
	public void deletePhaseThreeEvaluation(int submissionId,int phase);
		
}
