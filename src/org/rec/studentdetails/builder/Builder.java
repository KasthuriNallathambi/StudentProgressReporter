package org.rec.studentdetails.builder;

import java.util.List;

import org.rec.studentdetails.builder.report.ReportGenerator;
import org.rec.studentdetails.pojo.Student;

public class Builder {
	List<Student> studentsDetails ;
	
	public List<Student> getStudentDetails(String fileName) throws Exception {
		List<Student> list ;
		ReportGenerator generator = new ReportGenerator(studentsDetails);
		
		if (fileName.contains("time")) {
			studentsDetails = new MarkSheetBuilder().getStudentDetails(fileName);
			list = generator.getTimeTableResults();
		} else {
			studentsDetails = new TimeTableBuilder().getStudentDetails(fileName);
			list = generator.getMarkSheetResults();
		}
		return list;
	}
}