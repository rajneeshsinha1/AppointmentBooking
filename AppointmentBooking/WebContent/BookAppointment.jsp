
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appointment</title>
<link rel="stylesheet" type="text/css" href="look.css" />
</head>
<body>

	<h1 style="text-decoration: underline;">
		<b><i>Book Appointment</i></b>
	</h1>
	<div>
		<form action="booking" method="post">

			<table>

				<tr>
					<td>Patient Name :</td>
					<td><input type="text" maxlength="20" required="required"
						name="pname"></td>
				</tr>
				<tr>
					<td>Appointment Date :</td>
					<td><input type="date" required="required" name="date"
						id="txtdate"></td>
				</tr>
	<tr>
					<td>Doctor :</td>
					<td><select name="docselect">

							<%@ page import="com.sun.jersey.api.client.*"%>
							<%@ page import="java.util.*"%>
							<%@ page import="com.rajneesh.model.Doctor"%>

							<% 
							{
							final String BASE_URI = "http://localhost:9080/Appointmentbooking/rest/fiveAppointments/info"; 
							Client client = Client.create();
							WebResource webResource2 = client.resource(BASE_URI);
							ClientResponse response2 = webResource2.accept("application/xml").get(ClientResponse.class);
							if (response2.getStatus() != 200) {
								throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
							}

							List<Doctor> doctor = response2.getEntity(new GenericType<List<Doctor>>() {});
							
							for(Doctor d : doctor)
							{
								%>
							<option><%= d.getDocname()
									%></option>
							<%
							}
							}
						%>
					</select></td>
				</tr>  
				<tr>
					<td colspan="2"><input type="submit" name="submit"
						value="Book appointment"></td>
				</tr>
			</table>

		</form>
	</div>

</body>
</html>