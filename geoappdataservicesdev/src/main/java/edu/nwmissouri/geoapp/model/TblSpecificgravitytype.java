package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specificgravitytype database table.
 * 
 */
@Entity
@Table(name="tbl_specificgravitytype")
@NamedQuery(name="TblSpecificgravitytype.findAll", query="SELECT t FROM TblSpecificgravitytype t")
public class TblSpecificgravitytype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int specificGravityID;

	private String specificGravityName;

	private int specificGravityOrder;

	public TblSpecificgravitytype() {
	}

	public int getSpecificGravityID() {
		return this.specificGravityID;
	}

	public void setSpecificGravityID(int specificGravityID) {
		this.specificGravityID = specificGravityID;
	}

	public String getSpecificGravityName() {
		return this.specificGravityName;
	}

	public void setSpecificGravityName(String specificGravityName) {
		this.specificGravityName = specificGravityName;
	}

	public int getSpecificGravityOrder() {
		return this.specificGravityOrder;
	}

	public void setSpecificGravityOrder(int specificGravityOrder) {
		this.specificGravityOrder = specificGravityOrder;
	}

}