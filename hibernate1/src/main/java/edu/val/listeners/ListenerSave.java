package edu.val.listeners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

public class ListenerSave implements SaveOrUpdateEventListener{

	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		// TODO Auto-generated method stue
		
		System.out.println("SIIIUUUUU "+event.getEntityName() + " creado / actualizado");
		
	}

}
