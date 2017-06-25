package edu.val.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
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
import edu.val.dto.oracle.Departments;
import edu.val.dto.oracle.Regions;
import oracle.net.aso.d;
import oracle.net.aso.q;

public class HQLCriteriaSQLNativo5 {
	//EJEMPLO DE JPA NAMED QUERY
	
	private static void mostrarDeptos (List<Departments> list)
	{
		//List<Regions> list = session.createSQLQuery("SELECT * FROM REGIONS").addEntity(Regions.class).list();
		Iterator<Departments> it = list.iterator();
		Departments dp;
		while (it.hasNext())
		{
			dp = it.next();
			System.out.println(dp.getDepartmentName());		
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
        		//ejemplo 1,usando todos
        		//TypedQuery<Departments> query =  em.createNamedQuery("Departments.todos", Departments.class);
        		//List<Departments> results = query.getResultList();
        		
        		//mostrarDeptos(results);
        		//Operations -jeplo dos, usapor por nombre
        		
        		TypedQuery<Departments> query =  em.createNamedQuery("Departments.pornombre", Departments.class);
        		query.setParameter("name", "Operations");
        		//query.setFlushMode(FlushModeType.AUTO);//vss COMMIT
        		
        		Departments dresult = query.getSingleResult(); //daría fallo si devuelvo más de uno
        		System.out.println(dresult.getDepartmentName());
        		
        		
        		
        		
        		
        		
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
		
