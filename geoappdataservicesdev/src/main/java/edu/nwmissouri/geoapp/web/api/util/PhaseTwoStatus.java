package edu.nwmissouri.geoapp.web.api.util;

import java.util.List;

import edu.nwmissouri.geoapp.model.TblMineralsubmission;

public class PhaseTwoStatus {
	
	 //check if mineral submission exists
    //if exists check tbl_phaseevaluation-- no record then waiting for feedback
    // if record exists then check Isaccepted Yes or No
    //Yes -- Completed
    //No -- Resubmit

	private boolean isMineralExists;
	private boolean isPhaseEvaluationExists;
	private String isAccepted;
	private String status;
		
	private List<TblMineralsubmission> Minerals;

	 private String phase3Status;

	    public String getPhase3Status() {
	        return phase3Status;
	    }

	    public void setPhase3Status(String phase3Status) {
	        this.phase3Status = phase3Status;
	    }


	public boolean isMineralExists() {
		return isMineralExists;
	}


	public void setMineralExists(boolean isMineralExists) {
		this.isMineralExists = isMineralExists;
	}


	public boolean isPhaseEvaluationExists() {
		return isPhaseEvaluationExists;
	}


	public void setPhaseEvaluationExists(boolean isPhaseEvaluationExists) {
		this.isPhaseEvaluationExists = isPhaseEvaluationExists;
	}


	public String getIsAccepted() {
		return isAccepted;
	}


	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<TblMineralsubmission> getMinerals() {
		return Minerals;
	}


	public void setMinerals(List<TblMineralsubmission> minerals) {
		Minerals = minerals;
	}
	
	
	
	

}
