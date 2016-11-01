package edu.nwmissouri.geoapp.web.api.util;

import java.util.List;

public class PhaseOneForm {

	private List<String> imageList;
    private String latitude;
    private String longitude;
    private String description;
    private String errorMessage;
    private String statusCode;
    private int submissionId;
    private int assignmentId;
    private int studentId;
    private String studentName;
    private List<String> comments;
    private int imageID;
    private String isAcceptedPhaseOne;
    private String isEvaluated;
    private String isUserValid;
    
   

    public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}


    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getIsAcceptedPhaseOne() {
		return isAcceptedPhaseOne;
	}

	public void setIsAcceptedPhaseOne(String isAcceptedPhaseOne) {
		this.isAcceptedPhaseOne = isAcceptedPhaseOne;
	}

	public String getIsEvaluated() {
		return isEvaluated;
	}

	public void setIsEvaluated(String isEvaluated) {
		this.isEvaluated = isEvaluated;
	}

	public String getIsUserValid() {
		return isUserValid;
	}

	public void setIsUserValid(String isUserValid) {
		this.isUserValid = isUserValid;
	}

	   
}
