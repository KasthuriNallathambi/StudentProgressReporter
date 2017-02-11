<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form action="SettingsServlet" method="post">

		<table>
			<tr>
				<td>HOD Mail ID</td>
				<td><input type="text" name="hod_MailId"></td>
			</tr>

			<tr>
				<td>Mail Sender Username</td>
				<td><input type="text" name="mail_sender_mailId"></td>
			</tr>

			<tr>
				<td>Mail Sender password</td>
				<td><input type="password" name="mail_sender_password"></td>
			</tr>

			<tr>
				<td><a href="">Clickhere to eneable settings for mail</a></td>
			</tr>

		</table>

		<input type="submit" value="Send SMS" />
		
	</form>

</body>
</html>