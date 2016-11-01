package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_poolquestionfillin database table.
 * 
 */
@Entity
@Table(name="tbl_poolquestionfillin")
@NamedQuery(name="TblPoolquestionfillin.findAll", query="SELECT t FROM TblPoolquestionfillin t")
public class TblPoolquestionfillin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int poolQuestionFillinID;

	private String createdBy;

	private Timestamp createdDate;

	

	@Lob
	private String choice;
	
	private int fractionCorrect;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblPoolquestion
	@ManyToOne
	@JoinColumn(name="PoolQuestionID")
	@JsonBackReference
	private TblPoolquestion tblPoolquestion;

	public TblPoolquestionfillin() {
	}

	public int getPoolQuestionFillinID() {
		return this.poolQuestionFillinID;
	}

	public void setPoolQuestionFillinID(int poolQuestionFillinID) {
		this.poolQuestionFillinID = poolQuestionFillinID;
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

	

	

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public int getFractionCorrect() {
		return fractionCorrect;
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

}