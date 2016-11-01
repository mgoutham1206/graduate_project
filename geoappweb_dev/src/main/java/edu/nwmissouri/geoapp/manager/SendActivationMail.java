package edu.nwmissouri.geoapp.manager;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.nwmissouri.geoapp.controller.form.StudentEmailData;

public class SendActivationMail  implements Runnable{

	String mailID;
	String sName;
	int studentID;
	
	public SendActivationMail(String mailID, String sName, int studentID) {
		super();
		this.mailID = mailID;
		this.sName = sName;
		this.studentID = studentID;
	}
	
	
	
	@Override
	public void run() {

		
	////Send an Email via Outlook Office 365 SMTP server using TLS connection.
	    
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.office365.com");        
	    props.put("mail.smtp.port", "587");
	    

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication("s522585@mail.nwmissouri.edu", "Nag@19922");   //Your username may require the entire email id 'username@example.com'
	                }
	            });

	    try {
	    	StudentEmailData data = createEmailData(mailID);
	    	
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("s522585@mail.nwmissouri.edu"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(mailID));
	        message.setSubject("Student Enrollment Activation");
	        
	        String someHtmlMessage = "<html><body><h3>Hello "+ sName+","+"</h3>"
					+ "</div><div><h4>Please click on below link to activate the enrollment</h4>" + "<a href="
					+ data.link +studentID+ ">" + data.link +studentID+ "</a>"+"<p>if you are not done this please ignore this email.</p>"
							+ "<br/><br/><p>Thanks,<br/>GeoApp</p></div></body></html>";
	        
	        message.setContent(someHtmlMessage, "text/html; charset=utf-8");
	        Transport.send(message);   

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }

	
	}	
	
	
	
	
	private StudentEmailData createEmailData(String toEmailId) {
		StudentEmailData emailData = new StudentEmailData();
		//String username[] = toEmailId.split("@");
		emailData.setToEmailId(toEmailId);
		emailData.setName(toEmailId);
		return emailData;
	}
	
	
	
	
}
