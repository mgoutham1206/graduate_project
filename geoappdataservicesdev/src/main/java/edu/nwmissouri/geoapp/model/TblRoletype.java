package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_roletype database table.
 * 
 */
@Entity
@Table(name="tbl_roletype")
@NamedQuery(name="TblRoletype.findAll", query="SELECT t FROM TblRoletype t")
public class TblRoletype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roleTypeID;

	private String roleName;

	//bi-directional many-to-one association to TblUserrole
	@OneToMany(mappedBy="tblRoletype")
	private List<TblUserrole> tblUserroles;
	
	@ManyToMany(mappedBy="tblroles")
	private List<TblUser> tlbusers;
	
	public List<TblUser> getTlbusers() {
		return tlbusers;
	}

	public void setTlbusers(List<TblUser> tlbusers) {
		this.tlbusers = tlbusers;
	}

	public TblRoletype() {
	}

	public int getRoleTypeID() {
		return this.roleTypeID;
	}

	public void setRoleTypeID(int roleTypeID) {
		this.roleTypeID = roleTypeID;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<TblUserrole> getTblUserroles() {
		return this.tblUserroles;
	}

	public void setTblUserroles(List<TblUserrole> tblUserroles) {
		this.tblUserroles = tblUserroles;
	}

	public TblUserrole addTblUserrole(TblUserrole tblUserrole) {
		getTblUserroles().add(tblUserrole);
		tblUserrole.setTblRoletype(this);

		return tblUserrole;
	}

	public TblUserrole removeTblUserrole(TblUserrole tblUserrole) {
		getTblUserroles().remove(tblUserrole);
		tblUserrole.setTblRoletype(null);

		return tblUserrole;
	}

}