package edu.nwmissouri.geoapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.repository.RockSubmissionRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.service.RockSubmissionService;

@Service
public class RockSubmissionServiceImpl implements RockSubmissionService {

	@Autowired
	RockSubmissionRepository submissionRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PhaseevaluationRepository phaseevaluationRepository;
	
	
	@Override
	public TblSubmission update(int AssignmentID, int StudentID){	
		return (submissionRepo.findByAssignmentAndStudentId(AssignmentID, StudentID));	
	}
	
	
	@Override
	public void saveRockInfo(TblSubmission tblSubmission) {
		
		submissionRepo.save(tblSubmission);
		
		/*public Map<String, Object> getSectionData() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<TblSubmission> tblSubmission=sectionRepository.findAll();
		System.out.println(tblSubmision);
		map.put("Submission",tblSubmission);
		return map;
		
	
	}*/
	/*
	 @Override
	public TblSubmission getSectionByID(Int id) {
		// TODO Auto-generated method stub
		return submissionRepository.findOne(id);
	}
	 * */
	
	/*@Override
	public void saveSubmission(TblSubmission tblSubmission) {
		// TODO Auto-generated method stub
		sectionRepository.save(tblSubmission);
	}*/
		/*
		 * 
		 * 
	@Override
	public List<TblRocktype> findAll() {
		// TODO Auto-generated method stub
		return rocktyperepo.findAll();
	
		 */
		/*
		 * @Override
		public List<TblSubmission> findAllPhase3() {
			//generate phase3
			// TODO Auto-generated method stub
			return submissionrepo.findAll();
		 */
	}
	
	
	public TblPhaseevaluation findPhaseThreeEvaluation(int submissionId,int phase){
		return phaseevaluationRepository.findFeedbackForPhase2(submissionId, phase);
	}
	
	public void deletePhaseThreeEvaluation(int submissionId,int phase){
		  phaseevaluationRepository.deletePhaseOneEvalForSubmission(submissionId,phase);
	}

}
