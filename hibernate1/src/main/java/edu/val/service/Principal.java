package edu.val.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import edu.val.dao.oracle.RegionsDao;
import edu.val.dto.oracle.Countries;
import edu.val.dto.oracle.Regions;

public class Principal {
	
	public static void main(String[] args) {
	
		try{
			
		
		/*puesta a punto  sesionfactory HIBERNATE
		//Configuration configuration = new Configuration().configure();
    	//Preparo a un objeto, que será el encargado de generarme el estado de comunicación con la base de datos
    	//StandardServiceRegistryBuilder se preconfigura el entorno a emplear
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();////applySettings(configuration.getProperties());
    	
    	//Ahora sí, obtengo el objeto SessionFactory, a partir de la anterior clase /servicio
    	//que ya es la clase que encapsula al Pool y demás recursos físicos
    	SessionFactory factory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();//configuration.buildSessionFactory(builder.build());
    	
    	//Ahora ya con sesion, obtengo y manejo conexiones que me va dando SessionFactory
    	Session session = factory.openSession();
    	
    	*FINPUESTA A PUNTO SESIONFACTORY HIBERNATE**/
    	/***EJEMPLO 1
    	//Me creo el POJO
    	Regions region = new Regions();
    	region.setRegionId(new BigDecimal(500));
    	region.setRegionName("Antartida");
    	
    	Transaction transaction = null;
    	//Y procedo a guardarlo --> INICIO DE LA TRANSACCION
    	try 
    	{
    		transaction = session.beginTransaction();
    		session.save(region);
    		transaction.commit();//si todo ha ido bien, persisto los cambio, los hago de verdad, no en la copia de la BD
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    	}
    	FIN EJEMPLO 1**/
    	
    	/**EJEMPLO 2
    	Transaction transaction = null;
    	try 
    	{
    		
    		transaction = session.beginTransaction();
    		@SuppressWarnings("unchecked")
			List<Regions> list = session.createSQLQuery("SELECT * FROM REGIONS").addEntity(Regions.class).list();
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
    		transaction.commit();//si todo ha ido bien, persisto los cambio, los hago de verdad, no en la copia de la BD
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    	}
    	finally 
    	{
    		session.close();//haya ido bien o mal, libero recursos!
    		factory.close();
    	}**/
    	
    	
			
    	//EJMPLEO JPA
   /**EJEMPLO JPA  	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    	
    	EntityManager em = emf.createEntityManager();
    	
    	
    	EntityTransaction et = em.getTransaction();
    	et.begin();
    	Query consulta = em.createQuery("SELECT r FROM regions r");
    	List<Regions>lista_regiones = consulta.getResultList();
    	
    	
    	
    //	RegionsDao regions_dao = new RegionsDao();
    	
    //	List<Regions> lista_regiones = regions_dao.obtenerRegiones();
    	
    	mostrarRegiones(lista_regiones);
    	et.commit();
    	em.close();
    	emf.close();
**/    	
		}catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
	
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

}
