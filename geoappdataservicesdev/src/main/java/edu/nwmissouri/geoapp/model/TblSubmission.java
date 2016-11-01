package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_submission database table.
 * 
 */
@Entity
@Table(name="tbl_submission")
@NamedQuery(name="TblSubmission.findAll", query="SELECT t FROM TblSubmission t")
public class TblSubmission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int submissionID;

	private String createdBy;

	private Timestamp createdDate;

	private String description;

	private String rockName;

	private String rockType;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblImagesubmission
	@OneToMany(mappedBy="tblSubmission", cascade=CascadeType.ALL)
//	@JsonIgnore
	private List<TblImagesubmission> tblImagesubmissions;

	//bi-directional many-to-one association to TblMineralsubmission
	@OneToMany(mappedBy="tblSubmission", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<TblMineralsubmission> tblMineralsubmissions;

	//bi-directional many-to-one association to TblPhaseevaluation
	@OneToMany(mappedBy="tblSubmission", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<TblPhaseevaluation> tblPhaseevaluations;

	//bi-directional many-to-one association to TblAssignment
	@ManyToOne
	@JoinColumn(name="AssignmentID")
	@JsonIgnore
	private TblAssignment tblAssignment;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="StudentID")
	private TblUser tblUser;

	public TblSubmission() {
	}

	public int getSubmissionID() {
		return this.submissionID;
	}

	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRockName() {
		return this.rockName;
	}

	public void setRockName(String rockName) {
		this.rockName = rockName;
	}

	public String getRockType() {
		return this.rockType;
	}

	public void setRockType(String rockType) {
		this.rockType = rockType;
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

	public List<TblImagesubmission> getTblImagesubmissions() {
		return this.tblImagesubmissions;
	}

	public void setTblImagesubmissions(List<TblImagesubmission> tblImagesubmissions) {
		this.tblImagesubmissions = tblImagesubmissions;
	}

	public TblImagesubmission addTblImagesubmission(TblImagesubmission tblImagesubmission) {
		getTblImagesubmissions().add(tblImagesubmission);
		tblImagesubmission.setTblSubmission(this);

		return tblImagesubmission;
	}

	public TblImagesubmission removeTblImagesubmission(TblImagesubmission tblImagesubmission) {
		getTblImagesubmissions().remove(tblImagesubmission);
		tblImagesubmission.setTblSubmission(null);

		return tblImagesubmission;
	}

	public List<TblMineralsubmission> getTblMineralsubmissions() {
		return this.tblMineralsubmissions;
	}

	public void setTblMineralsubmissions(List<TblMineralsubmission> tblMineralsubmissions) {
		this.tblMineralsubmissions = tblMineralsubmissions;
	}

	public TblMineralsubmission addTblMineralsubmission(TblMineralsubmission tblMineralsubmission) {
		getTblMineralsubmissions().add(tblMineralsubmission);
		tblMineralsubmission.setTblSubmission(this);

		return tblMineralsubmission;
	}

	public TblMineralsubmission removeTblMineralsubmission(TblMineralsubmission tblMineralsubmission) {
		getTblMineralsubmissions().remove(tblMineralsubmission);
		tblMineralsubmission.setTblSubmission(null);

		return tblMineralsubmission;
	}

	public List<TblPhaseevaluation> getTblPhaseevaluations() {
		return this.tblPhaseevaluations;
	}

	public void setTblPhaseevaluations(List<TblPhaseevaluation> tblPhaseevaluations) {
		this.tblPhaseevaluations = tblPhaseevaluations;
	}

	public TblPhaseevaluation addTblPhaseevaluation(TblPhaseevaluation tblPhaseevaluation) {
		getTblPhaseevaluations().add(tblPhaseevaluation);
		tblPhaseevaluation.setTblSubmission(this);

		return tblPhaseevaluation;
	}

	public TblPhaseevaluation removeTblPhaseevaluation(TblPhaseevaluation tblPhaseevaluation) {
		getTblPhaseevaluations().remove(tblPhaseevaluation);
		tblPhaseevaluation.setTblSubmission(null);

		return tblPhaseevaluation;
	}

	public TblAssignment getTblAssignment() {
		return this.tblAssignment;
	}

	public void setTblAssignment(TblAssignment tblAssignment) {
		this.tblAssignment = tblAssignment;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	/*@Override
	public int compareTo(TblSubmission other) {
		if(other.getSubmissionID() < this.getSubmissionID())
		return 1;
		else return 0;
	}
	
	@Override
	public String toString(){
//		return "Rock Name : "+this.getRockName()+" Rock Type : "+this.getRockType();
		return "Rock Name\" : \""+this.getRockName()+"\" \"Rock Type\" : \""+this.getRockType();
	}*/

}