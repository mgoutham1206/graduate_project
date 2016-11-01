package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_userrole database table.
 * 
 */
@Embeddable
public class TblUserrolePK implements Serializable {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int userID;

	@Column(insertable=false, updatable=false)
	private int roleTypeID;

	public TblUserrolePK() {
	}
	public int getUserID() {
		return this.userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoleTypeID() {
		return this.roleTypeID;
	}
	public void setRoleTypeID(int roleTypeID) {
		this.roleTypeID = roleTypeID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblUserrolePK)) {
			return false;
		}
		TblUserrolePK castOther = (TblUserrolePK)other;
		return 
			(this.userID == castOther.userID)
			&& (this.roleTypeID == castOther.roleTypeID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userID;
		hash = hash * prime + this.roleTypeID;
		
		return hash;
	}
}