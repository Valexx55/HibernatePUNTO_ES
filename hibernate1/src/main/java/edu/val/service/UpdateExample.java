package edu.val.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.ObjectNotFoundException;
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

public class UpdateExample {

	public static void main(String[] args) {
		
		//EJMPLO 1 UPDATE
		StandardServiceRegistry registry = null;
    	SessionFactory factory = null;
    	Session session = null;
    	Regions rg = null;
    	try
    	{
    		registry = new StandardServiceRegistryBuilder().configure().build();////applySettings(configuration.getProperties());
        	factory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();//configuration.buildSessionFactory(builder.build());
        	Transaction tx = null;
        	try 
        	{
        		session = factory.openSession();
        		tx = session.beginTransaction();
        		
        		rg = session.get(Regions.class, new BigDecimal(500));//RG QUEDA DETACHED
        		System.out.println("REGION LEIDOA " + rg.getRegionName());
        		tx.commit();
        		session.close ();
        		
        		
        		Session session2 = factory.openSession();
        		Transaction tx2 = session2.beginTransaction();
        		rg.setRegionName("MODERNURAS1");//AÚN ESTANDO ASOCIADO A UNA SESSIÓN 
        		session2.update(rg);//INEXISTENTE -DETACHED-, SE ACTUALIZA
        		rg.setRegionName("MODERNURAS5");
        		
        		/*
        		Regions rg2 = new Regions();
        		rg2.setRegionId(new BigDecimal(750));
        		rg2.setRegionName("HIPONDURAS");
        		session2.update(rg2); //esto fallaria pq LA REGIÓN ESTÁ EN TRANSIENT
        		*/
        		tx2.commit();//EN ESTA NUEVA SESIÓN, PASNDO A ESTAR PERSISNTE, POR LO QUE SI LO MODIFICO NUEVAMENTE, CAMBIA
        		
        		session2.close();
        		
			} catch (Throwable e) 
        	{
				e.printStackTrace();
				tx.rollback();
			}finally {
				
				if ((session != null)&&(session.isConnected())) {
    				try 
    					{
    						session.close();
    						
    					} catch (Exception e2) 
    					{
    						e2.printStackTrace();
    					}
    			}
			}
        	
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    		
    	}finally {
    		
    		if (factory != null) 
    		{
				try {
						factory.close();
					} 
				catch (Exception e2)
					{
						e2.printStackTrace();
					}
				
    		}
    	
    	}
	}

}
