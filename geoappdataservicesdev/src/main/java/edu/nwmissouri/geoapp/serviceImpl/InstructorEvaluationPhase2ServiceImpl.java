package edu.nwmissouri.geoapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.repository.ImagesubmissionRepository;
import edu.nwmissouri.geoapp.repository.MineralsubmissionRepository;
import edu.nwmissouri.geoapp.service.InstructorEvaluationPhase2Service;

public class InstructorEvaluationPhase2ServiceImpl implements InstructorEvaluationPhase2Service {

	
	@Autowired
	public MineralsubmissionRepository mineralsubmissionrepo;
	
	
	@Override
	public List<TblMineralsubmission> findAllInPhase2() {
		// TODO Auto-generated method stub
		return mineralsubmissionrepo.findAll();
	}

	@Override
	public boolean saveComments(String comments, String submissionId) {
		// TODO Auto-generated method stub
		TblPhaseevaluation phaseevaluation = new TblPhaseevaluation();
		phaseevaluation.setComments(comments);
		// phaseevaluation.setTblSubmission(tblSubmission);
		// imagesubmissionrepo.save(arg0);
		return false;
	}
	
/** get all mineral submissions using submissionId **/
	
	public List<TblMineralsubmission> getMineralSubmissions(String submissionId) {
	
		List<TblMineralsubmission> tblMineralList = findAllInPhase2();
		
		List<TblMineralsubmission>  mytbl= new ArrayList<>();
		for(TblMineralsubmission  tbl :tblMineralList){
			if(tbl.getTblSubmission().getSubmissionID() ==  Integer.parseInt(submissionId)){
				mytbl.add(tbl);	
			}
		}
		return mytbl;
	}
	
	/** update mineral submissions **/
	@Override
	public boolean updateMineralSubmission(TblMineralsubmission mineralsubmission) {
		// TODO Auto-generated method stub
		mineralsubmissionrepo.save(mineralsubmission);
		return false;
	}
	}
	

