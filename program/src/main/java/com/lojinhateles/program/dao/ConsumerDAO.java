package com.lojinhateles.program.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Consumer;
import com.lojinhateles.program.service.ObjectService;

public class ConsumerDAO implements ObjectService<Consumer> {
	private static EntityManager connection = ConnectionFactory.getConection();

	@Override
	public List<Consumer> getAll() {
		Query query = connection.createQuery("Select c from Consumer c");
		List<Consumer> list = query.getResultList();
		return list;
	}
	
	@Override
	public Consumer getById(Integer id) {

		Consumer consumer = connection.find(Consumer.class, id);
		if (consumer != null) {
			return consumer;
		}
		return null;

	}

	@Override
	public void save(Consumer object) {
		if (object != null) {
			connection.getTransaction().begin();
			connection.merge(object);
			connection.getTransaction().commit();
		} else {
			throw new RuntimeException("Not Working");
		}
	}

	@Override
	public void delete(Consumer object) {
		try {
			Consumer consumer = this.getById(object.getId());// verificar se existe o ID
			if (consumer != null && object != null) {
				if (consumer.equals(object)) {// depois verificar se as informações dos dois objetos são iguais
					connection.getTransaction().begin();
					connection.remove(consumer);
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
			Consumer consumer = this.getById(id);// verificar se existe o ID
			if (consumer != null) {
				connection.getTransaction().begin();
				connection.remove(consumer);
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
	public void update(Consumer object) {
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
