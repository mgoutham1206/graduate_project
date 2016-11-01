package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_phasetype database table.
 * 
 */
@Entity
@Table(name="tbl_phasetype")
@NamedQuery(name="TblPhasetype.findAll", query="SELECT t FROM TblPhasetype t")
public class TblPhasetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int phaseID;

	private String phaseName;

	private int phaseOrder;

	//bi-directional many-to-one association to TblPhaseevaluation
	@OneToMany(mappedBy="tblPhasetype")
	private List<TblPhaseevaluation> tblPhaseevaluations;

	public TblPhasetype() {
	}

	public int getPhaseID() {
		return this.phaseID;
	}

	public void setPhaseID(int phaseID) {
		this.phaseID = phaseID;
	}

	public String getPhaseName() {
		return this.phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public int getPhaseOrder() {
		return this.phaseOrder;
	}

	public void setPhaseOrder(int phaseOrder) {
		this.phaseOrder = phaseOrder;
	}

	public List<TblPhaseevaluation> getTblPhaseevaluations() {
		return this.tblPhaseevaluations;
	}

	public void setTblPhaseevaluations(List<TblPhaseevaluation> tblPhaseevaluations) {
		this.tblPhaseevaluations = tblPhaseevaluations;
	}

	public TblPhaseevaluation addTblPhaseevaluation(TblPhaseevaluation tblPhaseevaluation) {
		getTblPhaseevaluations().add(tblPhaseevaluation);
		tblPhaseevaluation.setTblPhasetype(this);

		return tblPhaseevaluation;
	}

	public TblPhaseevaluation removeTblPhaseevaluation(TblPhaseevaluation tblPhaseevaluation) {
		getTblPhaseevaluations().remove(tblPhaseevaluation);
		tblPhaseevaluation.setTblPhasetype(null);

		return tblPhaseevaluation;
	}

}