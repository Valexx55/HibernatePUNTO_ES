package edu.val.listeners;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class MyInterceptor implements Interceptor {

	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
			throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onLOAD");
		return false;
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onFLUSHDirty");
		System.out.println("Entity " +entity.getClass().getSimpleName() + " id " + id + " ESTADO " + 
				Arrays.toString( currentState ) + " ESTADO ANTERIOR " + 
				Arrays.toString( previousState ));

		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
			throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onSave");
		System.out.println("Entity " +entity.getClass().getSimpleName() + " id " + id + " ESTADO " + 
				Arrays.toString( state ));
		return false;
	}

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
			throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onDelete");
		
	}

	public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onCollectionRecreate");
		
	}

	public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onCollectionRemove");
		
	}

	public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("onCollectionUpdate");
		
	}

	public void preFlush(Iterator entities) throws CallbackException {
		// TODO Auto-generated method stub
		System.out.println("preFlush");
		while (entities.hasNext())
		{
			System.out.println( entities.next().toString());
		}
		
	}

	public void postFlush(Iterator entities) throws CallbackException {
		// TODO Auto-generated method stub
		
	}

	public Boolean isTransient(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEntityName(Object object) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEntity(String entityName, Serializable id) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public void afterTransactionBegin(Transaction tx) {
		// TODO Auto-generated method stub
		
	}

	public void beforeTransactionCompletion(Transaction tx) {
		// TODO Auto-generated method stub
		
	}

	public void afterTransactionCompletion(Transaction tx) {
		// TODO Auto-generated method stub
		
	}

	public String onPrepareStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

}
