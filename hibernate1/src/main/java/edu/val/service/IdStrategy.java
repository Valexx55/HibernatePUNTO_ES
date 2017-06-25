package edu.val.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import edu.val.dto.oracle.Countries;
import edu.val.dto.oracle.Regions;
import edu.val.dto.oracle.Registro;
import edu.val.dto.oracle.Registroauto;
import edu.val.dto.oracle.Registroid;
import edu.val.dto.oracle.Registrotabla;

public class IdStrategy {

	public static void main(String[] args) {

		// EJEMPLO 1 UUID 1
/*
		StandardServiceRegistry registry = null;
		SessionFactory factory = null;
		Session session = null;
		Registro registro = null;
		Registro registro2 = null;
		Registro registro3 = null;

		try {
			registry = new StandardServiceRegistryBuilder().configure().build();////
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//
			session = factory.openSession();

			Transaction tx = null;

			try {
				tx = session.beginTransaction();
				registro = new Registro();
				registro2 = new Registro();
				registro3 = new Registro();

				session.save(registro);
				session.save(registro2);
				session.save(registro3);

				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				if (session != null)
					session.close();
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {

			if (factory != null)
				factory.close();

		}*/

		// EJEMPLO 2 SEQUENCE Integer
		/*
		StandardServiceRegistry registry = null;
		SessionFactory factory = null;
		Session session = null;
		Registroid registro = null;
		Registroid registro2 = null;
		Registroid registro3 = null;

		try {
			registry = new StandardServiceRegistryBuilder().configure().build();////
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//
			
			session = factory.openSession();

			Transaction tx = null;

			try {	
				tx = session.beginTransaction();
				registro = new Registroid();
				registro2 = new Registroid();
				registro3 = new Registroid();

				session.save(registro);
				session.save(registro2);
				session.save(registro3);

				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				if (session != null)
					session.close();
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {

			if (factory != null)
				factory.close();

		}
*/
		
		//EJEMPLO AUTO
		/*
		StandardServiceRegistry registry = null;
		SessionFactory factory = null;
		Session session = null;
		
		Registroauto registro = null;
		Registroauto registro2 = null;
		Registroauto registro3 = null;

		try {
	    	
	    	
	    	
			registry = new StandardServiceRegistryBuilder().configure().build();////
		
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//
			
			session = factory.openSession();

			Transaction tx = null;
			try {	
				tx = session.beginTransaction();
				session.save(new Registroauto());
				tx.commit();
				
				

			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				if (session != null)
					session.close();
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {

			if (factory != null)
				factory.close();

		}*/
		
		//ejempo TABLA
		
		StandardServiceRegistry registry = null;
		SessionFactory factory = null;
		Session session = null;
		

		try {
	    	
	    	
	    	
			registry = new StandardServiceRegistryBuilder().configure().build();////
		
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//
			
			session = factory.openSession();

			Transaction tx = null;
			try {	
				tx = session.beginTransaction();
				session.save(new Registrotabla());
				tx.commit();
				
				

			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				if (session != null)
					session.close();
			}

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {

			if (factory != null)
				factory.close();

		}
	}

}
