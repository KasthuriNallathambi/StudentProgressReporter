package org.rec.studentdetails;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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
	
	public static String getvalue(String key){
		Properties properties = new Properties();
		String value = null;
		try {
			InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("org/rec/studentdetails/config/config.properties");
			properties.load(inStream);
			value =(String) properties.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void main(String args[]){
		getvalue("");
	}
	
}
