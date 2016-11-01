package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_userrole database table.
 * 
 */
@Entity
@Table(name="tbl_userrole")
@NamedQuery(name="TblUserrole.findAll", query="SELECT t FROM TblUserrole t")
public class TblUserrole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblUserrolePK id;

	private String createdBy;

	private Timestamp createdDate;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblRoletype
	@ManyToOne
	@JoinColumn(name="RoleTypeID")
	private TblRoletype tblRoletype;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="UserID")
	private TblUser tblUser;

	public TblUserrole() {
	}

	public TblUserrolePK getId() {
		return this.id;
	}

	public void setId(TblUserrolePK id) {
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

	public TblRoletype getTblRoletype() {
		return this.tblRoletype;
	}

	public void setTblRoletype(TblRoletype tblRoletype) {
		this.tblRoletype = tblRoletype;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

}