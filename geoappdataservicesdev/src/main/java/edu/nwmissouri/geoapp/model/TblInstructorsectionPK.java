package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_instructorsection database table.
 * 
 */
@Embeddable
public class TblInstructorsectionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int instructorID;

	@Column(insertable=false, updatable=false)
	private int sectionID;

	public TblInstructorsectionPK() {
	}
	public int getInstructorID() {
		return this.instructorID;
	}
	public void setInstructorID(int instructorID) {
		this.instructorID = instructorID;
	}
	public int getSectionID() {
		return this.sectionID;
	}
	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblInstructorsectionPK)) {
			return false;
		}
		TblInstructorsectionPK castOther = (TblInstructorsectionPK)other;
		return 
			(this.instructorID == castOther.instructorID)
			&& (this.sectionID == castOther.sectionID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.instructorID;
		hash = hash * prime + this.sectionID;
		
		return hash;
	}
}