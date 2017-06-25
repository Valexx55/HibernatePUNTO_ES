package edu.val.service;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import edu.val.dto.oracle.Countries;
import edu.val.dto.oracle.Regions;

public class CicloDeVida {

	public static void main(String[] args) {
		
		/*//EJEMPLO 1 PERSISTENT
		StandardServiceRegistry registry = null;
		SessionFactory factory = null; 
		Session session = null;
		
		try 
		{
			registry = new StandardServiceRegistryBuilder().configure().build();//// applySettings(configuration.getProperties());
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();// configuration.buildSessionFactory(builder.build());
			session = factory.openSession();
			
			Transaction tx = null;
			
			try {
				tx = session.beginTransaction();
				
				Regions region = session.get(Regions.class, new BigDecimal(500));
				System.out.println("LEIDO =  " + region.getRegionName());
				region.setRegionName("Apatridas");//al estar en persisnt deberia cambiarse
				
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
			
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				
				Regions region = session.get(Regions.class, new BigDecimal(500));
				System.out.println("LEIDO =  " + region.getRegionName());
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
			
		}catch (Throwable t)
		{
			t.printStackTrace();
		}
		finally {
			
			if (factory != null) factory.close();
			
		}**/
		
	
		

		/*//EJEMPLO 2 DETACHED
		
		StandardServiceRegistry registry = null;
		SessionFactory factory = null; 
		Session session = null;
		Regions region = null;
		
		try 
		{
			registry = new StandardServiceRegistryBuilder().configure().build();//// applySettings(configuration.getProperties());
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();// configuration.buildSessionFactory(builder.build());
			session = factory.openSession();
			
			Transaction tx = null;
			
			try {
				tx = session.beginTransaction();
				
				region = session.get(Regions.class, new BigDecimal(500));
				System.out.println("LEIDO =  " + region.getRegionName());
				
				
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
			
			region.setRegionName("JAMONIDAS");//al estar en DETACHED NO tiene efecto deberia cambiarse
			
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				
				region = session.get(Regions.class, new BigDecimal(500));
				System.out.println("LEIDO =  " + region.getRegionName());
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
			
		}catch (Throwable t)
		{
			t.printStackTrace();
		}
		finally {
			
			if (factory != null) factory.close();
			
		}*/
		
		//EJEMPLO 3 TRANSIENT
		/*
		StandardServiceRegistry registry = null;
		SessionFactory factory = null; 
		Session session = null;
		Regions region = null;
		
		try 
		{
			registry = new StandardServiceRegistryBuilder().configure().build();//// applySettings(configuration.getProperties());
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();// configuration.buildSessionFactory(builder.build());
			session = factory.openSession();
			
			Transaction tx = null;
			
			try {
				tx = session.beginTransaction();
				region = new Regions();
				region.setRegionName("MADRIDLAND"); //esto está en transiente
				region.setRegionId(new BigDecimal(301));//si yo modifico el objeto, no tiene validez hasta que se registre en session				
				
				session.save(region);
				
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
			
		
			
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				
				region = session.get(Regions.class, new BigDecimal(301));
				System.out.println("LEIDO =  " + region.getRegionName());
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
		
		}catch (Throwable t)
		{
			t.printStackTrace();
		}
		finally {
			
			if (factory != null) factory.close();
			
		} */
		
	
		//EJEMPLO 4 REMOVED
		
		StandardServiceRegistry registry = null;
		SessionFactory factory = null; 
		Session session = null;
		Regions region = null;
		
		try 
		{
			registry = new StandardServiceRegistryBuilder().configure().build();//// applySettings(configuration.getProperties());
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();// configuration.buildSessionFactory(builder.build());
			session = factory.openSession();
			
			Transaction tx = null;
			
			try {
				tx = session.beginTransaction();
				region = session.get(Regions.class, new BigDecimal(301));
				System.out.println("NOmbre recuperado " + region.getRegionName());
				
				session.delete(region);
				region.setRegionId(new BigDecimal(501)); //esto no tiene EFECTO por estar REMOVED
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
			
		
			
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				
				region = session.get(Regions.class, new BigDecimal(301));
				if (region == null) 
					{
					System.out.println("No existe en la base datos un objeto con ese iD");
					} 
				else {
					System.out.println("LEIDO =  " + region.getRegionName());
				}
				tx.commit();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				tx.rollback();
			} finally 
			{
				if (session != null) session.close();
			}
		
		}catch (Throwable t)
		{
			t.printStackTrace();
		}
		finally {
			
			if (factory != null) factory.close();
			
		} 
		
	}

}
