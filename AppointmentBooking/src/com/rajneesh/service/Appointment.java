package com.rajneesh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.mysql.jdbc.PreparedStatement;
import com.rajneesh.dao.AppointmentDAO;
import com.rajneesh.model.Doctor;
import com.rajneesh.model.Patient;
import com.rajneesh.webService.FiveAppointment;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@WebServlet("/booking")
public class Appointment extends HttpServlet {

	public static final String BASE_URI = "http://localhost:9080/Appointmentbooking/rest/fiveAppointments/info";

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String pname = request.getParameter("pname");
		String date = request.getParameter("date");
		String docselect = request.getParameter("docselect");

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		

		Integer year = Integer.parseInt(date.substring(0, 4));
		Integer month = Integer.parseInt(date.substring(5, 7));
		Integer day = Integer.parseInt(date.substring(8, 10));

		date = new String();

		if (month < 10) {
			date = day + "-0" + month + "-" + year;
		}
		if (day < 10) {
			date = "0" + day + "-" + month + "-" + year;
		}
		if (month > 9 && day > 9) {
			date = day + "-" + month + "-" + year;
		}

		Date date2 = new Date();
		try {
			date2 = sdf.parse(date);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(sdf.format(date2));

		Date curdate = new Date();
		sdf.format(curdate);

		PrintWriter out = response.getWriter();

		if (!(date2.after(curdate) || (date2.getDate() == curdate.getDate() && date2.getMonth() == curdate.getMonth()
				&& date2.getYear() == curdate.getYear()))) {
			out.println("Invalid date selected");
		}

		else {

			AppointmentDAO dao = new AppointmentDAO();
			Connection connection = dao.getConnection();

			Client client = Client.create();
			WebResource webResource2 = client.resource(BASE_URI);
			ClientResponse response2 = webResource2.accept("application/xml").get(ClientResponse.class);
			if (response2.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
			}

			List<Doctor> doctor = response2.getEntity(new GenericType<List<Doctor>>() {
			});

			int flag = 0;

			for (Doctor x : doctor) {
				try {

					if (docselect.equals(x.getDocname())) {
						PreparedStatement stmt = (PreparedStatement) connection
								.prepareStatement("INSERT INTO Patient values(?,?,?,?)");

						Statement stmt1 = connection.createStatement();

						ResultSet rs = stmt1.executeQuery("SELECT * FROM Patient");
						int count = 0;
						while (rs.next()) {
							count++;
						}

						date = new String();

						if (month < 10) {
							date = year + "/0" + month + "/" + day;
						}
						if (day < 10) {
							date = year + "/" + month + "/0" + day;
						}
						if (month > 9 && day > 9) {
							date = year + "/" + month + "/" + day;
						}
						
						String docid = x.getDocid();
						stmt.setInt(1, (count + 1));
						stmt.setString(2, pname);
						stmt.setString(3, date);
						stmt.setString(4, docid);
						stmt.executeUpdate();

						flag = 1;

						out.print("<img src=\"thumbs_up.png\" alt=\"Successful\" style=\"width:150px\">");
						out.print("<h3>Your Appointment is fixed</h3>");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (flag == 0) {
				out.println("Doctor already has five appointments booked");
			}
		}
	}
}
