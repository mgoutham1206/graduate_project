package edu.nwmissouri.geoapp.controller.form;

public class EnrollmentCountExceedEmailForm {
	

	private String toEmailId;
	private int enrollmentLimit;
	private int exceedCount;
	private String section;
	public String subject = "GeoApp "+getSection()+": Student Enrollment Alert";
//	public static final String link = "http://localhost:8080/instructor/changePassword/";
//	public static final String link = "http://csgrad12.nwmissouri.edu:8080/GeoApp/instructor/changePassword/";
	public static final String fromEmailId = "s522585@mail.nwmissouri.edu";
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	public int getEnrollmentLimit() {
		return enrollmentLimit;
	}
	public void setEnrollmentLimit(int enrollmentLimit) {
		this.enrollmentLimit = enrollmentLimit;
	}
	public int getExceedCount() {
		return exceedCount;
	}
	public void setExceedCount(int exceedCount) {
		this.exceedCount = exceedCount;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
