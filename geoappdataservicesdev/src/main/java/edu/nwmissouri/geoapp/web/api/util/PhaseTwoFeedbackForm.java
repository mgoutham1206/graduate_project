package edu.nwmissouri.geoapp.web.api.util;

public class PhaseTwoFeedbackForm {
	
	private int minerlaId;
	private String mineralName;
	private String attributeName;
	private String isAccepted;
	private String comments;
	
	public PhaseTwoFeedbackForm(int mineralId,String mineralName, String attributeName, String isAccepted, String comments) {
		this.minerlaId = mineralId;
		this.mineralName = mineralName;
		this.attributeName = attributeName;
		this.isAccepted = isAccepted;
		this.comments = comments;
	}

	public int getMinerlaId() {
		return minerlaId;
	}

	public void setMinerlaId(int minerlaId) {
		this.minerlaId = minerlaId;
	}

	public String getMineralName() {
		return mineralName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getIsAccepted() {
		return isAccepted;
	}

	public String getComments() {
		return comments;
	}

	public void setMineralName(String mineralName) {
		this.mineralName = mineralName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
