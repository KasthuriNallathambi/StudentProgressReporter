package org.rec.studentdetails.sender;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import org.rec.studentdetails.Utils;
import org.rec.studentdetails.pojo.Student;

public class SMSSender {

	public void sendMessage(List<Student> students, boolean isMarksheet) {
		for (Student student : students) {
			try {
				if (student.getPhoneNo().length() == 10) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("RAC Thandalam, ");

					if (isMarksheet) {
						for (String subj : student.getSubjects().keySet()) {
							buffer.append(subj + "=" + student.getSubjects().get(subj) + ",");
						}
					} else {
						buffer.append("Total working days=" + (student.getPresents() + student.getAbsents()));
						buffer.append(",Total presents=" + student.getPresents());
						buffer.append(",Total absents=" + student.getAbsents());
						buffer.append(",Attendance Parcentage=" + student.getAttendancePercentage()+"%");
					}

					URL url = new URL("http://api.txtlocal.com/send/?username=" + Utils.getvalue("sms_username")
					+ "&hash=" + Utils.getvalue("sms_hashvalue") + "&numbers=+91" + student.getPhoneNo()
					+ "&sender=" + Utils.getvalue("sms_sender_phone") + "&message=" + URLEncoder.encode(buffer.toString(), "UTF-8"));

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}
					System.out.println(conn.getResponseCode());
					conn.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}