package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tbl_studentquiz database table.
 * 
 */
@Entity
@Table(name="tbl_studentquiz")
@NamedQuery(name="TblStudentquiz.findAll", query="SELECT t FROM TblStudentquiz t")
public class TblStudentquiz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int studentQuizID;

	private String createdBy;

	private Timestamp createdDate;

	private int numTakes;
	
	private int maxScore;

	private String updatedBy;

	private Timestamp updatedDate;
	
	// adding new attribute lastUpdatedTime
	
	private String lastUpdatedTime;

	//bi-directional many-to-one association to TblQuiz
	@ManyToOne
	@JoinColumn(name="QuizID")
	@JsonBackReference
	private TblQuiz tblQuiz;

	//bi-directional many-to-one association to TblStudent
	@ManyToOne
	@JoinColumn(name="StudentID")
	@JsonBackReference
	private TblStudent tblStudent;

	public TblStudentquiz() {
	}

	public int getStudentQuizID() {
		return this.studentQuizID;
	}

	public void setStudentQuizID(int studentQuizID) {
		this.studentQuizID = studentQuizID;
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

	public int getNumTakes() {
		return this.numTakes;
	}

	public void setNumTakes(int numTakes) {
		this.numTakes = numTakes;
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

	public TblQuiz getTblQuiz() {
		return this.tblQuiz;
	}

	public void setTblQuiz(TblQuiz tblQuiz) {
		this.tblQuiz = tblQuiz;
	}

	public TblStudent getTblStudent() {
		return this.tblStudent;
	}

	public void setTblStudent(TblStudent tblStudent) {
		this.tblStudent = tblStudent;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public TblStudentquiz(int numTakes, int maxScore, /*Timestamp updatedDate,*/ TblQuiz tblQuiz, TblStudent tblStudent) {
		super();
		this.numTakes = numTakes;
		this.maxScore = maxScore;
		//this.updatedDate = updatedDate;
		this.tblQuiz = tblQuiz;
		this.tblStudent = tblStudent;
	}
	
	

}