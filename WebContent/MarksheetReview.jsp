<%@page import="org.rec.studentdetails.Utils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.rec.studentdetails.pojo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload File Response</title>
</head>
<body>

	<form action="MarksheetReportSender" method="get">

		<%
			String var = "pertg";
			request.setAttribute("passmark", Utils.getvalue("passmark"));
		%>

		<TABLE border="1">

			<tr>
				<td>Name</td>
				<td>Roll No</td>
				
				<c:forEach items="${marksheet[0].subjects}" var="subjects">
						<td><c:out value="${subjects.key}" /></td>
				</c:forEach>
				
				<td>Mail ID</td>
				<td>Phone No</td>
			</tr>

			<c:forEach items="${marksheet}" var="student">
				<tr>
					<td><c:out value="${student.name}" /></td>
					<td><c:out value="${student.rollNo}" /></td>

					<c:forEach items="${student.subjects}" var="subjects">
						<td <c:if test="${subjects.value le passmark}">bgcolor="#FF0000"</c:if> >
						
						<c:out value="${subjects.value}" /></td>
					</c:forEach>

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
		Subject <input type="text" name="subject" value="OOAD"> <br>
		Faculty <input type="text" name="faculty" value="sample@gmail.com"> <br>	
		<input type="submit" value="Sumbit">

	</form>
</body>
</html>