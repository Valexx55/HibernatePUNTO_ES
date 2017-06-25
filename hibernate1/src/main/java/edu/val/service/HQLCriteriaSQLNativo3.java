package edu.val.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.val.dto.oracle.Countries;
import edu.val.dto.oracle.Regions;

public class HQLCriteriaSQLNativo3 {
	
	
	private static void mostrarRegiones (List<Regions> list)
	{
		//List<Regions> list = session.createSQLQuery("SELECT * FROM REGIONS").addEntity(Regions.class).list();
		Iterator<Regions> it = list.iterator();
		Regions rg;
		while (it.hasNext())
		{
			rg = it.next();
			System.out.println(rg.getRegionName() + " " +rg.getRegionId());
			Set<Countries> paises = rg.getCountrieses();
			System.out.println("PAISES");
			Iterator<Countries> it_paises = paises.iterator();
			
			while (it_paises.hasNext())
			{
				Countries country = it_paises.next();
				System.out.println(country.getCountryName());
			}
		}
	}

	public static void main(String[] args) {
		
		EntityManagerFactory emf =  null; //Persistence.createEntityManagerFactory("unit");
    	EntityManager em =  null; //emf.createEntityManager();
    	
    	try
    	{
    		emf = Persistence.createEntityManagerFactory("unit");////applySettings(configuration.getProperties());
        	em = emf.createEntityManager();// new MetadataSources( registry ).buildMetadata().buildSessionFactory();//configuration.buildSessionFactory(builder.build());
        	EntityTransaction tx = em.getTransaction();
        	try 
        	{
        		
        		tx.begin();
        		CriteriaBuilder cb = em.getCriteriaBuilder();
        		
        		CriteriaQuery<String> cq = cb.createQuery( String.class );//<INDICA EL TIPO>
        		Root<Regions> root = cq.from( Regions.class );
       
        		
        		cq.select(root.get("regionName").as(String.class));
        		

        		List<String> lista_nombre_regiones = em.createQuery( cq ).getResultList();
        		
        		System.out.println(lista_nombre_regiones);
        		
        		tx.commit();
			} catch (Exception e) 
        	{
				e.printStackTrace();
				tx.rollback();
			}finally {
				
				if (em != null) {
    				try 
    					{
    						em.close();
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
    		
    		if (emf != null) 
    		{
				try {
						emf.close();
					} 
				catch (Exception e2)
					{
						e2.printStackTrace();
					}
				
    		}
    	
    	}
	}
}
		
