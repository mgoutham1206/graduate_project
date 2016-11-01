package edu.nwmissouri.geoapp.controller.form;

import java.util.ArrayList;
import java.util.List;

import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblAttributetype;

public class Mineral {
	private Integer mineralId;
	private String luster;
	private String hardness;
	private String streakColor;
	private String geometry;
	private String specificGravity;
	private String color;
	private String minName;
	private String evalComments;
	private Integer submitId;
	private Integer tempMinId;
	private List<TblAttributeevaluation> evaluation=new ArrayList<>();
	private List<String> isAccepted;

	public Integer getMineralId() {
		return mineralId;
	}

	public void setMineralId(Integer mineralId) {
		this.mineralId = mineralId;
	}

	public String getLuster() {
		return luster;
	}

	public void setLuster(String luster) {
		this.luster = luster;
	}

	public String getHardness() {
		return hardness;
	}

	public void setHardness(String hardness) {
		this.hardness = hardness;
	}

	public String getStreakColor() {
		return streakColor;
	}

	public void setStreakColor(String streakColor) {
		this.streakColor = streakColor;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	public String getSpecificGravity() {
		return specificGravity;
	}

	public void setSpecificGravity(String specificGravity) {
		this.specificGravity = specificGravity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMinName() {
		return minName;
	}

	public void setMinName(String minName) {
		this.minName = minName;
	}

	public String getEvalComments() {
		return evalComments;
	}

	public void setEvalComments(String evalComments) {
		this.evalComments = evalComments;
	}

	public List<TblAttributeevaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(List<TblAttributeevaluation> evaluation) {
		this.evaluation = evaluation;
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public Integer getTempMinId() {
		return tempMinId;
	}

	public void setTempMinId(Integer tempId) {
		this.tempMinId = tempId;
	}

	public List<String> getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(List<String> isAccepted) {
		this.isAccepted = isAccepted;
	}

}