package com.rajneesh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppointmentDAO {

	Connection con;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "rockey123");
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
