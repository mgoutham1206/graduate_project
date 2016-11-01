package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_rocktype database table.
 * 
 */
@Entity
@Table(name="tbl_rocktype")
@NamedQuery(name="TblRocktype.findAll", query="SELECT t FROM TblRocktype t")
public class TblRocktype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int rockTypeID;

	private String rockTypeName;

	private int rockTypeOrder;

	public TblRocktype() {
	}

	public int getRockTypeID() {
		return this.rockTypeID;
	}

	public void setRockTypeID(int rockTypeID) {
		this.rockTypeID = rockTypeID;
	}

	public String getRockTypeName() {
		return this.rockTypeName;
	}

	public void setRockTypeName(String rockTypeName) {
		this.rockTypeName = rockTypeName;
	}

	public int getRockTypeOrder() {
		return this.rockTypeOrder;
	}

	public void setRockTypeOrder(int rockTypeOrder) {
		this.rockTypeOrder = rockTypeOrder;
	}

}