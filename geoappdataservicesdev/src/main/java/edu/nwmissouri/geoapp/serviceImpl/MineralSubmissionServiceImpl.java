package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.repository.MineralsubmissionRepository;
import edu.nwmissouri.geoapp.service.MineralSubmissionService;

@Service
public class MineralSubmissionServiceImpl implements MineralSubmissionService{

	
	@Autowired 
	MineralsubmissionRepository mineralrepo;
	
	@Override
	public List<TblMineralsubmission> findBySubmissionID(int submissionID) {
		// TODO Auto-generated method stub
		return mineralrepo.findBySubmissionID(submissionID);
	}

	@Override
	public void saveMineralsubmission(TblMineralsubmission mineral) {
		// TODO Auto-generated method stub		
		mineralrepo.save(mineral);		
	}

	@Override
	public List<TblMineralsubmission> findBySubmissionIDAndMineralID(int subID, int mineralID) {
		// TODO Auto-generated method stub
		return mineralrepo.findBySubmissionIDAndMineralID(subID, mineralID);
	}

	@Override
	public TblMineralsubmission findByMineralID(int mineralID) {
		// TODO Auto-generated method stub
		return mineralrepo.findByMineralID(mineralID);
	
	}

	@Override
	public TblMineralsubmission getMineral(Integer mineralID) {
		// TODO Auto-generated method stub
		return mineralrepo.findByMineralID(mineralID);
	}

	@Override
	public void deleteMineral(Integer mineralID) {
		// TODO Auto-generated method stub
		mineralrepo.delete(mineralID);
	}

	
}
