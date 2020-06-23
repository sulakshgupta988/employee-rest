package com.increff.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	// Create hibernate session factory
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	public static void configure() {
		// Configuring hibernate properties
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");

		// Adding a mapping from my java class (pojo) to database table
		config.addAnnotatedClass(EmployeePojo.class);
		// Create hibernate session factory
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();

		sessionFactory = config.buildSessionFactory(serviceRegistryObj);

	}

	public void createSession() {
		if (session != null && session.isOpen()) {
			return;
		}
		session = sessionFactory.openSession();
	}

	public void closeSession() {
		if (session != null) {
			session.close();
		}
	}
	
	public Session getSession() {
		return session;
	}
	
	public void beginTransaction() {
		 transaction = session.beginTransaction();
	}
	
	public void commitTransaction() {
		if(transaction!=null) {
			transaction.commit();
		}
	}
	
	
}
