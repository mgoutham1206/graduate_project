package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_assignment database table.
 * 
 */
@Entity
@Table(name="tbl_assignment")
@NamedQuery(name="TblAssignment.findAll", query="SELECT t FROM TblAssignment t")
public class TblAssignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int assignID;

//	private int sectionID;
	
	private String createdBy;

	private Timestamp createdDate;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date due_date;

	private String isActive;

	private String name;

	private BigDecimal possiblepointsphase1;

	private BigDecimal possiblepointsphase2;

	private BigDecimal possiblepointsphase3;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblQuiz
	@ManyToOne
	@JoinColumn(name="QuizID")
	@JsonIgnore
	private TblQuiz tblQuiz;

	//bi-directional many-to-one association to TblSection
	@ManyToOne
	@JoinColumn(name="SectionID")
	@JsonIgnore
	private TblSection tblSection;

	//bi-directional many-to-one association to TblPool
	@OneToMany(mappedBy="tblAssignment")
	@JsonIgnore
	private List<TblPool> tblPools;

	//bi-directional many-to-one association to TblSubmission
	@OneToMany(mappedBy="tblAssignment")
	@JsonIgnore
	private List<TblSubmission> tblSubmissions;

	public TblAssignment() {
	}

	public int getAssignID() {
		return this.assignID;
	}

	public void setAssignID(int assignID) {
		this.assignID = assignID;
	}
//	public int getSectionID() {
//		return sectionID;
//	}
//
//	public void setSectionID(int sectionID) {
//		this.sectionID = sectionID;
//	}
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

	public Date getDue_date() {
		return this.due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPossiblepointsphase1() {
		return this.possiblepointsphase1;
	}

	public void setPossiblepointsphase1(BigDecimal possiblepointsphase1) {
		this.possiblepointsphase1 = possiblepointsphase1;
	}

	public BigDecimal getPossiblepointsphase2() {
		return this.possiblepointsphase2;
	}

	public void setPossiblepointsphase2(BigDecimal possiblepointsphase2) {
		this.possiblepointsphase2 = possiblepointsphase2;
	}

	public BigDecimal getPossiblepointsphase3() {
		return this.possiblepointsphase3;
	}

	public void setPossiblepointsphase3(BigDecimal possiblepointsphase3) {
		this.possiblepointsphase3 = possiblepointsphase3;
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

	public TblQuiz getTblQuiz() {
		return this.tblQuiz;
	}

	public void setTblQuiz(TblQuiz tblQuiz) {
		this.tblQuiz = tblQuiz;
	}

	public TblSection getTblSection() {
		return this.tblSection;
	}

	public void setTblSection(TblSection tblSection) {
		this.tblSection = tblSection;
	}

	public List<TblPool> getTblPools() {
		return this.tblPools;
	}

	public void setTblPools(List<TblPool> tblPools) {
		this.tblPools = tblPools;
	}

	public TblPool addTblPool(TblPool tblPool) {
		getTblPools().add(tblPool);
		tblPool.setTblAssignment(this);

		return tblPool;
	}

	public TblPool removeTblPool(TblPool tblPool) {
		getTblPools().remove(tblPool);
		tblPool.setTblAssignment(null);

		return tblPool;
	}

	public List<TblSubmission> getTblSubmissions() {
		return this.tblSubmissions;
	}

	public void setTblSubmissions(List<TblSubmission> tblSubmissions) {
		this.tblSubmissions = tblSubmissions;
	}

	public TblSubmission addTblSubmission(TblSubmission tblSubmission) {
		getTblSubmissions().add(tblSubmission);
		tblSubmission.setTblAssignment(this);

		return tblSubmission;
	}

	public TblSubmission removeTblSubmission(TblSubmission tblSubmission) {
		getTblSubmissions().remove(tblSubmission);
		tblSubmission.setTblAssignment(null);

		return tblSubmission;
	}

}