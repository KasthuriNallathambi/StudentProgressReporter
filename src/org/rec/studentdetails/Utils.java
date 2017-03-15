package org.rec.studentdetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.rec.studentdetails.reader.XLSReader;
import org.rec.studentdetails.reader.XLSXReader;

public class Utils {
	@SuppressWarnings("unchecked")
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
	
	public static Properties getAttendanceProperty(){
		Properties properties = new Properties();
		try {
			InputStream inStream = new FileInputStream(new File("AttendanceReport.properties"));
			properties.load(inStream);
		} catch (Exception e) {
			OutputStream output;
			try {
				output = new FileOutputStream("AttendanceReport.properties");
				properties = new Properties();
				properties.store(output, null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return properties;
		}
		return properties;
	}
	
	public static void updateProperties(Properties prop){
		OutputStream output = null;
		try {
			output = new FileOutputStream("AttendanceReport.properties");
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Properties properties = getAttendanceProperty();
		properties.setProperty("raja", "343");
		updateProperties(properties);
	}
}