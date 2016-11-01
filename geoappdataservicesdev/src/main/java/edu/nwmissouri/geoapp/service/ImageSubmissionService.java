package edu.nwmissouri.geoapp.service;

import java.util.List;
import java.util.Map;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblPhasetype;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface ImageSubmissionService {

	 public TblImagesubmission save(TblImagesubmission Image) throws Exception; 
	 
	 public TblImagesubmission findOne(int imageID);
	 
	 public Map<String, Object> findAll();	 
	 
	 public List<TblImagesubmission> findAllImages();
	 	 
	 public TblSubmission submitPhaseOne(TblSubmission phaseonesubmission);
	 
	 void evictCache();
	 
	 public List<TblImagesubmission> fetchImagesforPhaseOne(int submissionId);
	 
	 public void deleteImagesforResubmission(int submissionId);
	 
	 public List<TblPhaseevaluation> getPhaseOneStatus(int submissionId);
	 
	 public TblPhasetype getPhaseType(int phaseId);
	 
	 public void deletePhaseOneEvaluation(int submissionId,int phase);
}
