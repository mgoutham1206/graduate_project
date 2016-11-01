package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_student database table.
 * 
 */
@Entity
@Table(name="tbl_student")
@NamedQuery(name="TblStudent.findAll", query="SELECT t FROM TblStudent t")
public class TblStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int studentID;

	private String createdBy;

	private Timestamp createdDate;

	private String isActive;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblSection
	@ManyToOne
	@JoinColumn(name="SectionID")
	@JsonIgnore
	private TblSection tblSection;

	//bi-directional one-to-one association to TblUser
	@OneToOne
	@PrimaryKeyJoinColumn(name="StudentID", referencedColumnName="userID")
	@JsonIgnore
	private TblUser tblUser;

	//bi-directional many-to-one association to TblStudentquiz
	@OneToMany(mappedBy="tblStudent")
	@JsonIgnore
	private List<TblStudentquiz> tblStudentquizs;

	//bi-directional many-to-one association to TblStudentquiztake
	@OneToMany(mappedBy="tblStudent")
	@JsonIgnore
	private List<TblStudentquiztake> tblStudentquiztakes;

	public TblStudent() {
	}

	public int getStudentID() {
		return this.studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
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

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public TblSection getTblSection() {
		return this.tblSection;
	}

	public void setTblSection(TblSection tblSection) {
		this.tblSection = tblSection;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public List<TblStudentquiz> getTblStudentquizs() {
		return this.tblStudentquizs;
	}

	public void setTblStudentquizs(List<TblStudentquiz> tblStudentquizs) {
		this.tblStudentquizs = tblStudentquizs;
	}

	public TblStudentquiz addTblStudentquiz(TblStudentquiz tblStudentquiz) {
		getTblStudentquizs().add(tblStudentquiz);
		tblStudentquiz.setTblStudent(this);

		return tblStudentquiz;
	}

	public TblStudentquiz removeTblStudentquiz(TblStudentquiz tblStudentquiz) {
		getTblStudentquizs().remove(tblStudentquiz);
		tblStudentquiz.setTblStudent(null);

		return tblStudentquiz;
	}

	public List<TblStudentquiztake> getTblStudentquiztakes() {
		return this.tblStudentquiztakes;
	}

	public void setTblStudentquiztakes(List<TblStudentquiztake> tblStudentquiztakes) {
		this.tblStudentquiztakes = tblStudentquiztakes;
	}

	public TblStudentquiztake addTblStudentquiztake(TblStudentquiztake tblStudentquiztake) {
		getTblStudentquiztakes().add(tblStudentquiztake);
		tblStudentquiztake.setTblStudent(this);

		return tblStudentquiztake;
	}

	public TblStudentquiztake removeTblStudentquiztake(TblStudentquiztake tblStudentquiztake) {
		getTblStudentquiztakes().remove(tblStudentquiztake);
		tblStudentquiztake.setTblStudent(null);

		return tblStudentquiztake;
	}

	@Override
	public String toString() {
		return "TblStudent [studentID=" + studentID + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", isActive=" + isActive + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", tblSection=" + tblSection + ", tblUser=" + tblUser + ", tblStudentquizs=" + tblStudentquizs
				+ ", tblStudentquiztakes=" + tblStudentquiztakes + "]";
	}

}