package edu.val.service;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import edu.val.dto.oracle.Regions;

public class EsqueletoHB {
	


	public static void main(String[] args) {
		
		StandardServiceRegistry registry = null;
    	SessionFactory factory = null;
    	Session session = null;
    	try
    	{
    		registry = new StandardServiceRegistryBuilder().configure().build();////applySettings(configuration.getProperties());
        	factory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();//configuration.buildSessionFactory(builder.build());
        	Transaction tx = null;
        	try 
        	{
        		session = factory.openSession();
        		tx = session.beginTransaction();
        		//TODO METER EL CÓDIGO DEL EJEMPLO
        		/*Regions rg = session.get(Regions.class, new BigDecimal(500));
        		System.out.println("REGION LEIDOA " + rg.getRegionName());*/
        		
        		
        		tx.commit();
			} catch (Exception e) 
        	{
				e.printStackTrace();
				tx.rollback();
			}finally {
				
				if (session != null) {
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
