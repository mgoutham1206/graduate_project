package edu.nwmissouri.geoapp.controller.form;

import java.util.ArrayList;
import java.util.List;

import edu.nwmissouri.geoapp.model.TblRocktype;

public class PhaseThreeForm {

	private String rockName;
	private String rockType;
	private Integer submitId;
	private Integer assignID;
	private Integer studentID;
	private String evalComments;
	private List<TblRocktype> rockTypes=new ArrayList<>();

	public Integer getSubmitId() {
		return submitId;
	}
	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}
	public Integer getAssignID() {
		return assignID;
	}
	public void setAssignID(Integer assignID) {
		this.assignID = assignID;
	}
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public String getRockName() {
		return rockName;
	}
	public void setRockName(String rockName) {
		this.rockName = rockName;
	}
	public String getRockType() {
		return rockType;
	}
	public void setRockType(String rockType) {
		this.rockType = rockType;
	}
	public String getEvalComments() {
		return evalComments;
	}
	public void setEvalComments(String evalComments) {
		this.evalComments = evalComments;
	}
	public List<TblRocktype> getRocktypes() {
		return rockTypes;
	}
	public void setRocktypes(List<TblRocktype> rocktypes) {
		this.rockTypes = rocktypes;
	}
}
