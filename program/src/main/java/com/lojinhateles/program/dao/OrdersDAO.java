package com.lojinhateles.program.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lojinhateles.program.dto.OrdersDTO;
import com.lojinhateles.program.enums.SituacionOrder;
import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Category;
import com.lojinhateles.program.model.Consumer;
import com.lojinhateles.program.model.Orders;
import com.lojinhateles.program.model.Product;
import com.lojinhateles.program.service.ObjectService;

public class OrdersDAO implements ObjectService<Orders> {
	private static EntityManager connection = ConnectionFactory.getConection();

	@Override
	public List<Orders> getAll() {
		Query query = connection.createQuery("Select c from Orders c");
		List<Orders> list = query.getResultList();
		return list;
	}

	@Override
	public Orders getById(Integer id) {
		Orders orders = connection.find(Orders.class, id);
		if (orders != null) {
			return orders;
		}
		return null;

	}

	@Override
	public void save(Orders object) {
		try {
			connection.getTransaction().begin();		
			connection.merge(object);
			connection.getTransaction().commit();
		} catch (RuntimeException runtime) {
			connection.getTransaction().rollback();
			runtime.printStackTrace();
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
