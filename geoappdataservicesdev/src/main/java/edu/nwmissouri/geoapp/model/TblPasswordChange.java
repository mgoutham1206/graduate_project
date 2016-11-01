package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity

@Table(name = "tblpasswordchange")
@NamedQuery(name = "TblPasswordChange.findAll", query = "SELECT t FROM TblPasswordChange t")
public class TblPasswordChange implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;

	private int userInfo;
	
	private char isUsed;


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(int userInfo) {
		this.userInfo = userInfo;
	}

	public char getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(char isUsed) {
		this.isUsed = isUsed;
	}


	
	

}
