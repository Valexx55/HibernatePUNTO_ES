package edu.val.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Filter;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import edu.val.dto.oracle.Countries;
import edu.val.dto.oracle.Employees;
import edu.val.dto.oracle.Regions;
import edu.val.dto.oracle.Registro;
import edu.val.dto.oracle.Registroauto;
import edu.val.dto.oracle.Registroid;
import edu.val.dto.oracle.Registrotabla;

public class StatelessSessionExample {

	public static void main(String[] args) {

		StandardServiceRegistry registry = null;
		SessionFactory factory = null;
		//Session session = null;
		StatelessSession session_stateles = null;
		try {
			registry = new StandardServiceRegistryBuilder().configure().build();//// applySettings(configuration.getProperties());
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();// configuration.buildSessionFactory(builder.build());
			Transaction tx = null;
			try {
				session_stateles = factory.openStatelessSession();
				tx = session_stateles.beginTransaction();

				for (int i = 0; i < 50; i++) {

					session_stateles.insert(new Registro());
				}

				tx.commit();
				//session_stateles.close();

		
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {

				if (session_stateles != null) {
					try {
						session_stateles.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (factory != null) {
				try {
					factory.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		}
	}

}
