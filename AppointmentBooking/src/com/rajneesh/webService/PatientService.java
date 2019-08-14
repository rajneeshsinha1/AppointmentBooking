package com.rajneesh.webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.ServiceMode;

import com.rajneesh.dao.AppointmentDAO;
import com.rajneesh.model.Doctor;
import com.rajneesh.model.Patient;

public class PatientService {

	private static Map<Integer, Patient> patient = new HashMap<Integer, Patient>();

	static {
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
				pac.setDocid(rs.getString(2));
				pac.setPname(rs.getString(3));
				pac.setDate(rs.getDate(4));
				patient.put(count, pac);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Patient getPatient(int pid) {
		Collection<Patient> values = patient.values();
		Patient d = null;
		int count = values.size();
		for (int i = 1; i <= count; i++) {
			d = new Patient();
			d = patient.get(i);

			if (pid == d.getPid()) {
				return d;
			}
		}
		return d;
	}

	public List<Patient> getPatients() {

		int count = patient.size();
		ArrayList<Patient> d = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			d.add(patient.get(i));
		}
		return d;
	}
}
