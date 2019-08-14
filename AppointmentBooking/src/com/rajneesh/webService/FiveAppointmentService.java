package com.rajneesh.webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rajneesh.dao.AppointmentDAO;
import com.rajneesh.model.Doctor;
import com.rajneesh.model.Patient;



public class FiveAppointmentService {

	private static Map<Integer, Patient> patient = new HashMap<Integer, Patient>();
	private static Map<Integer, Doctor> doctor = new HashMap<Integer, Doctor>();

	{
		AppointmentDAO dao = new AppointmentDAO();
		Connection connection = dao.getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Patient");
			int count = 0;
			while (rs.next()) {
				count++;
				Patient pac = new Patient();
				pac.setPid(rs.getInt(1));
				pac.setPname(rs.getString(2));
				pac.setDate(rs.getDate(3));
				pac.setDocid(rs.getString(4));
				patient.put(count, pac);
			}

			ResultSet rs2 = stmt.executeQuery("SELECT * FROM Doctor");
			int count2 = 0;
			while (rs2.next()) {
				count2++;
				Doctor doc = new Doctor();
				doc.setDocid(rs2.getString(1));
				doc.setDocname(rs2.getString(2));
				doctor.put(count2, doc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Patient> getPatients() {

		int count = patient.size();
		ArrayList<Patient> d = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			d.add(patient.get(i));
		}
		return d;
	}

	public List<Doctor> getDoctors() {

		int count = doctor.size();
		ArrayList<Doctor> d = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			d.add(doctor.get(i));
		}
		return d;
	}

}
