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

@Path("doctors")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class DoctorResource {

	DoctorService ms = new DoctorService();

	@GET
	@Path("{docid}")
	public Doctor getDoctor(@PathParam("docid") String id) {

		return ms.getDoctor(id);
	}

	@GET
	@Path("/")
	public List<Doctor> getDoctors() {

		return ms.getDoctors();
	}
}
