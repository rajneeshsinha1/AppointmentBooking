package com.rajneesh.webService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rajneesh.model.Doctor;
import com.rajneesh.model.Patient;

@Path("patients")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PatientResource {

	PatientService ms = new PatientService();

	@GET
	@Path("{pid}")
	public Patient getPatient(@PathParam("pid") int id) {

		return ms.getPatient(id);
	}

	@GET
	@Path("/")
	public List<Patient> getPatients() {

		return ms.getPatients();
	}
}
