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

import com.lojinhateles.program.dao.ProductDAO;
import com.lojinhateles.program.dto.OrdersDTO;
import com.lojinhateles.program.dto.ProductDTO;
import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Product;
import com.lojinhateles.program.service.ObjectService;

@Path("/product")
public class ProductResource {
	private static ObjectService<Product> getProductDAO = new ProductDAO();
	private static Product product = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAll() {
		List<Product> allProduct = new ArrayList<Product>();
		GenericEntity<List<ProductDTO>> entity;
		try {

			allProduct = getProductDAO.getAll();

			List<ProductDTO> newProductDto = allProduct.stream().map(x -> new ProductDTO(x))
					.collect(Collectors.toList());

			if (allProduct.size() <= 0) {
				return Response.status(404).entity("Not Found").build();
			}
			entity = new GenericEntity<List<ProductDTO>>(Lists.newArrayList(newProductDto)) {
			};
			return Response.ok(entity).build();
		} catch (NullPointerException nulls) {
			Response.status(404).entity("Not Found").build();
		} finally {
			ConnectionFactory.close();
		}
		return Response.status(500).entity("Not Working!").build();

	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(Product prod) {
		try {
			product = getProductDAO.getById(prod.getId());
			if (product == null) {
				return Response.status(404).entity("Not Found").build();
			}
			getProductDAO.update(prod);

		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity("Its Work!").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getById(@PathParam("id") int id) {
		ProductDTO prodDTO;
		try {
			product = getProductDAO.getById(id);
			prodDTO = new ProductDTO(product);
			if (product == null) {
				return Response.status(404).entity("Not Found").build();
			}
		} catch (NullPointerException nulls) {
			return Response.status(404
					).entity("Not Found!").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}
		return Response.status(200).entity(prodDTO).build();
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN })
	@DELETE
	@Path("/delete")
	public Response delete(Product prod) {
		try {
			getProductDAO.delete(prod);
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
			getProductDAO.deleteById(id);// retirar o supressed error
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
	public Response save(Product prod) {
		try {
			prod.setId(null);

			getProductDAO.save(prod);

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
			total = getProductDAO.total();
		} catch (NullPointerException nulls) {
			return Response.status(404).entity("Not Found").build();

		} catch (RuntimeException runtime) {
			return Response.status(500).entity("Not Working!").build();

		}

		return Response.status(200).entity(total).build();
	}
}
