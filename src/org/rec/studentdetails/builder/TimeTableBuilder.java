package org.rec.studentdetails.builder;

import java.util.ArrayList;
import java.util.List;

import org.rec.studentdetails.Utils;
import org.rec.studentdetails.pojo.Student;

public class TimeTableBuilder {
	public List<Student> getStudentDetails(String filename) throws Exception {
		List<Student> students = new ArrayList<>();
		List<List<String>> studentDetails = Utils.getStudentDetails(filename);
		
		for (int i = 6; i < studentDetails.size() - 1; i++) {
			if (studentDetails.get(i).size() > 5) {
				Student student = new Student();
				student.setRollNo(studentDetails.get(i).get(1));
				student.setName(studentDetails.get(i).get(2));
				student.setPresents((int) Float.parseFloat(studentDetails.get(i).get(3)));
				student.setAbsents((int) Float.parseFloat(studentDetails.get(i).get(4)));
				students.add(student);
			}
		}
		return students;
	}
}