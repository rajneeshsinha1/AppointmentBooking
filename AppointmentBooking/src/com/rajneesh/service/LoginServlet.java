package com.rajneesh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rajneesh.dao.AppointmentDAO;
import com.rajneesh.model.Doctor;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String docid = request.getParameter("docid");
		String docname = request.getParameter("docname");

		PrintWriter out = response.getWriter();

		AppointmentDAO dao = new AppointmentDAO();
		Connection connection = dao.getConnection();

		try {

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM DOCTOR");

			while (rs.next()) {
				Doctor doc = new Doctor();
				doc.setDocid(rs.getString(1));
				doc.setDocname(rs.getString(2));
				if (doc.getDocid().equals(docid)) {
					if (doc.getDocname().equals(docname)) {

						HttpSession session = request.getSession();
						session.setAttribute("docid", docid);

						// request.setAttribute("did", docid);
						RequestDispatcher rd = request.getRequestDispatcher("Success.html");
						rd.forward(request, response);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("doctorLogin.jsp");
		HttpSession session = request.getSession();
		request.setAttribute("error", "Invalid Username or Password");
		rd.forward(request, response);

	}

}
