package edu.val.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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
import edu.val.dto.oracle.Employees;
import edu.val.dto.oracle.Regions;

public class HQLCriteriaSQLNativo4 {
	
	
	
	
	

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
        		
        		CriteriaQuery<EmpleadoBasic> criteria = cb.createQuery( EmpleadoBasic.class );
        		Root<Employees> root = criteria.from( Employees.class );

        		Path<Integer> idPath = root.get( "employeeId");
        		Path<String> fnPath = root.get( "firstName");

        		criteria.select( cb.construct( EmpleadoBasic.class, idPath, fnPath ) );
        		//criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );

        		List<EmpleadoBasic> le = em.createQuery( criteria ).getResultList();
        		EmpleadoBasic.mostrarEmpleados(le);
        		
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
		
