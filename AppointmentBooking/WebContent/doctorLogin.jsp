<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments</title>
<link rel="stylesheet" type="text/css" href="look.css" />
</head>
<body>

	<h1 style="text-decoration: underline;">
		<i><b>Doctor Login</b></i>
	</h1>

	<div>
		<form action="login" method="post">

			<table>

				<tr>
					<td>Doctor ID :</td>
					<td><input type="text" maxlength="20" required="required"
						name="docid"></td>
				</tr>
				<tr>
					<td>Doctor Name :</td>
					<td><input type="text" maxlength="20" required="required"
						name="docname"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="submit"
						value="Login"></td>
				</tr>

			</table>

		</form>
	</div>
	
	<%
	
		session.invalidate();
		String login_msg = (String)request.getAttribute("error");
		if (login_msg != null)
			out.println("<font color=red size=4px>" + login_msg + "</font>");
	%>

</body>
</html>