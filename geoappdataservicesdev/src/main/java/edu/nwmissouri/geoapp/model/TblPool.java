package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_pool database table.
 * 
 */
@Entity
@Table(name="tbl_pool")
@NamedQuery(name="TblPool.findAll", query="SELECT t FROM TblPool t")
public class TblPool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int poolID;

	private String createdBy;

	private Timestamp createdDate;

	private String poolName;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblAssignment
	@ManyToOne
	@JoinColumn(name="AssignID")
	@JsonBackReference
	private TblAssignment tblAssignment;

	//bi-directional many-to-one association to TblPoolquestion
	@OneToMany(mappedBy="tblPool")
	@JsonManagedReference
	private List<TblPoolquestion> tblPoolquestions;

	public TblPool() {
	}

	public int getPoolID() {
		return this.poolID;
	}

	public void setPoolID(int poolID) {
		this.poolID = poolID;
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

	public String getPoolName() {
		return this.poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
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

	public TblAssignment getTblAssignment() {
		return this.tblAssignment;
	}

	public void setTblAssignment(TblAssignment tblAssignment) {
		this.tblAssignment = tblAssignment;
	}

	public List<TblPoolquestion> getTblPoolquestions() {
		return this.tblPoolquestions;
	}

	public void setTblPoolquestions(List<TblPoolquestion> tblPoolquestions) {
		this.tblPoolquestions = tblPoolquestions;
	}

	public TblPoolquestion addTblPoolquestion(TblPoolquestion tblPoolquestion) {
		getTblPoolquestions().add(tblPoolquestion);
		tblPoolquestion.setTblPool(this);

		return tblPoolquestion;
	}

	public TblPoolquestion removeTblPoolquestion(TblPoolquestion tblPoolquestion) {
		getTblPoolquestions().remove(tblPoolquestion);
		tblPoolquestion.setTblPool(null);

		return tblPoolquestion;
	}

	public Object getTblQuiz() {
		// TODO Auto-generated method stub
		return null;
	}

	public TblPool(String poolName, TblAssignment tblAssignment) {
		super();
		this.poolName = poolName;
		this.tblAssignment = tblAssignment;
	}

	

	
}