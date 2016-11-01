package edu.nwmissouri.geoapp.controller.form;

public class StudentEmailData {
	
	private String name;
	private String toEmailId;
	
	public static final String subject = "Student Enrollment Activation";
	public static final String link = "http://csgrad12.nwmissouri.edu:8080/GeoApp/student/enrollment/activatestudent/";
	public static final String fromEmailId = "s522585@mail.nwmissouri.edu";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;  
		
	}   
	
	
	
	
	

}
