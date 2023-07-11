package com.lojinhateles.program.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Product;
import com.lojinhateles.program.service.ObjectService;

public class ProductDAO implements ObjectService<Product> {
	private static EntityManager connection = ConnectionFactory.getConection();

	@Override
	public List<Product> getAll() {
		Query query = connection.createQuery("Select p from Product p");
		List<Product> list = query.getResultList();
		return list;
	}
	
	@Override
	public Product getById(Integer id) {

		Product product = connection.find(Product.class, id);
		if (product != null) {
			return product;
		}
		return null;

	}

	@Override
	public void save(Product object) {
		if (object != null) {
			connection.getTransaction().begin();
			connection.merge(object);
			connection.getTransaction().commit();
		} else {
			throw new RuntimeException("Not Working");
		}
	}

	@Override
	public void delete(Product object) {
		try {
			Product product = this.getById(object.getId());// verificar se existe o ID
			if (product != null && object != null) {
				if (product.equals(object)) {// depois verificar se as informações dos dois objetos são iguais
					connection.getTransaction().begin();
					connection.remove(product);
				} else {
					throw new RuntimeException("Not Working!");
				}
			}

		} catch (RuntimeException runtime) {
			connection.getTransaction().rollback();
			throw new RuntimeException("Not Working!");
		} finally {
			connection.getTransaction().commit();
		}

	}

	@Override
	public void deleteById(Integer id) {
		try {
			Product product = this.getById(id);// verificar se existe o ID
			if (product != null) {
				connection.getTransaction().begin();
				connection.remove(product);
			}

		} catch (RuntimeException runtime) {
			connection.getTransaction().rollback();
			throw new RuntimeException("Not Working!");
		} finally {
			connection.getTransaction().commit();
		}

	}

	@Override
	public int total() {

		return this.getAll().size();
	}

	@Override
	public void update(Product object) {
		try {
			connection.getTransaction().begin();
			connection.merge(object);
		} catch (RuntimeException runtime) {
			connection.getTransaction().rollback();
		} finally {
			connection.getTransaction().commit();
		}
	}

}
