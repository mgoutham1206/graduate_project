package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_section database table.
 * 
 */
@Entity
@Table(name="tbl_section")
@NamedQuery(name="TblSection.findAll", query="SELECT t FROM TblSection t")
public class TblSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sectionID;

	private String allowEnrollement;

	private String basicdescription;

	private String classPinsAreVisible;
	
	private String classURL;

	private String createdBy;

	private Timestamp createdDate;

	private String enrollmentPassword;

	private int expectedNoofStudents;

	private String title;

	private String updatedBy;

	private Timestamp updatedDate;

	private int year;

	//bi-directional many-to-one association to TblAssignment
	@OneToMany(mappedBy="tblSection", cascade = CascadeType.REMOVE, orphanRemoval = true)
	//@JsonManagedReference
	@JsonIgnore
	private List<TblAssignment> tblAssignments;

	//bi-directional many-to-one association to TblInstructorsection
	@OneToMany(mappedBy="tblSection", cascade = CascadeType.REMOVE, orphanRemoval = true)
	//@JsonManagedReference
	@JsonIgnore
	private List<TblInstructorsection> tblInstructorsections;

	//bi-directional many-to-one association to TblTermtype
	@ManyToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name="TermID")
	//@JsonBackReference
	//@JsonManagedReference
	@JsonIgnore
	private TblTermtype tblTermtype;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name="tbl_instructorsection",joinColumns=@JoinColumn(name="SectionID",referencedColumnName="sectionID"),
	inverseJoinColumns=@JoinColumn(name="instructorID",referencedColumnName="userID"))
	private List<TblUser> tblusers;	
	

	public List<TblUser> getTblusers() {
		return tblusers;
	}

	public void setTblusers(List<TblUser> tblusers) {
		this.tblusers = tblusers;
	}

	//bi-directional many-to-one association to TblStudent
	@OneToMany(mappedBy="tblSection", cascade = CascadeType.REMOVE, orphanRemoval = true)
	//@JsonManagedReference
   @JsonIgnore
	private List<TblStudent> tblStudents;

	public TblSection() {
	}

	public int getSectionID() {
		return this.sectionID;
	}

	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}

	public String getAllowEnrollement() {
		return this.allowEnrollement;
	}

	public void setAllowEnrollement(String allowEnrollement) {
		this.allowEnrollement = allowEnrollement;
	}

	public String getBasicdescription() {
		return this.basicdescription;
	}

	public void setBasicdescription(String basicdescription) {
		this.basicdescription = basicdescription;
	}

	public String getClassPinsAreVisible() {
		return this.classPinsAreVisible;
	}

	public void setClassPinsAreVisible(String classPinsAreVisible) {
		this.classPinsAreVisible = classPinsAreVisible;
	}

	public String getClassURL() {
		return this.classURL;
	}
	public void setClassURL(String classURL) {
		
		System.out.println(this.classURL+"dfg"+classURL+getClassURL()+getTitle());
		this.classURL = classURL;
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

	public String getEnrollmentPassword() {
		return this.enrollmentPassword;
	}

	public void setEnrollmentPassword(String enrollmentPassword) {
		this.enrollmentPassword = enrollmentPassword;
	}

	public int getExpectedNoofStudents() {
		return this.expectedNoofStudents;
	}

	public void setExpectedNoofStudents(int expectedNoofStudents) {
		this.expectedNoofStudents = expectedNoofStudents;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<TblAssignment> getTblAssignments() {
		return this.tblAssignments;
	}

	public void setTblAssignments(List<TblAssignment> tblAssignments) {
		this.tblAssignments = tblAssignments;
	}

	public TblAssignment addTblAssignment(TblAssignment tblAssignment) {
		getTblAssignments().add(tblAssignment);
		tblAssignment.setTblSection(this);

		return tblAssignment;
	}

	public TblAssignment removeTblAssignment(TblAssignment tblAssignment) {
		getTblAssignments().remove(tblAssignment);
		tblAssignment.setTblSection(null);

		return tblAssignment;
	}

	public List<TblInstructorsection> getTblInstructorsections() {
		return this.tblInstructorsections;
	}

	public void setTblInstructorsections(List<TblInstructorsection> tblInstructorsections) {
		this.tblInstructorsections = tblInstructorsections;
	}

	public TblInstructorsection addTblInstructorsection(TblInstructorsection tblInstructorsection) {
		getTblInstructorsections().add(tblInstructorsection);
		tblInstructorsection.setTblSection(this);

		return tblInstructorsection;
	}

	public TblInstructorsection removeTblInstructorsection(TblInstructorsection tblInstructorsection) {
		getTblInstructorsections().remove(tblInstructorsection);
		tblInstructorsection.setTblSection(null);

		return tblInstructorsection;
	}

	public TblTermtype getTblTermtype() {
		return this.tblTermtype;
	}

	public void setTblTermtype(TblTermtype tblTermtype) {
		this.tblTermtype = tblTermtype;
	}

	public List<TblStudent> getTblStudents() {
		return this.tblStudents;
	}

	public void setTblStudents(List<TblStudent> tblStudents) {
		this.tblStudents = tblStudents;
	}

	public TblStudent addTblStudent(TblStudent tblStudent) {
		getTblStudents().add(tblStudent);
		tblStudent.setTblSection(this);

		return tblStudent;
	}

	public TblStudent removeTblStudent(TblStudent tblStudent) {
		getTblStudents().remove(tblStudent);
		tblStudent.setTblSection(null);

		return tblStudent;
	}

}