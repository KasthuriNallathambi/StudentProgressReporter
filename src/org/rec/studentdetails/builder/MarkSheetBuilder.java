package org.rec.studentdetails.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rec.studentdetails.Utils;
import org.rec.studentdetails.pojo.Student;

public class MarkSheetBuilder {

	public List<Student> getStudentDetails(String filename) throws Exception {
		List<Student> students = new ArrayList<>();
		List<List<String>> studentDetails = Utils.getStudentDetails(filename);

		for (int i = 1; i < studentDetails.size(); i++) {
			Student student = new Student();
			student.setRollNo(studentDetails.get(i).get(1));
			student.setName(studentDetails.get(i).get(2));
			student.setMailId(studentDetails.get(i).get(3));
			student.setPhoneNo(studentDetails.get(i).get(4));

			Map<String, String> subjects = student.getSubjects();
			subjects.put(studentDetails.get(0).get(5), studentDetails.get(i).get(5));
			subjects.put(studentDetails.get(0).get(6), studentDetails.get(i).get(6));
			subjects.put(studentDetails.get(0).get(7), studentDetails.get(i).get(7));
			subjects.put(studentDetails.get(0).get(8), studentDetails.get(i).get(8));
			subjects.put(studentDetails.get(0).get(9), studentDetails.get(i).get(9));
			students.add(student);
		}
		return students;
	}
}