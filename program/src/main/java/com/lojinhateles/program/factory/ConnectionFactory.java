package com.lojinhateles.program.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static EntityManagerFactory factory;
	private static EntityManager manager;

	public static EntityManager getConection() {

		factory = Persistence.createEntityManagerFactory("lojinhajpa2023");
		manager = factory.createEntityManager();
		return manager;

	}

	public static void close() {
		if (factory != null && manager != null) {
			manager = null;
			factory = null;
		}
	}

}
