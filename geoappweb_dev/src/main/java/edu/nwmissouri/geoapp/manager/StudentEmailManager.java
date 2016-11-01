package edu.nwmissouri.geoapp.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import edu.nwmissouri.geoapp.controller.form.StudentEmailData;

@Service
public class StudentEmailManager {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(int studentId , String to, String sname) {  

		System.out.println("in send email service");
		try {
			StudentEmailData data = createEmailData(to);
			MimeMessage mimemailMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimemailMessage, true);

			mailMessage.setFrom(StudentEmailData.fromEmailId);
			mailMessage.setTo(data.getToEmailId());
			mailMessage.setSubject(StudentEmailData.subject);
			
			mailMessage.setText("<html><body><h3>Hello "+ sname+","+"</h3>"
					+ "</div><div><h4>Please click on below link to activate the enrollment</h4>" + "<a href="
					+ data.link +studentId+ ">" + data.link +studentId+ "</a>"+"<p>if you are not done this please ignore this email.</p>"
							+ "<br/><br/><p>Thanks,<br/>GeoApp</p></div></body></html>", true);
			mailSender.send(mimemailMessage);
		}

		catch (MessagingException e) {

			e.printStackTrace();
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
