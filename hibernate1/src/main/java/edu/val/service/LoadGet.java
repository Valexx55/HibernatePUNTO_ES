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

public class LoadGet {

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
        		Regions rg = null;
        		//EJM 1 load con OBJETO q existe
        		/*
        		try {
        			
        			 rg = session.load(Regions.class, new BigDecimal(500));
        			 System.out.println("Se ejecuto el load");
        			 System.out.println("REGION LEIDOA " + rg.getRegionName());//aahí accede a la base de datos
        			 
        		}catch (Exception onf)
        		{
        			onf.printStackTrace();
        		}
        		**/
        		//1 con load q no existe
        		/**
        		try {
        			
	       			 rg = session.load(Regions.class, new BigDecimal(600));
	       			 System.out.println("Se ejecuto el load");
	       			 System.out.println("REGION LEIDOA " + rg.getRegionName());//aahí accede a la base de datos
	       			 
	       		}catch (Exception onf)
	       		{
	       			onf.printStackTrace();
	       		}
        		**/
        		//2 con GET existe
        		/*
        		try {
        			
	       			 rg = session.get(Regions.class, new BigDecimal(500));
	       			 System.out.println("Se ejecuto el get");//ojo log, antes va el select
	       			 System.out.println("REGION LEIDOA " + rg.getRegionName());
	       			 
	       		}catch (Exception onf)
	       		{
	       			onf.printStackTrace();
	       		}
        	*/
        		//3 CON GET Q NO EXISTE, DEVUELVE NULL
        		
        		try {
        			
	       			 rg = session.get(Regions.class, new BigDecimal(600));
	       			 System.out.println("Se ejecuto el get");//ojo log, antes va el select
	       			 if (null != rg)
	       			 {
	       				System.out.println("REGION LEIDOA " + rg.getRegionName()); 
	       			 } else {
	       				 System.out.println("GET DEVOLVIO NULL");
	       			 }
	       			 
	       			 
	       		}catch (Exception onf)
	       		{
	       			onf.printStackTrace();
	       		}
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
