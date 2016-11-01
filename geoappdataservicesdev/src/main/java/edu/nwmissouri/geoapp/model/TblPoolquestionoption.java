package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_poolquestionoption database table.
 * 
 */
@Entity
@Table(name="tbl_poolquestionoption")
@NamedQuery(name="TblPoolquestionoption.findAll", query="SELECT t FROM TblPoolquestionoption t")
public class TblPoolquestionoption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int poolQuestionOptionID;

	@Lob
	private String choice;

	private String createdBy;

	private Timestamp createdDate;

	private int displayOrder;

	private int fractionCorrect;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblPoolquestion
	@ManyToOne
	@JoinColumn(name="PoolQuestionID")
	@JsonBackReference
	private TblPoolquestion tblPoolquestion;

	public TblPoolquestionoption() {
	}

	public int getPoolQuestionOptionID() {
		return this.poolQuestionOptionID;
	}

	public void setPoolQuestionOptionID(int poolQuestionOptionID) {
		this.poolQuestionOptionID = poolQuestionOptionID;
	}


	public String getChoice() {
		return this.choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
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

	public int getFractionCorrect() {
		return this.fractionCorrect;
	}

	public void setFractionCorrect(int fractionCorrect) {
		this.fractionCorrect = fractionCorrect;
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

	public TblPoolquestion getTblPoolquestion() {
		return this.tblPoolquestion;
	}

	public void setTblPoolquestion(TblPoolquestion tblPoolquestion) {
		this.tblPoolquestion = tblPoolquestion;
	}

	public TblPoolquestionoption(String choice, int fractionCorrect) {
		super();
		this.choice = choice;
		this.fractionCorrect = fractionCorrect;
	}

	@Override
	public String toString() {
		return "TblPoolquestionoption [choice=" + choice + ", fractionCorrect=" + fractionCorrect + "]";
	}

	public TblPoolquestionoption(String choice, int displayOrder, int fractionCorrect,
			TblPoolquestion tblPoolquestion) {
		super();
		this.choice = choice;
		this.displayOrder = displayOrder;
		this.fractionCorrect = fractionCorrect;
		this.tblPoolquestion = tblPoolquestion;
	}
	
	
	
	

}