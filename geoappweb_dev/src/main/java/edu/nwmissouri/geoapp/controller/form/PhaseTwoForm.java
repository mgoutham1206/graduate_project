package edu.nwmissouri.geoapp.controller.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhaseTwoForm {

	private List<Mineral> minerals;
	
	private Integer submitId;
	
	private Integer assignID;
	
	private String comment;
	
	private Set<Integer> deleteMineralSet = new HashSet<>();
	
	public PhaseTwoForm() {
		super();
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}


	public Integer getAssignID() {
		return assignID;
	}

	public void setAssignID(Integer assignId) {
		this.assignID = assignId;
	}

	public List<Mineral> getMinerals() {
		return minerals;
	}

	public void setMinerals(List<Mineral> minerals) {
		this.minerals = minerals;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<Integer> getDeleteMineralSet() {
		return deleteMineralSet;
	}

	public void setDeleteMineralSet(Set<Integer> deleteMineralSet) {
		this.deleteMineralSet = deleteMineralSet;
	}

}