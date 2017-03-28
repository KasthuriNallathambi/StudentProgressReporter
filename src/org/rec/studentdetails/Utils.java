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

import com.sun.org.apache.regexp.internal.recompile;

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
	
/*	public static Properties getAttendanceProperty(String slot){
		
		int slotNo = Integer.parseInt(slot);
		
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
	}*/
	
	public static Properties createAttendanceProperty(String filename){
		OutputStream output;
		Properties properties = new Properties();
		try {
			output = new FileOutputStream(filename);
			properties = new Properties();
			properties.store(output, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Properties getAttendanceProperty(String filename){
		Properties properties = new Properties();
		try {
			InputStream inStream = new FileInputStream(new File(filename));
			properties.load(inStream);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static void updateProperties(Properties prop,String filename){
		OutputStream output = null;
		try {
			output = new FileOutputStream(filename);
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/*public static void main(String args[]){
		createAttendanceProperty("zfszs.properties");
		System.out.println("dvdv");
	}*/
}