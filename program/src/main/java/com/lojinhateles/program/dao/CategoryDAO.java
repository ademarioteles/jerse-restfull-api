package com.lojinhateles.program.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Category;
import com.lojinhateles.program.service.ObjectService;

public class CategoryDAO implements ObjectService<Category> {
	private static EntityManager connection = ConnectionFactory.getConection();

	@Override
	public List<Category> getAll() {
		Query query = connection.createQuery("Select c from Category c");
		List<Category> list = query.getResultList();
		return list;
	}

	@Override
	public Category getObject(Category object) {
		Category category = this.getById(object.getId());
		if (category != null) {
			if (object.equals(category)) {
				return category;
			}
		}
		return null;

	}

	@Override
	public Category getById(Integer id) {

		Category category = connection.find(Category.class, id);
		if (category != null) {
			return category;
		}
		return null;

	}

	@Override
	public void save(Category object) {
		if (object != null) {
			connection.getTransaction().begin();
			connection.merge(object);
			connection.getTransaction().commit();
		} else {
			throw new RuntimeException("Not Working");
		}
	}

	@Override
	public void delete(Category object) {
		try {
			Category category = this.getById(object.getId());// verificar se existe o ID
			if (category != null && object != null) {
				if (category.equals(object)) {// depois verificar se as informações dos dois objetos são iguais
					connection.getTransaction().begin();
					connection.remove(category);
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
			Category category = this.getById(id);// verificar se existe o ID
			if (category != null) {
				connection.getTransaction().begin();
				connection.remove(category);
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
	public void update(Category object) {
		try {
			connection.getTransaction().begin();
			connection.persist(object);
		} catch (RuntimeException runtime) {
			connection.getTransaction().rollback();
		} finally {
			connection.getTransaction().commit();
		}
	}

}
