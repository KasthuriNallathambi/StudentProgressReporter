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

	<form action="Attendance.jsp" method="get">
	
	<%
	session.setAttribute("filter", request.getParameter("filter"));
	%>

		<TABLE border="1">
			<c:forEach items="${report}" var="student">
				<tr>
					<td><c:out value="${student.name}" /></td>
					<td><c:out value="${student.rollNo}" /></td>
					<td><c:out value="${student.mailId}" /></td>
					<td><c:out value="${student.phoneNo}" /></td>
				</tr>
			</c:forEach>
		</TABLE>

		<input type="submit" value="Sumbit"> 
		<input type="button" value="SendReport" onclick="location.href = 'Settings.html';"> 
		<input type="button" value="SendReport" onclick="location.href = 'SendMail.jsp';"> 
		<input type="text" name="filter" value="60">
	</form>
</body>
</html>