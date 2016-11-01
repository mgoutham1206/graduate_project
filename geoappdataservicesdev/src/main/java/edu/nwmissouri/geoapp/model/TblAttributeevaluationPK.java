package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_attributeevaluation database table.
 * 
 */
@Embeddable
public class TblAttributeevaluationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int mineralID;

	@Column(insertable=false, updatable=false)
	private int attributeID;

	public TblAttributeevaluationPK() {
	}
	public int getMineralID() {
		return this.mineralID;
	}
	public void setMineralID(int mineralID) {
		this.mineralID = mineralID;
	}
	public int getAttributeID() {
		return this.attributeID;
	}
	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblAttributeevaluationPK)) {
			return false;
		}
		TblAttributeevaluationPK castOther = (TblAttributeevaluationPK)other;
		return 
			(this.mineralID == castOther.mineralID)
			&& (this.attributeID == castOther.attributeID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mineralID;
		hash = hash * prime + this.attributeID;
		
		return hash;
	}
}