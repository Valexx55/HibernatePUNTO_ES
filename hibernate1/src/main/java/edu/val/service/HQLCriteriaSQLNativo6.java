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

import org.hibernate.SQLQuery;
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
import edu.val.dto.oracle.Employees;
import edu.val.dto.oracle.Regions;
import oracle.net.aso.d;
import oracle.net.aso.q;

public class HQLCriteriaSQLNativo6 {
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
        		//OPCION 1, A PELO
        		SQLQuery query = session.createSQLQuery("select employee_id, email from employees");
        		List<Object[]> rows = query.list();
        		Employees emp = null;
        		for(Object[] row : rows){
        			emp = new Employees();
        			emp.setEmployeeId(((BigDecimal)row[0]).intValue());
        			emp.setEmail((String)row[1]);
        			System.out.println(emp.getEmail() + " " + emp.getEmployeeId());
        		}
        		//OPCION 2, HACIENDO ENTIDAD
        		/*
        		SQLQuery query = session.createSQLQuery("select * from employees");
        		query.addEntity(Employees.class);
        		List<Employees> rows = query.list();
        		for(Employees e : rows){
        			
        			System.out.println(e.getEmail() + " " + e.getEmployeeId());
        		}
        		*/
        		///OPCION 3 ADD JOIN
        		
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
		
