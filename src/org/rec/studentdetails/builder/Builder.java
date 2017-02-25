package org.rec.studentdetails.builder;

import java.util.List;

import org.rec.studentdetails.pojo.Student;

public class Builder {
	List<Student> studentsDetails ;
	
	public List<Student> getStudentDetails(String fileName, Boolean isMarksheet) throws Exception {
		
		if (isMarksheet) {
			studentsDetails = new MarkSheetBuilder().getStudentDetails(fileName);
		} else {
			studentsDetails = new TimeTableBuilder().getStudentDetails(fileName);
		}
		return studentsDetails;
	}
}