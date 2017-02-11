package org.rec.studentdetails;

import java.util.List;

import org.rec.studentdetails.reader.XLSReader;
import org.rec.studentdetails.reader.XLSXReader;

public class Utils {
	public static List<List<String>> getStudentDetails(String fileName) throws Exception {
		String fileExtension = fileName.split("\\.")[fileName.split("\\.").length - 1];
		List<List<String>> studentDetails = null;

		if ("xls".equals(fileExtension)) {
			studentDetails = new XLSReader().read(fileName);
		} else if ("xlsx".equals(fileExtension)) {
			studentDetails = new XLSXReader().read(fileName);
		} else {
			System.out.println("");
		}
		return studentDetails;
	}
}
