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

public class DoctorService {

	private static Map<Integer, Doctor> doctor = new HashMap<Integer, Doctor>();

	static {
		AppointmentDAO dao = new AppointmentDAO();
		Connection connection = dao.getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Doctor");
			int count = 0;
			while (rs.next()) {
				count++;
				Doctor doc = new Doctor();
				doc.setDocid(rs.getString(1));
				doc.setDocname(rs.getString(2));
				doctor.put(count, doc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Doctor getDoctor(String docid) {
		Collection<Doctor> values = doctor.values();
		Doctor d = null;
		int count = values.size();
		for (int i = 1; i <= count; i++) {
			d = new Doctor();
			d = doctor.get(i);

			if (docid.equals(d.getDocid())) {
				return d;
			}
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
