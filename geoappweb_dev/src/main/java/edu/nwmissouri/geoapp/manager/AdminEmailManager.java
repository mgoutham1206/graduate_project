package edu.nwmissouri.geoapp.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.controller.form.EmailData;

@Service
public class AdminEmailManager {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, int passwordURLId , String password) {

		System.out.println("in send email sevice");
		try {
			EmailData data = createEmailData(to , password);
			MimeMessage mimemailMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimemailMessage, true);

			mailMessage.setFrom(EmailData.fromEmailId);
			mailMessage.setTo(data.getToEmailId());
			mailMessage.setSubject(EmailData.subject);
			mailMessage.setText("<html><body><h3>Instructor Login Details</h3>"
					+ "<div><table>"					
					+ "<tr><td>Username : </td><td>" + data.getUsername() + "</td></tr>" 
					+"<tr><td>Password : </td><td>" + data.getPassword() + "</td></tr>"
					+ "<tr><td>EmailID : "
							+ "</td><td>" + data.getToEmailId() + "</td></tr>"
					+ "</table></div><div><h4>Please click on below link to change password</h4>" + "<a href="
					+ data.link +passwordURLId+ ">" + data.link +passwordURLId+ "</a></div></body></html>", true);
			mailSender.send(mimemailMessage);
		}

		catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	private EmailData createEmailData(String toEmailId , String password) {
		EmailData emailData = new EmailData();
		String username[] = toEmailId.split("@");
		emailData.setToEmailId(toEmailId);
		emailData.setName(toEmailId);
		emailData.setUsername(username[0]);
		/*String tempPassword = emailData.generateRandomString(username[0]);
		System.out.println("pass====" + tempPassword);*/
		emailData.setPassword(password);

		return emailData;

	}
}
