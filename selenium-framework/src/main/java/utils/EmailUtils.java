package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {
	public static void sendTestReport(String reportPath) {
		final String senderEmail = "apidakosha1328@gmail.com";
		final String appPassword = "dzcpmgjrllndmmwh";
		final String recipientEmail = "apidakosha1328@gmail.com";

		// SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		// create session with Authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});
		session.setDebug(true);
		try {
			// create email message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email from Automation");

			// message.setText("Hello, \n This is a test email sent from Java \n Regards,\n
			// QA Team");

			// Email body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello, \n\n This is a test email sent from Java \n\n Regards,\n QA Team");

			// Attachment part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			//String filePath = System.getProperty("user.dir") + "/reports/ExtentReport_2025-06-03_19-32-05.html";
			System.out.println("Attachment path is: " + reportPath);
			attachmentPart.attachFile(new File(reportPath));

			// combine body and attachment part
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);

			// send email
			Transport.send(message);
			System.out.println("Email sent successfully with attachments...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
