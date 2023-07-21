package com.lojinhateles.program.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Orders;
import com.lojinhateles.program.service.ObjectService;

public class OrdersDAO implements ObjectService<Orders> {
	private static EntityManager connection = ConnectionFactory.getConection();

	@Override
	public List<Orders> getAll() {
		Query query = connection.createQuery("FROM Orders");

		List<Orders> list = query.getResultList();
		return list;
	}

	@Override
	public Orders getById(Integer id) {
		Orders orders = connection.find(Orders.class, id);
		if (orders == null) {
			return null;
		}
		return orders;

	}

	@Override
	public void save(Orders object) {	
		if (object != null) {
			connection.getTransaction().begin();
			object.getConsumer().setId(null);
			object.getProducts().forEach(x -> x.setId(null));
			object.getProducts().forEach(x-> x.getCategory().setId(null));
			connection.persist(object);
			connection.getTransaction().commit();
		} else {
			connection.getTransaction().rollback();
		}

	}

	@Override
	public void delete(Orders object) {
		try {
			Orders orders = this.getById(object.getId());// verificar se existe o ID
			if (orders != null && object != null) {
				if (orders.equals(object)) {// depois verificar se as informações dos dois objetos são iguais
					connection.getTransaction().begin();
					connection.remove(orders);
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
			Orders orders = this.getById(id);// verificar se existe o ID
			if (orders != null) {
				connection.getTransaction().begin();
				connection.remove(orders);
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
	public void update(Orders object) {
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
