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
			try {
				Student student = new Student();
				student.setRollNo(studentDetails.get(i).get(1));
				student.setName(studentDetails.get(i).get(2));
				student.setMailId(studentDetails.get(i).get(3));
				student.setPhoneNo(studentDetails.get(i).get(4));
				
				Map<String, String> subjects = student.getSubjects();
				
				for(int columnCount=5; columnCount<studentDetails.get(0).size(); columnCount++){
					subjects.put(studentDetails.get(0).get(columnCount), studentDetails.get(i).get(columnCount));
					checkStatus(studentDetails.get(0).get(columnCount), studentDetails.get(i).get(columnCount));
				}
				students.add(student);
				Student.totalStudents++;				
			} catch (Exception e) {
			}
		}
		return students;
	}
	
	private void checkStatus(String subject, String mark) {
		if (Integer.parseInt(mark) < Integer.parseInt(Utils.getvalue("passmark"))) {
			if(Student.subjectResults.containsKey(subject)){
				Student.subjectResults.put(subject, Student.subjectResults.get(subject)+1);
			} else {
				Student.subjectResults.put(subject, 1);	
			}
		}
	}
}