package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_poolquestion database table.
 * 
 */
@Entity
@Table(name="tbl_poolquestion")
@NamedQuery(name="TblPoolquestion.findAll", query="SELECT t FROM TblPoolquestion t")
public class TblPoolquestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int poolQuestionID;

	private String createdBy;

	private Timestamp createdDate;

	private int displayOrder;

	@Lob
	private String question;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblPool
	@ManyToOne
	@JoinColumn(name="PoolID")
	@JsonBackReference
	private TblPool tblPool;

	//bi-directional many-to-one association to TblPoolquestionfillin
	@OneToMany(mappedBy="tblPoolquestion")
	@JsonManagedReference
	private List<TblPoolquestionfillin> tblPoolquestionfillins;

	//bi-directional many-to-one association to TblPoolquestionoption
	@OneToMany(mappedBy="tblPoolquestion")
	@JsonManagedReference
	private List<TblPoolquestionoption> tblPoolquestionoptions;

	public TblPoolquestion() {
	}

	public int getPoolQuestionID() {
		return this.poolQuestionID;
	}

	public void setPoolQuestionID(int poolQuestionID) {
		this.poolQuestionID = poolQuestionID;
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

	public int getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public TblPool getTblPool() {
		return this.tblPool;
	}

	public void setTblPool(TblPool tblPool) {
		this.tblPool = tblPool;
	}

	public List<TblPoolquestionfillin> getTblPoolquestionfillins() {
		return this.tblPoolquestionfillins;
	}

	public void setTblPoolquestionfillins(List<TblPoolquestionfillin> tblPoolquestionfillins) {
		this.tblPoolquestionfillins = tblPoolquestionfillins;
	}

	public TblPoolquestionfillin addTblPoolquestionfillin(TblPoolquestionfillin tblPoolquestionfillin) {
		getTblPoolquestionfillins().add(tblPoolquestionfillin);
		tblPoolquestionfillin.setTblPoolquestion(this);

		return tblPoolquestionfillin;
	}

	public TblPoolquestionfillin removeTblPoolquestionfillin(TblPoolquestionfillin tblPoolquestionfillin) {
		getTblPoolquestionfillins().remove(tblPoolquestionfillin);
		tblPoolquestionfillin.setTblPoolquestion(null);

		return tblPoolquestionfillin;
	}

	public List<TblPoolquestionoption> getTblPoolquestionoptions() {
		return this.tblPoolquestionoptions;
	}

	public void setTblPoolquestionoptions(List<TblPoolquestionoption> tblPoolquestionoptions) {
		this.tblPoolquestionoptions = tblPoolquestionoptions;
	}

	public TblPoolquestionoption addTblPoolquestionoption(TblPoolquestionoption tblPoolquestionoption) {
		getTblPoolquestionoptions().add(tblPoolquestionoption);
		tblPoolquestionoption.setTblPoolquestion(this);

		return tblPoolquestionoption;
	}

	public TblPoolquestionoption removeTblPoolquestionoption(TblPoolquestionoption tblPoolquestionoption) {
		getTblPoolquestionoptions().remove(tblPoolquestionoption);
		tblPoolquestionoption.setTblPoolquestion(null);

		return tblPoolquestionoption;
	}

	public TblPoolquestion(int displayOrder, String question, TblPool tblPool) {
		super();
		this.displayOrder = displayOrder;
		this.question = question;
		this.tblPool = tblPool;
	}

}