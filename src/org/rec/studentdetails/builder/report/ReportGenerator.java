package org.rec.studentdetails.builder.report;

import java.util.List;
import org.rec.studentdetails.pojo.Student;

public class ReportGenerator {
	List<Student> studentsDetails;
	
	
	public ReportGenerator(List<Student> studentsDetails) {
		this.studentsDetails = studentsDetails;
	}

	public List<Student> getTimeTableResults() {
		return null;
	}

	public List<Student> getMarkSheetResults() {
		for(Student student : studentsDetails){
			//Map<String, Integer> subs = student.getSubjects();
			
		}
		return null;
	}
}