package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_studentquiztake database table.
 * 
 */
@Entity
@Table(name="tbl_studentquiztake")
@NamedQuery(name="TblStudentquiztake.findAll", query="SELECT t FROM TblStudentquiztake t")
public class TblStudentquiztake implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int studentQuizTakeID;

	private String createdBy;

	private Timestamp createdDate;

	private int takeNum;
	
	private int pointsCorrect;

	private String updatedBy;

	private Timestamp updatedDate;

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

	public TblStudentquiztake() {
	}

	public int getStudentQuizTakeID() {
		return this.studentQuizTakeID;
	}

	public void setStudentQuizTakeID(int studentQuizTakeID) {
		this.studentQuizTakeID = studentQuizTakeID;
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

	public int getTakeNum() {
		return this.takeNum;
	}

	public void setTakeNum(int takeNum) {
		this.takeNum = takeNum;
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

	public int getPointsCorrect() {
		return pointsCorrect;
	}

	public void setPointsCorrect(int pointsCorrect) {
		this.pointsCorrect = pointsCorrect;
	}

	public TblStudentquiztake(int takeNum, int pointsCorrect, TblQuiz tblQuiz, TblStudent tblStudent) {
		super();
		this.takeNum = takeNum;
		this.pointsCorrect = pointsCorrect;
		this.tblQuiz = tblQuiz;
		this.tblStudent = tblStudent;
	}

}