package fr.wati.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CRUDEntityManagerFactory {

	public CRUDEntityManagerFactory() {
	}

	public static EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("school");
		// We need an entity manager to create entity provider
		EntityManager em = emf.createEntityManager();
		return em;
	}
}
