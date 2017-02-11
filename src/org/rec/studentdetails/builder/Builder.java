package org.rec.studentdetails.builder;

import java.util.List;

import org.rec.studentdetails.pojo.Student;

public class Builder {
	List<Student> studentsDetails ;
	
	public List<Student> getStudentDetails(String fileName) throws Exception {
		
		if (fileName.contains("time")) {
			studentsDetails = new TimeTableBuilder().getStudentDetails(fileName);
		} else {
			studentsDetails = new MarkSheetBuilder().getStudentDetails(fileName);
		}
		return studentsDetails;
	}
}