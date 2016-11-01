package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_quiz database table.
 * 
 */
@Entity
@Table(name="tbl_quiz")
@NamedQuery(name="TblQuiz.findAll", query="SELECT t FROM TblQuiz t")
public class TblQuiz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int quizID;

	private String createdBy;

	private Timestamp createdDate;

	private int num_Takes_Max;
	
	private int qualpercent; 

	private int numQuestions;

	private String quizDesc;

	private String quizName;
	
	private String timer;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblAssignment
	@OneToMany(mappedBy="tblQuiz")
	@JsonIgnore
	private List<TblAssignment> tblAssignments;

	//bi-directional many-to-one association to TblStudentquiz
	@OneToMany(mappedBy="tblQuiz")
	@JsonIgnore
	private List<TblStudentquiz> tblStudentquizs;

	//bi-directional many-to-one association to TblStudentquiztake
	@OneToMany(mappedBy="tblQuiz")
	@JsonIgnore
	private List<TblStudentquiztake> tblStudentquiztakes;

	public TblQuiz() {
	}

	public int getQuizID() {
		return this.quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public int getQualpercent() {
		return qualpercent;
	}

	public void setQualpercent(int qualpercent) {
		this.qualpercent = qualpercent;
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

	public int getNum_Takes_Max() {
		return this.num_Takes_Max;
	}

	public void setNum_Takes_Max(int num_Takes_Max) {
		this.num_Takes_Max = num_Takes_Max;
	}

	
	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	public int getNumQuestions() {
		return this.numQuestions;
	}

	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	public String getQuizDesc() {
		return this.quizDesc;
	}

	public void setQuizDesc(String quizDesc) {
		this.quizDesc = quizDesc;
	}

	public String getQuizName() {
		return this.quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
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

	public List<TblAssignment> getTblAssignments() {
		return this.tblAssignments;
	}

	public void setTblAssignments(List<TblAssignment> tblAssignments) {
		this.tblAssignments = tblAssignments;
	}

	public TblAssignment addTblAssignment(TblAssignment tblAssignment) {
		getTblAssignments().add(tblAssignment);
		tblAssignment.setTblQuiz(this);

		return tblAssignment;
	}

	public TblAssignment removeTblAssignment(TblAssignment tblAssignment) {
		getTblAssignments().remove(tblAssignment);
		tblAssignment.setTblQuiz(null);

		return tblAssignment;
	}

	public List<TblStudentquiz> getTblStudentquizs() {
		return this.tblStudentquizs;
	}

	public void setTblStudentquizs(List<TblStudentquiz> tblStudentquizs) {
		this.tblStudentquizs = tblStudentquizs;
	}

	public TblStudentquiz addTblStudentquiz(TblStudentquiz tblStudentquiz) {
		getTblStudentquizs().add(tblStudentquiz);
		tblStudentquiz.setTblQuiz(this);

		return tblStudentquiz;
	}

	public TblStudentquiz removeTblStudentquiz(TblStudentquiz tblStudentquiz) {
		getTblStudentquizs().remove(tblStudentquiz);
		tblStudentquiz.setTblQuiz(null);

		return tblStudentquiz;
	}

	public List<TblStudentquiztake> getTblStudentquiztakes() {
		return this.tblStudentquiztakes;
	}

	public void setTblStudentquiztakes(List<TblStudentquiztake> tblStudentquiztakes) {
		this.tblStudentquiztakes = tblStudentquiztakes;
	}

	public TblStudentquiztake addTblStudentquiztake(TblStudentquiztake tblStudentquiztake) {
		getTblStudentquiztakes().add(tblStudentquiztake);
		tblStudentquiztake.setTblQuiz(this);

		return tblStudentquiztake;
	}

	public TblStudentquiztake removeTblStudentquiztake(TblStudentquiztake tblStudentquiztake) {
		getTblStudentquiztakes().remove(tblStudentquiztake);
		tblStudentquiztake.setTblQuiz(null);

		return tblStudentquiztake;
	}

	public TblQuiz(int num_Takes_Max, int qualpercent, int numQuestions, String quizDesc, String quizName,
			String timer) {
		super();
		this.num_Takes_Max = num_Takes_Max;
		this.qualpercent = qualpercent;
		this.numQuestions = numQuestions;
		this.quizDesc = quizDesc;
		this.quizName = quizName;
		this.timer = timer;
	}

}