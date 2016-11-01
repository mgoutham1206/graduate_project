package edu.nwmissouri.geoapp.service;

import antlr.collections.List;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;

public interface PhaseEvaluationService {
	
	
	public List findAll();

	/** save phase evaluation **/
	public void savePhaseEvaluation(TblPhaseevaluation phaseevaluation);
	
	
	public void deletePhaseOneEvaluation(int submissionId,int phase);
	
	public void deletePhaseEvaluation(TblPhaseevaluation phaseevaluation);
	
}