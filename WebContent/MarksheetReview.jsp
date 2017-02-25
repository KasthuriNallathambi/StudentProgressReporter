<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<TABLE border="1">
		<c:forEach items="${marksheet}" var="student">
			<tr>
				<td><c:out value="${student.name}" /></td>
				<td><c:out value="${student.rollNo}" /></td>
				
				<c:forEach items="${student.subjects}" var="subjects">
				<td><c:out value="${subjects.value}" /></td> 
				</c:forEach>
				
				
				<td><c:out value="${student.mailId}" /></td>
				<td><c:out value="${student.phoneNo}" /></td>
			</tr>
		</c:forEach>
	</TABLE>

	Send report as: 
	SMS<input type="checkbox" name="SMS" value="SMS">
	Mail<input type="checkbox" name="Mail" value="Mail">
	Mail To HOD<input type="checkbox" name="HOD" value="HOD">
	<input type="submit" value="Sumbit"> 

</form>
</body>
</html>