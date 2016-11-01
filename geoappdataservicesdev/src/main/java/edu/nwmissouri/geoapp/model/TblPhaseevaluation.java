package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_phaseevaluation database table.
 * 
 */
@Entity
@Table(name="tbl_phaseevaluation")
//@NamedQuery(name="TblPhaseevaluation.findAll", query="SELECT t FROM TblPhaseevaluation t")
public class TblPhaseevaluation implements Serializable {
	
		private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected TblPhaseevaluationPK id;

	private String comments;

	private String createdBy;

	private Timestamp createdDate;

	private String isAccepted;

	private BigDecimal points;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblPhasetype
	@ManyToOne
    @JoinColumn(name = "Phase", referencedColumnName = "PhaseID", insertable = false, updatable = false)
	@JsonBackReference(value="Phaseid")
	private TblPhasetype tblPhasetype;

	//bi-directional many-to-one association to TblSubmission
	@ManyToOne
    @JoinColumn(name = "SubmissionID", referencedColumnName = "SubmissionID", insertable = false, updatable = false)
	@JsonBackReference(value="SubmissionID")
	private TblSubmission tblSubmission;
//
	public TblPhaseevaluation() {
	}

	public TblPhaseevaluationPK getId() {
		return this.id;
	}

	public void setId(TblPhaseevaluationPK id) {
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

	public BigDecimal getPoints() {
		return this.points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
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

	public TblPhasetype getTblPhasetype() {
		return this.tblPhasetype;
	}

	public void setTblPhasetype(TblPhasetype tblPhasetype) {
		this.tblPhasetype = tblPhasetype;
	}

	public TblSubmission getTblSubmission() {
		return this.tblSubmission;
	}

	public void setTblSubmission(TblSubmission tblSubmission) {
		this.tblSubmission = tblSubmission;
	}

}