<%@page import="org.rec.studentdetails.Utils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.rec.studentdetails.pojo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<script type="text/javascript">
		function checkInternet() {
			if (!navigator.onLine) {
				alert('Please check your internet connection!');
			} else {
				document.getElementById("Button").disabled = true;
				document.getElementById("myForm").submit();
			}
		}
	</script>
	<form id="myForm" action="MarksheetReportSender" method="get">

		<%
			String var = "pertg";
			request.setAttribute("passmark", Utils.getvalue("passmark"));
		%>

		<table>
			<tr>
				<td>
					<TABLE border="1" style="font-family: Georgia, Garamond, Serif; color: white;display: block; height: 450px; overflow-y: auto">
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
									<td
										<c:if test="${subjects.value le passmark}">bgcolor="#FF0000"</c:if>>

										<c:out value="${subjects.value}" />
									</td>
								</c:forEach>
								<td><c:out value="${student.mailId}" /></td>
								<td><c:out value="${student.phoneNo}" /></td>
							</tr>
						</c:forEach>
					</TABLE>
				</td>

				<td
					style="vertical-align: top; text-align: left;" >
					<div>
						<label>Enter the Details</label>
						<!--  Department <input type="text" name="department" value=""> <br>-->
						<label>Year</label> <input type="text" name="year" value="">
						<br> <label>Semester</label> <input type="text"
							name="semester" value=""> <br> <label>Section</label>
						<input type="text" name="section" value=""> <br> <label>SMS</label>
						<input type="checkbox" name="SMS"
							value="SMS"> <br> <label >Mail</label><input
							type="checkbox" name="Mail" value="Mail"> <br>
						<!-- Mail To	HOD<input type="checkbox" name="HOD" value="HOD"> <br> -->
						<label>Faculty Mail</label> <input
							type="text" name="faculty" value="kasthudheiva@gmail.com">
						<br>
						<button id="Button" onclick="checkInternet()">Submit</button>
					</div>
				<td>
			</tr>
		</table>
		<!-- 
		Send report as: SMS<input type="checkbox" name="SMS" value="SMS"> <br>
		Mail<input type="checkbox" name="Mail" value="Mail"> <br>
		Mail To	HOD<input type="checkbox" name="HOD" value="HOD"> <br>
		
		
		Faculty Mail <input type="text" name="facultyMail" value="sample@gmail.com"> <br>
		Department <input type="text" name="department" value="CSC"> <br>
		Year <input type="text" name="year" value="4"> <br>
		Semester <input type="text" name="semester" value="7"> <br>
		Section <input type="text" name="section" value=""> <br>
		Subject <input type="text" name="subject" value="OOAD"> <br>
		Faculty <input type="text" name="faculty" value="sample@gmail.com"> <br>	
		<input type="submit" value="Sumbit"> -->

	</form>
</body>
</html>