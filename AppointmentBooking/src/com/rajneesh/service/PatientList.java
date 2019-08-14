package com.rajneesh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;
import com.rajneesh.dao.AppointmentDAO;
import com.rajneesh.model.Doctor;
import com.rajneesh.model.Patient;

@WebServlet("/patientList")
public class PatientList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		HttpSession session = request.getSession();

		String docid = (String) session.getAttribute("docid");

		PrintWriter out = response.getWriter();
		AppointmentDAO dao = new AppointmentDAO();
		Connection connection = dao.getConnection();

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("SELECT * FROM PATIENT WHERE docid = ?");

			stmt.setString(1, docid);

			ResultSet rs = stmt.executeQuery();

			out.print("<table border=\"2\" name=\"patientList\">");
			out.print(
					"<tr><th>Patient ID</th> <th>Doctor ID</th> <th>Patient Name</th> <th>Date of appointment</th></tr>");

			while (rs.next()) {
				Patient p = new Patient();
				p.setPid(rs.getInt(1));
				p.setDocid(rs.getString(4));
				p.setPname(rs.getString(2));
				p.setDate(rs.getDate(3));
				Date date = p.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
				sdf.format(date);

				Date curdate = new Date();
				sdf.format(curdate);

				if (date.after(curdate) || (date.getDate() == curdate.getDate() && date.getMonth() == curdate.getMonth()
						&& date.getYear() == curdate.getYear())) {
					out.print("<tr><td>" + p.getPid() + "</td> <td>" + p.getDocid() + "</td> <td>" + p.getPname()
							+ "</td> <td>" + p.getDate() + "</td></tr>");
				}

			}

			out.print("</table>");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.print("<a href=\"doctorLogin.jsp\">Logout</a>");
	}

}
