package org.rec.studentdetails.sender;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.rec.studentdetails.Utils;
import org.rec.studentdetails.pojo.Student;

public class MailSender {

	Session session = null;
	public MailSender() {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Utils.getvalue("email_username"), Utils.getvalue("email_password"));
			}
		});
	}
	
	public void sendMails(List<Student> students) {
		for(Student student : students){
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(Utils.getvalue("email_username")));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getMailId()));
				message.setSubject(Utils.getvalue("email_subject"));
				message.setText("Dear Parent, Please fine your son attendance details below\n\n\n"
						+ "Total days : " + student.getAbsents() 
						+ "\n No. of days Present : " + student.getPresents() 
						+ "\n No. of days Absent : "+student.getAbsents()
						+ " \nThanks\n RAC Thandalam");
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}