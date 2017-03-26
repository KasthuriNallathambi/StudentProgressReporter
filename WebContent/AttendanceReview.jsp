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
<style>
body {
	background-color: #262626;
}

form {
	background: #000;
	padding: 2em 1em;
	font-family: helvetica, sans-serif;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	text-align: left;
}

div {
	margin-top: -19px;
	border: black;
	padding: 25px;
	background: black margin: 25px;
}

label {
	color: #fff;
	margin: 1.0em 3% 0;
	display: block;
	text-align: left;
	font-family: helvetica, sans-serif;
	width: 100%;
}

input[type=text] {
	width: 118%;
	padding: .5em .25em; <!--
	margin: 0 3% 1em; -->
	font-size: 1.2em;
	border: 2px solid #000;
	outline: none;
	webkit-transition: all 0.25s;
	-moz-transition: all 0.25s;
	-ms-transition: all 0.25s;
	-o-transition: all 0.25s;
	transition: all 0.25s;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

input[type=text]:focus {
	border: 2px solid #1fd100;
}

button {
	width: 112%;
	margin: 1.5em 3% 0;
	border: none;
	background: #1fd100;
	padding: .65em 0;
	font-size: 1.3em;
	clear: both;
	color: #000;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	outline: none;
	-webkit-transition: all 0.25s;
	-moz-transition: all 0.25s;
	-ms-transition: all 0.25s;
	-o-transition: all 0.25s;
	transition: all 0.25s;
	border-radius: 5px;
	cursor: pointer;
}

h2 {
	color: white;
	font-family: helvetica, sans-serif;
	margin-left: 440px;
	margin-top: 10px;
}
</style>
</head>
<body>
	<form action="AttendanceReportSender" method="get">
		<%
			String var = "pertg";
			request.setAttribute("pertg", Utils.getvalue("attendance_percantage"));
		%>
<h1 style="font-family: Georgia, Garamond, Serif; color: white;" align="center">Rajalakshmi engineering college</h1>
		<table align="center" width="100%">
			<tr>
				<td>
					<table border="1" 
						style="font-family: Georgia, Garamond, Serif; color: white;display: block; height: 450px; overflow-y: auto">
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
								<td
									<c:if test="${student.attendancePercentage le pertg}">bgcolor="#FF0000"</c:if>>
									<c:out value="${student.attendancePercentage}" />%
								</td>
								<td><c:out value="${student.mailId}" /></td>
								<td><c:out value="${student.phoneNo}" /></td>
							</tr>
						</c:forEach>
					</table>
				</td>

				<td  style="vertical-align: top;  text-align: left; background-color: #123626;" >
					<div>
						<label>Enter the Details</label>
						<!--  Department <input type="text" name="department" value=""> <br>-->
						<label>Year</label> <input type="text" name="year" value="">
						<br> <label>Semester</label> <input type="text"
							name="semester" value=""> <br> <label>Section</label>
						<input type="text" name="section" value=""> <br> <label
							name="smslabel">SMS</label><input type="checkbox" name="SMS"
							value="SMS"> <br> <label name="maillabel">Mail</label><input
							type="checkbox" name="Mail" value="Mail"> <br>
						<!-- Mail To	HOD<input type="checkbox" name="HOD" value="HOD"> <br> -->
						<label name="facultymaillablel">Faculty Mail</label> <input
							type="text" name="faculty" value="kasthudheiva@gmail.com">
						<br>
						<button type="submit">Sumbit</button>
					</div>
				<td>
			</tr>
		</table>

		<!-- Send report as: SMS<input type="checkbox" name="SMS" value="SMS"> <br>
		Mail<input type="checkbox" name="Mail" value="Mail"> <br>
		Mail To	HOD<input type="checkbox" name="HOD" value="HOD"> <br>
		
		
		Faculty Mail <input type="text" name="facultyMail" value="sample@gmail.com"> <br>
		Department <input type="text" name="department" value="CSC"> <br>
		Year <input type="text" name="year" value="4"> <br>
		Slot <input type="text" name="slot" value="2"> <br>
		Semester <input type="text" name="semester" value="7"> <br>
		Section <input type="text" name="section" value=""> <br>
		<input type="submit" value="Sumbit"> -->

	</form>
</body>
</html>