package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_attributeevaluation database table.
 * 
 */
@Entity
@Table(name="tbl_attributeevaluation")
@NamedQuery(name="TblAttributeevaluation.findAll", query="SELECT t FROM TblAttributeevaluation t")
public class TblAttributeevaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblAttributeevaluationPK id;

	private String comments;

	private String createdBy;

	private Timestamp createdDate;

	private String isAccepted;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblAttributetype
	@ManyToOne
	@JoinColumn(name="AttributeID")
	@JsonBackReference
	private TblAttributetype tblAttributetype;

	//bi-directional many-to-one association to TblMineralsubmission
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="MineralID")
	private TblMineralsubmission tblMineralsubmission;

	public TblAttributeevaluation() {
	}

	public TblAttributeevaluationPK getId() {
		return this.id;
	}

	public void setId(TblAttributeevaluationPK id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsAccepted() {
		return this.isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public TblAttributetype getTblAttributetype() {
		return this.tblAttributetype;
	}

	public void setTblAttributetype(TblAttributetype tblAttributetype) {
		this.tblAttributetype = tblAttributetype;
	}

	public TblMineralsubmission getTblMineralsubmission() {
		return this.tblMineralsubmission;
	}

	public void setTblMineralsubmission(TblMineralsubmission tblMineralsubmission) {
		this.tblMineralsubmission = tblMineralsubmission;
	}

}