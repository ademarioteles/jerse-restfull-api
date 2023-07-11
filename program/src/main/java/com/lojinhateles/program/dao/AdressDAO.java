package com.lojinhateles.program.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lojinhateles.program.factory.ConnectionFactory;
import com.lojinhateles.program.model.Adress;
import com.lojinhateles.program.service.ObjectService;

public class AdressDAO implements ObjectService<Adress> {
	private static EntityManager connection = ConnectionFactory.getConection();

	@Override
	public List<Adress> getAll() {
		Query query = connection.createQuery("Select a from Adress a");
		List<Adress> list = query.getResultList();
		return list;
	}
	
	@Override
	public Adress getById(Integer id) {

		Adress adress = connection.find(Adress.class, id);
		if (adress != null) {
			return adress;
		}
		return null;

	}

	@Override
	public void save(Adress object) {
		if (object != null) {
			connection.getTransaction().begin();
			connection.merge(object);
			connection.getTransaction().commit();
		} else {
			throw new RuntimeException("Not Working");
		}
	}

	@Override
	public void delete(Adress object) {
		try {
			Adress adress = this.getById(object.getCep());// verificar se existe o ID
			if (adress != null && object != null) {
				if (adress.equals(object)) {// depois verificar se as informações dos dois objetos são iguais
					connection.getTransaction().begin();
					connection.remove(adress);
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
			Adress adress = this.getById(id);// verificar se existe o ID
			if (adress != null) {
				connection.getTransaction().begin();
				connection.remove(adress);
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
	public void update(Adress object) {
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
