package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_hardnesstype database table.
 * 
 */
@Entity
@Table(name="tbl_hardnesstype")
@NamedQuery(name="TblHardnesstype.findAll", query="SELECT t FROM TblHardnesstype t")
public class TblHardnesstype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private short hardnessTypeID;

	private int hardnessMax;

	private int hardnessMin;

	private String hardnessName;

	private short hardnessOrder;

	public TblHardnesstype() {
	}

	public short getHardnessTypeID() {
		return this.hardnessTypeID;
	}

	public void setHardnessTypeID(short hardnessTypeID) {
		this.hardnessTypeID = hardnessTypeID;
	}

	public int getHardnessMax() {
		return this.hardnessMax;
	}

	public void setHardnessMax(int hardnessMax) {
		this.hardnessMax = hardnessMax;
	}

	public int getHardnessMin() {
		return this.hardnessMin;
	}

	public void setHardnessMin(int hardnessMin) {
		this.hardnessMin = hardnessMin;
	}

	public String getHardnessName() {
		return this.hardnessName;
	}

	public void setHardnessName(String hardnessName) {
		this.hardnessName = hardnessName;
	}

	public short getHardnessOrder() {
		return this.hardnessOrder;
	}

	public void setHardnessOrder(short hardnessOrder) {
		this.hardnessOrder = hardnessOrder;
	}

}