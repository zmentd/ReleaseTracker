package com.fdc.boarding.core.jpa;

import java.util.Stack;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 * A store for entity managers. It is basically a ThreadLocal which stores the entity manager.
 * The {@link com.fdc.boarding.releasetracker.common.transaction.TransactionInterceptor} is expected to register entity manager. The application code
 * can get the current entity manager either by injecting the store or the {@link EntityManagerDelegate}.
 */
@ApplicationScoped
public class EntityManagerStoreImpl implements IEntityManagerStore 
{
	final LoggerProxy 							logger 				= LoggerProxy.getLogger();

	@Inject
	private EntityManagerFactory 				emf;
	private ThreadLocal<Stack<EntityManager>>	emStackThreadLocal 	= new ThreadLocal<Stack<EntityManager>>();
	
	@Override
	public EntityManager get(
	) 
	{
		EntityManager					local;
		Stack<EntityManager> 			entityManagerStack;
		
		logger.debug( "Getting the current entity manager" );
		
		entityManagerStack = emStackThreadLocal.get();
		if( entityManagerStack == null || entityManagerStack.isEmpty() ) 
		{
			/* if nothing is found, we return null to cause a NullPointer exception in the business code.
         		This leeds to a nicer stack trace starting with client code.
			 */
			logger.warn( "No entity manager was found. Did you forget to mark your method as transactional?" );
			this.createAndRegister();
//			return null;
		} 
		local	= entityManagerStack.peek();
		
		return local;
	}

	/**
	 * Creates an entity manager and stores it in a stack. The use of a stack allows to implement
	 * transaction with a 'requires new' behaviour.
	 *
	 * @return the created entity manager
	 */
	@Override
	public EntityManager createAndRegister(
	) 
	{
		Stack<EntityManager> 			entityManagerStack;
		EntityManager 					entityManager;
		
		logger.debug( "Creating and registering an entity manager" );
		
		entityManagerStack = emStackThreadLocal.get();
		if( entityManagerStack == null ) 
		{
			entityManagerStack = new Stack<EntityManager>();
			emStackThreadLocal.set(entityManagerStack);
		}
		entityManager = emf.createEntityManager();
		entityManagerStack.push( entityManager );

		return entityManager;
	}

	/**
	 * Removes an entity manager from the thread local stack. It needs to be created using the
	 * {@link #createAndRegister()} method.
	 *
	 * @param entityManager - the entity manager to remove
	 * @throws IllegalStateException in case the entity manager was not found on the stack
	 */
	@Override
	public void unregister(
	  EntityManager 						entityManager
	) 
	{
		Stack<EntityManager> 				entityManagerStack;
		
		logger.debug( "Unregistering an entity manager" );
		
		entityManagerStack = emStackThreadLocal.get();
		if( entityManagerStack == null || entityManagerStack.isEmpty() )
		{
			throw new IllegalStateException( "Removing of entity manager failed. Your entity manager was not found." );
		}

		if( entityManagerStack.peek() != entityManager )
		{
			throw new IllegalStateException( "Removing of entity manager failed. Your entity manager was not found." );
		}
		entityManagerStack.pop();
	}
}