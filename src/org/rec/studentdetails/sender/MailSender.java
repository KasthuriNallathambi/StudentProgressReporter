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
	
	public void sendMails(List<Student> students, boolean ismarksheet) {
		boolean isSend = false;
		
		for (Student student : students) {
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(Utils.getvalue("email_username")));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getMailId()));
				message.setSubject(Utils.getvalue("email_subject"));

				if (ismarksheet) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("Dear Parent, Please fine your son mark details below\n\n\n");

					for (String subj : student.getSubjects().keySet()) {
						buffer.append(subj + " : " + student.getSubjects().get(subj) + "\n");
						if(Integer.parseInt(student.getSubjects().get(subj)) < Integer.parseInt(Utils.getvalue("passmark"))){
							isSend = true;
						}
					}
					message.setText(buffer.toString());
				} else {
					
					if(student.getAttendancePercentage() < Integer.parseInt(Utils.getvalue("attendance_percantage"))){
						isSend = true;
					}
					
					message.setText("Dear Parent, Please fine your son attendance details below\n\n\n" + "Total days : "
							+ student.getAbsents() + "\n No. of days Present : " + student.getPresents()
							+ "\n No. of days Absent : " + student.getAbsents() + " \nThanks\n RAC Thandalam");
				}

				if(isSend)
					Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void sendMailtoHOD(List<Student> students, boolean isMarkSheet) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Utils.getvalue("email_username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Utils.getvalue("hod_mail_id")));
			message.setSubject(Utils.getvalue("email_subject"));
			StringBuffer buffer = new StringBuffer();
			buffer.append("<html><table border=\"1\">");
			for (Student student : students) {
				if (isMarkSheet) {
					buffer.append("<tr>");
					buffer.append("<td>" + student.getName() + "</td>");
					buffer.append("<td>" + student.getRollNo() + "</td>");
					for (String subj : student.getSubjects().keySet()) {
						buffer.append("<td>" + student.getSubjects().get(subj) + "</td>");
					}
					buffer.append("</tr>");
				} else {
					buffer.append("<tr>");
					buffer.append("<td>" + student.getName() + "</td>");
					buffer.append("<td>" + student.getRollNo() + "</td>");
					buffer.append("<td>" + student.getAbsents() + "</td>");
					buffer.append("<td>" + student.getPresents() + "</td>");
					buffer.append("</tr>");
				}
			}
			buffer.append("</table></html>");
			message.setContent(buffer.toString(), "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}