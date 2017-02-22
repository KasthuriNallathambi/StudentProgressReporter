package org.rec.studentdetails.pojo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
	private Map<String,String> subjects = new LinkedHashMap<>();
	private String name;
	private String rollNo;
	private String mailId;
	private String phoneNo;
	private int Presents;
	private int Absents;
	
	public Map<String, String> getSubjects() {
		return subjects;
	}
	public void setSubjects(Map<String, String> subjects) {
		this.subjects = subjects;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getPresents() {
		return Presents;
	}
	public void setPresents(int presents) {
		Presents = presents;
	}
	public int getAbsents() {
		return Absents;
	}
	public void setAbsents(int absents) {
		Absents = absents;
	}
}