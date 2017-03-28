package org.rec.studentdetails.sender;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import org.rec.studentdetails.Utils;
import org.rec.studentdetails.pojo.CommonDetails;
import org.rec.studentdetails.pojo.Student;

public class SMSSender {

	public void sendMessage(CommonDetails commonDetails, List<Student> students, boolean isMarksheet) {
		boolean isSend = false;
		for (Student student : students) {
			try {
				if (student.getPhoneNo().length() == 10) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("REC Thandalam, ");
				//	buffer.append("department"+commonDetails.department);
					//buffer.append("Faculty"+commonDetails.faculty);
				//	buffer.append("Section"+commonDetails.section);
				//	buffer.append("Semester"+commonDetails.semester);
					//buffer.append("Subject"+commonDetails.subject);

					if (isMarksheet) {
						for (String subj : student.getSubjects().keySet()) {
							if (Integer.parseInt(student.getSubjects().get(subj)) < Integer.parseInt(Utils.getvalue("passmark"))) {
								isSend = true;
							}
							buffer.append(subj + "=" + student.getSubjects().get(subj) + ",");
						}
					} else {
						buffer.append("Total working days=" + (student.getPresents() + student.getAbsents()));
						buffer.append(",Total presents=" + student.getPresents());
						buffer.append(",Total absents=" + student.getAbsents());
						buffer.append(",Warning Count=" + student.getWarningCount());
						buffer.append(",Attendance Percentage=" + student.getAttendancePercentage() + "%");
						if (student.getAttendancePercentage() < Integer.parseInt(Utils.getvalue("attendance_percantage"))) {
							isSend = true;
						}
					}

					if (isSend) {
						URL url = new URL(Utils.getvalue("url")+"?username=" + Utils.getvalue("sms_username")
								+ "&hash=" + Utils.getvalue("sms_hashvalue") + "&numbers=+91" + student.getPhoneNo()
								+ "&sender=" + Utils.getvalue("sms_sender_phone") + "&message="
								+ URLEncoder.encode(buffer.toString(), "UTF-8"));

						System.out.println(url.toString());
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						if (conn.getResponseCode() != 200) {
							throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
						}
						System.out.println(conn.getResponseCode());
						conn.disconnect();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}