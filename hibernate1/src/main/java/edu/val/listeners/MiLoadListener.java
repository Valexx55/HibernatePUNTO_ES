package edu.val.listeners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

public class MiLoadListener implements LoadEventListener{


	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		// TODO Auto-generated method stub
		
		System.out.println("SIIIUUUUU "+event.getEntityClassName() + " KARGADO / LEIDO");
	}

}
