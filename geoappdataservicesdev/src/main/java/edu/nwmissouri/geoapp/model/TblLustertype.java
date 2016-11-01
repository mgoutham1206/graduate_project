package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lustertype database table.
 * 
 */
@Entity
@Table(name="tbl_lustertype")
@NamedQuery(name="TblLustertype.findAll", query="SELECT t FROM TblLustertype t")
public class TblLustertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lusterTypeID;

	private String lusterName;

	public TblLustertype() {
	}

	public int getLusterTypeID() {
		return this.lusterTypeID;
	}

	public void setLusterTypeID(int lusterTypeID) {
		this.lusterTypeID = lusterTypeID;
	}

	public String getLusterName() {
		return this.lusterName;
	}

	public void setLusterName(String lusterName) {
		this.lusterName = lusterName;
	}

}