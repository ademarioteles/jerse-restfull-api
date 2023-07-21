package com.lojinhateles.program;

import java.util.ArrayList;
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

import com.lojinhateles.program.dao.OrdersDAO;
import com.lojinhateles.program.dto.OrdersDTO;
import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Orders;
import com.lojinhateles.program.service.ObjectService;

@Path("/orders")
public class OrdersResource {
	private static ObjectService<Orders> getOrdersDAO = new OrdersDAO();
	private static Orders orders = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAll() {

		GenericEntity<List<OrdersDTO>> entity;
		List<OrdersDTO> newOrderDto;
		List<Orders> allOrders = new ArrayList<Orders>();
		try {
			allOrders = getOrdersDAO.getAll();
			newOrderDto = allOrders.stream().map(x -> new OrdersDTO(x)).collect(Collectors.toList());

			if (newOrderDto.size() <= 0) {
				return Response.status(404).entity("Not Found").build();
			}
			
			entity = new GenericEntity<List<OrdersDTO>>(Lists.newArrayList(newOrderDto)) {
			};
			return Response.ok(entity).build();
			
		} catch (RuntimeException runtime) {
			runtime.printStackTrace();
		} finally {
			ConnectionFactory.close();
		}
		return Response.status(500).entity("Not Working!").build();

	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(Orders prod) {
		try {
			orders = getOrdersDAO.getById(prod.getId());
			if (orders == null) {
				return Response.status(404).entity("Not Found").build();
			}
			getOrdersDAO.update(prod);

		} catch (NullPointerException runtime) {
			return Response.status(500).entity("Not Working!").build();
		}
		return Response.status(200).entity("Its Work!").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		OrdersDTO orderDTO;
		try {
			orders = getOrdersDAO.getById(id);
			orderDTO = new OrdersDTO(orders);
			if (orders == null) {

				return Response.status(404).entity("Not Found").build();

			}
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}

		return Response.status(200).entity(orderDTO).build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN })
	@DELETE
	@Path("/delete")
	public Response delete(Orders prod) {
		try {
			getOrdersDAO.delete(prod);
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
			getOrdersDAO.deleteById(id);// retirar o supressed error
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
	public Response save(Orders order) {
		try {
			order.setId(null);
			getOrdersDAO.save(order);
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} 

		return Response.status(200).entity("Its Work!").build();
	}

	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/total")
	public Response total() {
		int total = 0;
		try {
			total = getOrdersDAO.total();
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}

		return Response.status(200).entity(total).build();
	}
}
