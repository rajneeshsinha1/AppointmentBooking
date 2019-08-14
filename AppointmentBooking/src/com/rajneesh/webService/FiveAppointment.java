package com.rajneesh.webService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rajneesh.model.Doctor;
import com.rajneesh.model.Patient;

@Path("fiveAppointments")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class FiveAppointment {
	
	
	

	FiveAppointmentService ms = new FiveAppointmentService();
	
/*	@GET
	@Path("getstring")
	public String getString() {
		
		
		
		return "Hello Rajneesh";
		
	}
*/
	
	@GET
	@Path("info")
	public List<Doctor> getInfo() {

		ArrayList<Patient> p = (ArrayList<Patient>) ms.getPatients();
		ArrayList<Doctor> d = (ArrayList<Doctor>) ms.getDoctors();

		int[] count = new int[d.size() + 1];

		ArrayList<Doctor> dd = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		int i = 0;
		for (Doctor doctor : d) {
			i = i + 1;
			for (Patient patient : p) {
				if (doctor.getDocid().equals(patient.getDocid())) {

					Date date = patient.getDate();
					sdf.format(date);
					Date curdate = new Date();
					sdf.format(curdate);

					if (date.after(curdate) || (date.getDate() == curdate.getDate()
							&& date.getMonth() == curdate.getMonth() && date.getYear() == curdate.getYear())) {
						count[i]++;
					}
				}
			}
			if (count[i] < 5)
				dd.add(doctor);
			System.out.println(count[i]);
			System.out.println(dd);
		}

		return dd;
	}
}
