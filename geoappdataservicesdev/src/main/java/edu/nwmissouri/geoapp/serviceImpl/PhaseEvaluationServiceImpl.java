package edu.nwmissouri.geoapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.service.PhaseEvaluationService;

@Service
public class PhaseEvaluationServiceImpl implements PhaseEvaluationService {

	
	@Autowired
	public PhaseevaluationRepository phaseevaluationRepository;
	
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePhaseEvaluation(TblPhaseevaluation phaseevaluation) {
		phaseevaluationRepository.save(phaseevaluation);
	}

	@Override
	public void deletePhaseEvaluation(TblPhaseevaluation phaseevaluation) {
		// TODO Auto-generated method stub
		phaseevaluationRepository.delete(phaseevaluation);
		
	}

	@Override
	public void deletePhaseOneEvaluation(int submissionId, int phase) {
		// TODO Auto-generated method stub
		phaseevaluationRepository.deletePhaseOneEvalForSubmission(submissionId, phase);
		
	}
	

}
