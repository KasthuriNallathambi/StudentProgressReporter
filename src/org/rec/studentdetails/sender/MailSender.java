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
import org.rec.studentdetails.pojo.CommonDetails;
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
	
	public void sendMails(CommonDetails commonDetails, List<Student> students, boolean ismarksheet) {
		
		for (Student student : students) {
			boolean isSend = false;
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(Utils.getvalue("email_username")));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getMailId()));
				message.setSubject(Utils.getvalue("email_subject"));
				
				if (ismarksheet) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("Dear Parent, Please find your ward mark details below\n\n\n");
				//	buffer.append("department : "+commonDetails.department);
				//	buffer.append("\nFaculty : "+commonDetails.faculty);
				//	buffer.append("\nSection : "+commonDetails.section);
				//	buffer.append("\nSemester : "+commonDetails.semester);
				//	buffer.append("\nSubject : "+commonDetails.subject);

					for (String subj : student.getSubjects().keySet()) {
						buffer.append(subj + " : " + student.getSubjects().get(subj) + "\n");
						if(Integer.parseInt(student.getSubjects().get(subj)) < Integer.parseInt(Utils.getvalue("passmark"))){
							isSend = true;
							
						}
					}
					message.setText(buffer.toString());
				} else {
					
					if(student.getAttendancePercentage() < Integer.parseInt(Utils.getvalue("attendance_percantage"))){
						message.setText("Dear Parent, Please find your ward attendance details below\n\n\n" + "Total days : "
								+ (student.getAbsents()+student.getPresents()) + "\n No. of days Present : " + student.getPresents()
								+ "\n No. of days Absent : " + student.getAbsents() + "\n Warnings : " + student.getWarningCount()
								+ " \nThanks\n REC Thandalam");
						isSend = true;
					}
					
					/*message.setText("Dear Parent, Please find your ward attendance details below\n\n\n" + "Total days : "
							+ (student.getAbsents()+student.getPresents()) + "\n No. of days Present : " + student.getPresents()
							+ "\n No. of days Absent : " + student.getAbsents() + "\n Warnings : " + student.getWarningCount()
							+ " \nThanks\n REC Thandalam");*/
				}

				if(isSend) {
					System.out.println("mail send to : - "+student.getMailId());					
					Transport.send(message);
				}
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void sendMail(CommonDetails commonDetails, List<Student> students,String mailId, boolean isMarkSheet) {
		try {
			boolean isSet = false;
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Utils.getvalue("email_username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailId));
			message.setSubject(Utils.getvalue("email_subject"));
			StringBuffer buffer = new StringBuffer();
			buffer.append("<html><table border=\"1\">");
			for (Student student : students) {
				if (isMarkSheet) {

					if(!isSet){						
						buffer.append("<tr>");
						buffer.append("<td>Name</td>");
						buffer.append("<td>RollNo</td>");
						for (String subj : student.getSubjects().keySet()) {
							buffer.append("<td>" + subj + "</td>");
						}
						buffer.append("</tr>");
						isSet = true;
					}

					boolean isSend = false;
					StringBuffer buffer1 = new StringBuffer();
					buffer1.append("<tr>");
					buffer1.append("<td>" + student.getName() + "</td>");
					buffer1.append("<td>" + student.getRollNo() + "</td>");
					for (String subj : student.getSubjects().keySet()) {
						buffer1.append("<td>" + student.getSubjects().get(subj) + "</td>");
						if(Integer.parseInt(student.getSubjects().get(subj)) < Integer.parseInt(Utils.getvalue("passmark"))){
							isSend = true;
						}
					}
					buffer1.append("</tr>");
					
					if(isSend)
						buffer.append(buffer1);
					
				} else {
					if(!isSet){
						buffer.append("<tr>");
						buffer.append("<td>Name</td>");
						buffer.append("<td>RollNo</td>");
						buffer.append("<td>Absents</td>");
						buffer.append("<td>Presents</td>");
						buffer.append("<td>Warnings</td>");
						buffer.append("<tr>");
						isSet = true;
					}
					
					if(student.getAttendancePercentage() < Integer.parseInt(Utils.getvalue("attendance_percantage"))){
						buffer.append("<td>" + student.getName() + "</td>");
						buffer.append("<td>" + student.getRollNo() + "</td>");
						buffer.append("<td>" + student.getAbsents() + "</td>");
						buffer.append("<td>" + student.getPresents() + "</td>");
						buffer.append("<td>" + student.getWarningCount() + "</td>");
						buffer.append("</tr>");						
					}
				}
			}
			buffer.append("</table></html>");
//			buffer.append("Department : "+commonDetails.department);
			if (!isMarkSheet) 
				buffer.append("\nSlot:"+commonDetails.slot);
			buffer.append("\nYear : "+commonDetails.year);
			buffer.append("\nSemester : "+commonDetails.semester);
			buffer.append("\nSeection : "+commonDetails.section);
			message.setContent(buffer.toString(), "text/html; charset=utf-8");
			
			System.out.println("mail send to : - "+mailId);
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}