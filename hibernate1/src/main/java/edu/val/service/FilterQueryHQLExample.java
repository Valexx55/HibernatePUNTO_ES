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

public class FilterQueryHQLExample {

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
        		Filter filter = session.enableFilter("employeeFilter");
                filter.setParameter("salary", 4000);
                Query query = session.createQuery("from Employees e");
                List list = query.list();
                Iterator it =list.iterator();
                while (it.hasNext()) {
                    Employees emp = (Employees) it.next();
                    System.out.println("Employee Name : "+emp.getFirstName() +" , Salary : "+emp.getSalary());
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
