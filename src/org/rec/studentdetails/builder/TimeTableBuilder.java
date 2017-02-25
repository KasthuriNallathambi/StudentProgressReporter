package org.rec.studentdetails.builder;

import java.util.ArrayList;
import java.util.List;

import org.rec.studentdetails.Utils;
import org.rec.studentdetails.pojo.Student;

public class TimeTableBuilder {
	public List<Student> getStudentDetails(String filename) throws Exception {
		List<Student> students = new ArrayList<>();
		List<List<String>> studentDetails = Utils.getStudentDetails(filename);
		
		for (int i = 6; i < studentDetails.size(); i++) {
			if (studentDetails.get(i).size() > 5) {
				
				float present = Float.parseFloat(studentDetails.get(i).get(3));
				float absent = Float.parseFloat(studentDetails.get(i).get(4));
				float  parcentage =  (present/(present +absent))*100;
				
				Student student = new Student();
				student.setRollNo(studentDetails.get(i).get(1));
				student.setName(studentDetails.get(i).get(2));
				student.setPresents(Float.parseFloat(studentDetails.get(i).get(3)));
				student.setAbsents(Float.parseFloat(studentDetails.get(i).get(4)));
				student.setPhoneNo(studentDetails.get(i).get(7));
				student.setMailId(studentDetails.get(i).get(8));
				
				student.setAttendancePercentage((int)parcentage);
				students.add(student);
				Student.totalStudents++;
			}
		}
		return students;
	}
}