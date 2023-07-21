package com.lojinhateles.program;

import java.util.List;
import java.util.stream.Collectors;

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

import com.lojinhateles.program.dao.ConsumerDAO;
import com.lojinhateles.program.dto.ConsumerDTO;
import com.lojinhateles.program.model.Consumer;
import com.lojinhateles.program.service.ObjectService;

@Path("/consumer")
public class ConsumerResource {
	private static ObjectService<Consumer> getConsumerDAO = new ConsumerDAO();
	private static Consumer consumer = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAll() {
		List<Consumer> allConsumer;
		List<ConsumerDTO> allConsumerDTO;
		GenericEntity<List<ConsumerDTO>> entity;
		try {

			allConsumer = getConsumerDAO.getAll();
			allConsumerDTO = allConsumer.stream().map(x-> new ConsumerDTO(x)).collect(Collectors.toList());
			if(allConsumer.size() <= 0) {
				return Response.status(404).entity("Not Found").build();
			}
			entity = new GenericEntity<List<ConsumerDTO>>(Lists.newArrayList(allConsumerDTO)) {
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
	public Response update(Consumer cons) {
		try {
			consumer = getConsumerDAO.getById(cons.getId());
			if (consumer == null) {
				return Response.status(404).entity("Not Found").build();
			}
			getConsumerDAO.update(cons);
			
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

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
			consumer = getConsumerDAO.getById(id);
			if (consumer == null) {

				return Response.status(404).entity("Not Found").build();

			}
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity(consumer).build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN })
	@DELETE
	@Path("/delete")
	public Response delete(Consumer prod) {
		try {
			getConsumerDAO.delete(prod);
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
			getConsumerDAO.deleteById(id);// retirar o supressed error
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
	public Response save(Consumer cons) {
		try {
			cons.setId(null);
		
			getConsumerDAO.save(cons);

		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
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
			total = getConsumerDAO.total();
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}

		return Response.status(200).entity(total).build();
	}
}
