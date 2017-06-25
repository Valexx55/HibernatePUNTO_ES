package edu.val.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;

import edu.val.dto.oracle.Regions;
import edu.val.dto.oracle.Registrotabla;
import edu.val.listeners.ListenerSave;
import edu.val.listeners.MiLoadListener;
import edu.val.listeners.MyInterceptor;

public class MainIntercetptors {
	
	public static void main(String[] args) {
		
		//EJEMPLO DE LISTNER INTERCEPETOR
		/*
		StandardServiceRegistry registry = null;
		SessionFactory factory = null;
		Session session = null;
		SessionFactoryImplementor sfi = null;
		

		try {
	    	
	    	
	    	
			registry = new StandardServiceRegistryBuilder().configure().build();////
		
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();//
			
			session = factory.withOptions().interceptor(new MyInterceptor()).openSession();
			

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
		*/
		//FIN EJEMPLO LISTENER
		
		
		//EJEMPLO EVENTLISTENR DEL SISTEMA
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    	
    	EntityManager em = emf.createEntityManager();
    	
    	try
    	{
    		SessionFactoryImpl sfi  = emf.unwrap(SessionFactoryImpl.class);
    		EventListenerRegistry registry = sfi.getServiceRegistry().getService(EventListenerRegistry.class);
    	    registry.getEventListenerGroup(EventType.LOAD).appendListener(new MiLoadListener());
    	    
    	    Session sesion = sfi.openSession();
    	    Transaction t =  sesion.beginTransaction();
    	    //EntityTransaction et = em.getTransaction(); //finciona con los dos, manda polla JPA e HIBER
    	    try{
    	    	
    	    	sesion.get(Regions.class, new BigDecimal(500));
        	    //em.find(Regions.class, new BigDecimal(500));
        	    //et.commit();
    	    	t.commit();
    	    	
    	    }catch (Exception e)
    	    {
    	    	//t.rollback();
    	    	//et.rollback();
    	    	e.printStackTrace();
    	    	t.rollback();
    	    }
    	    
    		
    	}catch (Exception e)
    	{
    		
    	} finally {
			if (em != null) try { em.close(); }catch (Exception e2) {
				e2.printStackTrace();
			}
			
			if (emf != null) try { emf.close(); }catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//FIN EJEMPLO LISTENER DEL SISTEMA
	}

}
