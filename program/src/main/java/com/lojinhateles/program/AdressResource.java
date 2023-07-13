package com.lojinhateles.program;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.guava.Lists;

import com.lojinhateles.program.dao.AdressDAO;
import com.lojinhateles.program.model.Adress;
import com.lojinhateles.program.service.ObjectService;

@Path("/adress")
public class AdressResource {
	private static ObjectService<Adress> getAdressDAO = new AdressDAO();
	private static Adress adress = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAll() {
		List<Adress> allAdress;
		GenericEntity<List<Adress>> entity;
		try {

			allAdress = getAdressDAO.getAll();
			if (allAdress.size() <= 0) {
				return Response.status(404).entity("Not Found").build();
			}
			entity = new GenericEntity<List<Adress>>(Lists.newArrayList(allAdress)) {
			};
			return Response.ok(entity).build();
		} catch (NullPointerException nulls) {
			Response.status(404).entity("Not Found").build();
		}
		return Response.status(500).entity("Not Working!").build();

	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(Adress adress) {
		try {
			adress = getAdressDAO.getById(adress.getCep());
			if (adress == null) {
				return Response.status(404).entity("Not Found").build();
			}
			getAdressDAO.update(adress);

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity("Its Work!").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		try {
			adress = getAdressDAO.getById(id);
			if (adress == null) {

				return Response.status(404).entity("Not Found").build();

			}
		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity(adress).build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN })
	@DELETE
	@Path("/delete")
	public Response delete(Adress adress) {
		try {
			getAdressDAO.delete(adress);
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity("OK!").build();
	}

	@Produces({ MediaType.TEXT_PLAIN })
	@DELETE
	@Path("/delete/{id}")
	public Response deleteById(@PathParam("id") int id) {
		try {
			getAdressDAO.deleteById(id);// retirar o supressed error
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity("OK!").build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN })
	@POST
	@Path("/save")
	public Response save(Adress adress) {
		try {
			getAdressDAO.save(adress);

		} catch (NullPointerException runtime) {
			return Response.status(500).entity("Not Working!").build();
		}

		return Response.status(200).entity("Its Work!").build();
	}

	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/total")
	public Response total() {
		int total = 0;
		try {
			total = getAdressDAO.total();
		} catch (NullPointerException runtime) {
			return Response.status(500).entity("Not Working!").build();
		}

		return Response.status(200).entity(total).build();
	}
}
