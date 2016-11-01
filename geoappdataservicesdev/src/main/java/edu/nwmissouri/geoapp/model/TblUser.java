package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity

@Table(name="tbl_user")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;

	
	private String createdBy;
	
	private Timestamp createdDate;

	private String emailAddress;

	private String loginName;

	private String name;
	
	private String updatedBy;
	
	private Timestamp updatedDate;

	private String userPassword;

	//bi-directional many-to-one association to TblInstructorsection
	@OneToMany(mappedBy="tblUser")
	@JsonIgnore
	private List<TblInstructorsection> tblInstructorsections;

	//bi-directional one-to-one association to TblStudent
	@JsonIgnore
	@OneToOne(mappedBy="tblUser")

	
	private TblStudent tblStudent;
	@JsonIgnore
	//bi-directional many-to-one association to TblSubmission
	@OneToMany(mappedBy="tblUser")
	
	
	

	private List<TblSubmission> tblSubmissions;
	@ManyToMany(mappedBy="tblusers")
	@JsonIgnore
	private List<TblSection> tlbsec;
	
		
public List<TblSection> getTlbsec() {
	return tlbsec;
}

public void setTlbsec(List<TblSection> tlbsec) {
	this.tlbsec = tlbsec;
}

	//bi-directional many-to-one association to TblUserrole
	@JsonIgnore
	@OneToMany(mappedBy="tblUser")


	private List<TblUserrole> tblUserroles;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tbl_userrole",joinColumns=@JoinColumn(name="UserID",referencedColumnName="userID"),
	inverseJoinColumns=@JoinColumn(name="RoleTypeID",referencedColumnName="roleTypeID"))
	private List<TblRoletype> tblroles;
	

	public List<TblRoletype> getTblroles() {
		return tblroles;
	}

	public void setTblroles(List<TblRoletype> tblroles) {
		this.tblroles = tblroles;
	}

	public TblUser() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<TblInstructorsection> getTblInstructorsections() {
		return this.tblInstructorsections;
	}

	public void setTblInstructorsections(List<TblInstructorsection> tblInstructorsections) {
		this.tblInstructorsections = tblInstructorsections;
	}

	public TblInstructorsection addTblInstructorsection(TblInstructorsection tblInstructorsection) {
		getTblInstructorsections().add(tblInstructorsection);
		tblInstructorsection.setTblUser(this);

		return tblInstructorsection;
	}

	public TblInstructorsection removeTblInstructorsection(TblInstructorsection tblInstructorsection) {
		getTblInstructorsections().remove(tblInstructorsection);
		tblInstructorsection.setTblUser(null);

		return tblInstructorsection;
	}

	public TblStudent getTblStudent() {
		return this.tblStudent;
	}

	public void setTblStudent(TblStudent tblStudent) {
		this.tblStudent = tblStudent;
	}

	public List<TblSubmission> getTblSubmissions() {
		return this.tblSubmissions;
	}

	public void setTblSubmissions(List<TblSubmission> tblSubmissions) {
		this.tblSubmissions = tblSubmissions;
	}

	public TblSubmission addTblSubmission(TblSubmission tblSubmission) {
		getTblSubmissions().add(tblSubmission);
		tblSubmission.setTblUser(this);

		return tblSubmission;
	}

	public TblSubmission removeTblSubmission(TblSubmission tblSubmission) {
		getTblSubmissions().remove(tblSubmission);
		tblSubmission.setTblUser(null);

		return tblSubmission;
	}

	public List<TblUserrole> getTblUserroles() {
		return this.tblUserroles;
	}

	public void setTblUserroles(List<TblUserrole> tblUserroles) {
		this.tblUserroles = tblUserroles;
	}

	public TblUserrole addTblUserrole(TblUserrole tblUserrole) {
		getTblUserroles().add(tblUserrole);
		tblUserrole.setTblUser(this);

		return tblUserrole;
	}

	public TblUserrole removeTblUserrole(TblUserrole tblUserrole) {
		getTblUserroles().remove(tblUserrole);
		tblUserrole.setTblUser(null);

		return tblUserrole;
	}

}