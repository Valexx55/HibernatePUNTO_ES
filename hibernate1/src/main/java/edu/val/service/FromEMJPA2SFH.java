package edu.val.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.val.dto.oracle.Countries;
import edu.val.dto.oracle.Regions;

public class FromEMJPA2SFH {
	
	
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
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    	
    	EntityManager em = emf.createEntityManager();
    	
    	
    	//EntityTransaction et = em.getTransaction();
    	SessionFactory sf = em.getEntityManagerFactory().unwrap(SessionFactory.class);
    	Session sesion = sf.openSession();
    	
    	Transaction txh = null;
		
		try
		{
			txh = sesion.beginTransaction();
	    	Query consulta = em.createQuery("SELECT r FROM regions r");
	    	List<Regions>lista_regiones = consulta.getResultList();
	    	mostrarRegiones(lista_regiones);
	    	txh.commit();
			
			
		} catch (Exception e)
		{
			txh.rollback();
			e.printStackTrace();
		}
		finally {
			if (em!= null) em.close();
			if (emf != null) emf.close();
		}
		
    	
    	
    	
    //	RegionsDao regions_dao = new RegionsDao();
    	
    //	List<Regions> lista_regiones = regions_dao.obtenerRegiones();
    	
    	
	}

}
