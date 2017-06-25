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

public class MergeExample {

	public static void main(String[] args) {
		
		//EJMPLO 1 MERGE
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
        		Regions rg3 = (Regions)session2.merge(rg);//INEXISTENTE -DETACHED-, Devuelve un nuevo objeto, que queda persisten
        		rg3.setRegionName("MODERNURAS7");//es el nuevo el que aparece PERSISNTEN, no el otro
        		rg.setRegionName("MODERNURAS8");//est no tiene efecto, sigue desconectado
        		
        		
        		Regions rg4 = new Regions();//ahora con un objecto transient
        		rg4.setRegionId(new BigDecimal(750));//el comportamiento es como detached
        		rg4.setRegionName("HIPONDURAS");//
        		Regions rg5 = (Regions)session2.merge(rg4); //me mete uno nuevo y me devuelve otro ojbeto persintent
        		rg4.setRegionName("HIPONDURAS2");
        		rg5.setRegionName("AYMIAMOL");
        		
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
