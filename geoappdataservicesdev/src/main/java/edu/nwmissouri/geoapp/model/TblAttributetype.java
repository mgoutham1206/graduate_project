package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_attributetype database table.
 * 
 */
@Entity
@Table(name="tbl_attributetype")
@NamedQuery(name="TblAttributetype.findAll", query="SELECT t FROM TblAttributetype t")
public class TblAttributetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attributeID;

	private String attributeName;

	private int attributeOrder;

	//bi-directional many-to-one association to TblAttributeevaluation
	@OneToMany(mappedBy="tblAttributetype")
	private List<TblAttributeevaluation> tblAttributeevaluations;

	public TblAttributetype() {
	}

	public int getAttributeID() {
		return this.attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public int getAttributeOrder() {
		return this.attributeOrder;
	}

	public void setAttributeOrder(int attributeOrder) {
		this.attributeOrder = attributeOrder;
	}

	public List<TblAttributeevaluation> getTblAttributeevaluations() {
		return this.tblAttributeevaluations;
	}

	public void setTblAttributeevaluations(List<TblAttributeevaluation> tblAttributeevaluations) {
		this.tblAttributeevaluations = tblAttributeevaluations;
	}

	public TblAttributeevaluation addTblAttributeevaluation(TblAttributeevaluation tblAttributeevaluation) {
		getTblAttributeevaluations().add(tblAttributeevaluation);
		tblAttributeevaluation.setTblAttributetype(this);

		return tblAttributeevaluation;
	}

	public TblAttributeevaluation removeTblAttributeevaluation(TblAttributeevaluation tblAttributeevaluation) {
		getTblAttributeevaluations().remove(tblAttributeevaluation);
		tblAttributeevaluation.setTblAttributetype(null);

		return tblAttributeevaluation;
	}

}