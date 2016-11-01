package edu.nwmissouri.geoapp.controller.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PhaseOneForm {
	private List<MultipartFile> images;
	
	private String latitude;
	
	private String longitude;
	
	private String description;

	private Integer assignID;
	
	private Integer submitId;
	
	private Integer userId;
	
	private List<String> evalComments;
	
	private List<String> isAccepted;
	
	private String comment;
	
	private String imagesPath;
	
	private List<Integer> imageIDs;
	
	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}

	public Integer getAssignID() {
		return assignID;
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public void setAssignID(Integer assignId) {
		this.assignID = assignId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<String> getEvalComments() {
		return evalComments;
	}

	public void setEvalComments(List<String> evalComments) {
		this.evalComments = evalComments;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Integer> getImageIDs() {
		return imageIDs;
	}

	public void setImageIDs(List<Integer> imageIDs) {
		this.imageIDs = imageIDs;
	}

	public List<String> getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(List<String> isAccepted) {
		this.isAccepted = isAccepted;
	}

}
