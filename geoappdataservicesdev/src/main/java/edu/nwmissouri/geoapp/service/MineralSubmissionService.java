package edu.nwmissouri.geoapp.service;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblMineralsubmission;

public interface MineralSubmissionService {
	
	public List<TblMineralsubmission> findBySubmissionID(int subID);
	
	public void saveMineralsubmission(TblMineralsubmission mineral);
	
	public List<TblMineralsubmission> findBySubmissionIDAndMineralID(int subID, int mineralID);
	
	public TblMineralsubmission getMineral(Integer mineralID);
	
	public void deleteMineral(Integer mineralID);
	
	public TblMineralsubmission findByMineralID(int mineralID);

}
