package edu.nwmissouri.geoapp.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.controller.form.EnrollmentCountExceedEmailForm;

@Service
public class EnrollmentCountExceedEmailManager {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String sectionName, int enrollementLimit, int exceedLimit) {

		System.out.println("in send email sevice");
		try {
			EnrollmentCountExceedEmailForm data = createEmailData(to , sectionName, enrollementLimit,exceedLimit);
			MimeMessage mimemailMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimemailMessage, true);

			mailMessage.setFrom(data.fromEmailId);
			mailMessage.setTo(data.getToEmailId());
			mailMessage.setSubject(data.getSubject());
			mailMessage.setText("<html><body><h3>Alert : One of your sections enrollment limit is exceeded</h3>"
					+ "<div><table>"					
					+ "<tr><td>Section : </td><td>" + data.getSection() + "</td></tr>" 
					+"<tr><td>Enrollment Limit : </td><td>" + data.getEnrollmentLimit() + "</td></tr>"
					+ "<tr><td>Exceeded Count : "
							+ "</td><td>" + data.getExceedCount() + "</td></tr>"
					+ "</table></div><div><h4>We suggest you to change enrollment limit or deactive to unkonwn students</h4></div></body></html>", true);
			
			System.out.println("Excess Limit mail formed and ready to send.");
			mailSender.send(mimemailMessage);
		}

		catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	private EnrollmentCountExceedEmailForm createEmailData(String to, String sectionName, int enrollementLimit, int exceedLimit) {
		EnrollmentCountExceedEmailForm emailData = new EnrollmentCountExceedEmailForm();
		emailData.setToEmailId(to);
		emailData.setSection(sectionName);
		emailData.setSubject("GeoApp "+emailData.getSection()+": Student Enrollment Alert");
		emailData.setEnrollmentLimit(enrollementLimit);
		emailData.setExceedCount(exceedLimit);
		return emailData;

	}
}
