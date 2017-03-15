<%@page import="org.rec.studentdetails.Utils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.rec.studentdetails.pojo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload File Response</title>
</head>
<body>
	<%-- Using JSP EL to get message attribute value from request scope --%>
	<h2>${requestScope.message}</h2>

	<form action="AttendanceReportSender" method="get">

		<%
			String var = "pertg";
			request.setAttribute("pertg", Utils.getvalue("attendance_percantage"));
		%>

		
		<TABLE border="1">
			<tr>
				<td>Name</td>
				<td>Roll No</td>
				<td>Total working Days</td>
				<td>Total days present</td>
				<td>Attendance</td>
				<td>Mail ID</td>
				<td>Phone number</td>
			</tr>
			<c:forEach items="${attendance}" var="student">
				<tr>
					<td><c:out value="${student.name}" /></td>
					<td><c:out value="${student.rollNo}" /></td>
					<td><c:out value="${student.presents+student.absents}" /></td>
					<td><c:out value="${student.presents}" /></td>
					
					<td <c:if test="${student.attendancePercentage le pertg}">bgcolor="#FF0000"</c:if> >
					<c:out value="${student.attendancePercentage}" />%</td>
					
					<td><c:out value="${student.mailId}" /></td>
					<td><c:out value="${student.phoneNo}" /></td>
				</tr>
			</c:forEach>
		</TABLE>
		
		Send report as: SMS<input type="checkbox" name="SMS" value="SMS"> <br>
		Mail<input type="checkbox" name="Mail" value="Mail"> <br>
		<!-- Mail To	HOD<input type="checkbox" name="HOD" value="HOD"> <br> -->
		
		
		Faculty Mail <input type="text" name="facultyMail" value="sample@gmail.com"> <br>
		Department <input type="text" name="department" value="CSC"> <br>
		Year <input type="text" name="year" value="4"> <br>
		Semester <input type="text" name="semester" value="7"> <br>
		Section <input type="text" name="section" value=""> <br>
		<input type="submit" value="Sumbit">
		
		</form>
</body>
</html>