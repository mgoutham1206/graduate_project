package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_instructorsection database table.
 * 
 */
@Entity
@Table(name="tbl_instructorsection")
@NamedQuery(name="TblInstructorsection.findAll", query="SELECT t FROM TblInstructorsection t")
public class TblInstructorsection implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblInstructorsectionPK id;

	private String createdBy;

	private Timestamp createdDate;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblSection
	@ManyToOne
	@JoinColumn(name="SectionID")
	@JsonBackReference
	private TblSection tblSection;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="InstructorID")
	@JsonBackReference
	private TblUser tblUser;

	public TblInstructorsection() {
	}

	public TblInstructorsectionPK getId() {
		return this.id;
	}

	public void setId(TblInstructorsectionPK id) {
		this.id = id;
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

}