package com.fdc.boarding.core.transaction;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.hibernate.HibernateException;

import com.fdc.boarding.core.jpa.EntityManagerStoreImpl;
import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.core.transaction.annotation.Transactional;

/**
 * A simple transaction interceptor which registers an entitymangager in a ThreadLocal and unregisters after the
 * method was called.
 * It does not support any kind of context propagation. If a transactional method calls another's bean transactional
 * method a new entity manager is created and added to the stack.
 * 
 * Note that this is not currently supporting joining existing transactions.
 */
@Interceptor
@Transactional
public class TransactionInterceptor {
	private LoggerProxy 				logger 				= LoggerProxy.getLogger();

   @Inject
   private EntityManagerStoreImpl 		entityManagerStore;


   @AroundInvoke
   public Object runInTransaction(
     InvocationContext 					invocationContext
   ) throws Exception 
   {
	   EntityManager 					em;
	   Object 							result	= null;
	   
	   em = entityManagerStore.createAndRegister();
	   try 
	   {
		   em.getTransaction().begin();
		   result = invocationContext.proceed();
		   em.getTransaction().commit();

	   } 
	   catch( Exception e ) 
	   {
		   try 
		   {
			   if( em.getTransaction().isActive() ) 
			   {
				   em.getTransaction().rollback();
				   logger.debug("Rolled back transaction");
			   }
		   } 
		   catch( HibernateException e1 ) 
		   {
			   logger.warn("Rollback of transaction failed -> " + e1);
		   }
		   throw e;
	   } 
	   finally 
	   {
		   if( em != null ) 
		   {
			   entityManagerStore.unregister( em );
			   em.close();
		   }
	   }

	   return result;
   }
}