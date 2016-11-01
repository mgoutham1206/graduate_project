package edu.nwmissouri.geoapp.controller.form;

public class EmailData {
	
	private String username;
	private String toEmailId;
	private String password;
	private String name;
	public static final String subject = "Instructor Login Details";
//	public static final String link = "http://localhost:8080/instructor/changePassword/";
	public static final String link = "http://csgrad12.nwmissouri.edu:8080/GeoApp/instructor/changePassword/";
	public static final String fromEmailId = "s522585@mail.nwmissouri.edu";
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public  String generateRandomString(String username){
		StringBuffer buffer = new StringBuffer();
		int length = 5,min = 0,max=61;
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for(int i=0;i<length;i++){
			int index = (int)Math.floor((Math.random() * (max - min)) + min);
			buffer.append(characters.charAt(index));
		}
		String temppassword = username+buffer.toString();
		return temppassword;
	}
	
	
}
