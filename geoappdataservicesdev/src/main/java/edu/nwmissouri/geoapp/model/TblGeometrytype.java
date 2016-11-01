package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_geometrytype database table.
 * 
 */
@Entity
@Table(name="tbl_geometrytype")
@NamedQuery(name="TblGeometrytype.findAll", query="SELECT t FROM TblGeometrytype t")
public class TblGeometrytype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int geometryTypeID;

	private String angleType;

	private int geometryOrder;

	private int geometrySets;

	public TblGeometrytype() {
	}

	public int getGeometryTypeID() {
		return this.geometryTypeID;
	}

	public void setGeometryTypeID(int geometryTypeID) {
		this.geometryTypeID = geometryTypeID;
	}

	public String getAngleType() {
		return this.angleType;
	}

	public void setAngleType(String angleType) {
		this.angleType = angleType;
	}

	public int getGeometryOrder() {
		return this.geometryOrder;
	}

	public void setGeometryOrder(int geometryOrder) {
		this.geometryOrder = geometryOrder;
	}

	public int getGeometrySets() {
		return this.geometrySets;
	}

	public void setGeometrySets(int geometrySets) {
		this.geometrySets = geometrySets;
	}

}