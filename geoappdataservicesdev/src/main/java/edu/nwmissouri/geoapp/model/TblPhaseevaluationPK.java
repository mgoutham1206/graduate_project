package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_phaseevaluation database table.
 * 
 */
@Embeddable
public class TblPhaseevaluationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int submissionID;

	@Column(insertable=false, updatable=false)
	private int phase;

	public TblPhaseevaluationPK() {
	}
	public int getSubmissionID() {
		return this.submissionID;
	}
	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}
	public int getPhase() {
		return this.phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblPhaseevaluationPK)) {
			return false;
		}
		TblPhaseevaluationPK castOther = (TblPhaseevaluationPK)other;
		return 
			(this.submissionID == castOther.submissionID)
			&& (this.phase == castOther.phase);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.submissionID;
		hash = hash * prime + this.phase;
		
		return hash;
	}
}