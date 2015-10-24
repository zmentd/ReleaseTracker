package com.fdc.boarding.core.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javassist.util.proxy.ProxyObject;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

import com.fdc.boarding.core.domain.IActivatable;
import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.domain.ILogicallyDeletable;
import com.fdc.boarding.core.service.exception.ServiceException;
import com.fdc.boarding.core.util.DateTimeUtil;

/**
 *
 */
@Dependent
public class EntityDao extends AbstractPersistentSvc implements IEntityDao
{
   private static final long           serialVersionUID    = 1L;

    @Inject
    private Validator					validator;
  
    /**
     * 
     */
    public EntityDao(
    )
    {
        super();
    }

    @Override
    public <T extends IEntity<?>> void delete(
      T                                 ent
    )
    {
        IEntity<?>                      entity;
        
        try
        {
            entity  = ent;
            if( !getEntityManager().contains( entity ) )
            {
                entity = getEntityManager().merge( entity );
            }
            if( entity instanceof ILogicallyDeletable || entity instanceof IActivatable )
            {
                if( entity instanceof ILogicallyDeletable )
                {
                    ( ( ILogicallyDeletable )entity ).setLogicallyDeletedTimestamp( DateTimeUtil.getCurrentUtcDateTime() );
                }
                if( entity instanceof IActivatable )
                {
                    ( ( IActivatable )entity ).setActive( false );
                }
                getEntityManager().persist( entity );
            }
            else
            {
                getEntityManager().remove( entity );
            }

            getEntityManager().flush();
        }
        catch( Exception e )
        {
            handleException( e, "delete", "Delete failed for entity with id:" + ent.getUniqueId(), ent );
        }
    }
    
    /**
     *  Function for fetching entity objects by key.
     *  Note that because we do not have a generic entity object factory, we cannot factory-create
     *  the return value; if this is used to fetch a member of a base class, the result will be
     *  of the base class, not any derived class.
     *  
     * @param <T>
     *      The entity object class
     * @param <P>
     *      The key class
     * @param clazz
     *      The entity class object
     * @param key
     *      The key
     * @param em
     *      An entityManager
     * @return
     *      The entity object if found, null if not found.
     */
    @Override
    public <T extends IEntity<P>, P extends Serializable> T findByKey(
        Class<T>                        clazz
    ,   P                               key
	, String...							initialize
    )
    {
        return findByKey( clazz, key, false, false, initialize );
    }

    /**
     *  Function for fetching entity objects by key.
     *  Note that because we do not have a generic entity object factory, we cannot factory-create
     *  the return value; if this is used to fetch a member of a base class, the result will be
     *  of the base class, not any derived class.
     *  
     * @param <T>
     *      The entity object class
     * @param <P>
     *      The key class
     * @param clazz
     *      The entity class object
     * @param key
     *      The key
     * @param em
     *      An entityManager
     * @return
     *      The entity object if found, null if not found.
     */
    public <T extends IEntity<P>, P extends Serializable> T findByKey(
      Class<T>                        	clazz
    , P                               	key
    , boolean                           includeInactive
    , boolean                           includeLogclDel
	, String...							initialize
    )
    {
        Class<T>                        entityClass;
        T                          		entity;
        
        try
        {
            //
            // Locate the entity class.
            //
            entityClass = clazz;
            //
            // Find the entity.
            //
            entity      = getEntityManager().find( entityClass, key );
            if( entity != null )
            {
                //
                // Use the located entity class to find the busnobj.
                //
                if( !includeInactive && entity instanceof IActivatable )
                {
                    if( !( ( IActivatable )entity ).isActive() )
                    {
                        entity = null;
                    }
                }
                if( !includeLogclDel && entity instanceof ILogicallyDeletable )
                {
                    if( ( ( ILogicallyDeletable )entity ).isLogicallyDeleted() )
                    {
                        entity = null;
                    }
                }
                initializeLazyAttributes( entity, clazz, initialize );
            }
            
        }
        catch( Exception e )
        {
            throw ServiceException.create( logger
                                      , "Error finding entity object of class " + clazz.getName()
                                      , e
                                      , "findByKey"
                                      , getClass()
            );
        }
        
        return entity;
   }
    
    /**
     *  Function for fetching entity objects by a natural key.
     *  Note that because we do not have a generic entity object factory, we cannot factory-create
     *  the return value; if this is used to fetch a member of a base class, the result will be
     *  of the base class, not any derived class.
     *  
     * @param <T>
     *      The entity object class
     * @param <P>
     *      The key class
     * @param clazz
     *      The entity class object
     * @param key
     *      The key
     * @param em
     *      An entityManager
     * @return
     *      The entity object if found, null if not found.
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public <T extends IEntity<P>, P extends Serializable> T findByNaturalKey(
      Class<T>                          clazz
    , String                            name
    , Object                            key
	, String...							initialize
    )
    {
        Class<T>                        entityClass;
        T                          		entity			= null;
        Query                           query;
       
        try
        {
            //
            // Locate the entity class.
            //
            entityClass = clazz;
            //
            // Find the entity.
            //
            query       = getEntityManager().createQuery( "from " + entityClass.getName() + " entity where entity." + name + " = :propValue" );
            query.setParameter( "propValue", key );
            entity      = ( T )query.getSingleResult();
            initializeLazyAttributes( entity, clazz, initialize );
        }
        catch( Exception e )    // IllegalAccessException, InstantiationException
        {
        	if( !( e instanceof NoResultException ) )
        	{
	            throw ServiceException.create( logger
	                                      , "Error finding entity object of class " + clazz.getName()
	                                      , e
	                                      , "findByKey"
	                                      , getClass()
	            );
        	}
        }
        
        return entity;
   }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends IEntity<P>, P extends Serializable> void initializeLazyAttributes(
      T									entity
    , Class<T>							entityClass
    , String...							initialize	
    )
    {
    	Object							value;
    	String[]						fields;
    	
    	for( String field : initialize )
    	{
    		if( field.contains( "." ) )
    		{
    			fields	= field.split( "\\.", 2 );
    			value	= getFieldValue( entity, entityClass, fields[0] );
    			if( value instanceof IEntity ){
    				initializeLazyAttributes( ( IEntity )value, ( Class<IEntity> )value.getClass(), fields[1] );
    			}
    			else if( value instanceof Collection ){
        			Hibernate.initialize( value );
        			if( value instanceof PersistentCollection ){
        				initializeLazyAttributes( ( Collection<IEntity> )value, ( Class<IEntity> )value.getClass(), fields[1] );
        			}
    			}
    		}
    		else
    		{
    			value	= getFieldValue( entity, entityClass, field );
    			Hibernate.initialize( value );
    		}
    	}
    }

    
	@SuppressWarnings("unchecked")
	public static <T extends IEntity<P>, P extends Serializable> void initializeLazyAttributes(
      Collection<T>						collection
    , Class<T>							entityClass
    , String...							initialize	
    )
    {
    	for( T ent : collection ){
    		initializeLazyAttributes( ent, ( Class<T> )ent.getClass(), initialize );
    	}
    }

	public static boolean isProxy( Object obj ){
		boolean							proxy	= false;
		
		if( obj instanceof ProxyObject || obj instanceof HibernateProxy ){
			proxy	= true;
		}
		
		return proxy;
	}
	
    public static Object getFieldValue(
      final Object                      target
    , final Class<?>					entityClass
    , final String						name	
    )
    {
        Object                          value;
        
        if( isProxy( target ) ){
        	return getFieldValueFromProxy( target, entityClass, name );
        }
        
        value = AccessController.doPrivileged(
                new PrivilegedAction<Object>(
                )
                {
                      public Object run(
                      )
                      {
                          Field                           field		= null;

                          try
                          {
                        	  if( !entityClass.isInterface() ){
                        		  for( Field f : entityClass.getDeclaredFields() ){
                        			  if( f.getName().equals( name ) ){
                        				  field = f;
                        				  break;
                        			  }
                        		  }
                        		  if( field == null ){
                        			  return getFieldValue( target, entityClass.getSuperclass(), name );
                        		  }
                        	  }
                        	  else{
                        		  for( Field f : target.getClass().getDeclaredFields() ){
                        			  if( f.getName().equals( name ) ){
                        				  field = f;
                        				  break;
                        			  }
                        		  }
                        		  if( field == null ){
                        			  if( target.getClass().getSuperclass() != null ){
                        				  return getFieldValue( target, target.getClass().getSuperclass(), name );
                        			  }
                        		  }
                        	  }
                              if( !field.isAccessible() )
                              {
                                  field.setAccessible( true );
                              }
                              return field.get( target );
                          }
                          catch( NullPointerException e )
                          {
                              throw new IllegalArgumentException( "Invoking " + name + " on a  null object", e );
                          }
                          catch( IllegalArgumentException e )
                          {
                              throw new IllegalArgumentException( "Invoking " + name + " on the wrong object " + target.getClass().getName(), e );
                          }
                          catch( Exception e )
                          {
                              throw new IllegalStateException( "Unable to invoke " + name, e );
                          }
                      }
                }
              );

        return value;
    }


    public static Object getFieldValueFromProxy(
      final Object                      target
    , final Class<?>					entityClass
    , final String						name	
    )
    {
        Object                          value;
        
        Hibernate.initialize( target );
        value = AccessController.doPrivileged(
                new PrivilegedAction<Object>(
                )
                {
                      public Object run(
                      )
                      {
                    	  Method        method		= null;
                          String		getter;
                          
                          try
                          {
                        	  getter	= "get" + name;
                        	  if( !entityClass.isInterface() ){
                        		  for( Method m : entityClass.getDeclaredMethods() ){
                        			  if( m.getName().equalsIgnoreCase( getter ) ){
                        				  method = m;
                        				  break;
                        			  }
                        		  }
                        		  if( method == null ){
                        			  return getFieldValueFromProxy( target, entityClass.getSuperclass(), name );
                        		  }
                        	  }
                        	  else{
                        		  for( Method m : target.getClass().getDeclaredMethods() ){
                        			  if( m.getName().equalsIgnoreCase( getter ) ){
                        				  method = m;
                        				  break;
                        			  }
                        		  }
                        		  if( method == null ){
                        			  if( target.getClass().getSuperclass() != null ){
                        				  return getFieldValueFromProxy( target, target.getClass().getSuperclass(), name );
                        			  }
                        		  }
                        	  }
                              if( !method.isAccessible() )
                              {
                                  method.setAccessible( true );
                              }
                              return method.invoke( target );
                          }
                          catch( NullPointerException e )
                          {
                              throw new IllegalArgumentException( "Invoking " + name + " on a  null object", e );
                          }
                          catch( IllegalArgumentException e )
                          {
                              throw new IllegalArgumentException( "Invoking " + name + " on the wrong object " + target.getClass().getName(), e );
                          }
                          catch( Exception e )
                          {
                              throw new IllegalStateException( "Unable to invoke " + name, e );
                          }
                      }
                }
              );

        return value;
    }
   
  
    /**
     *  Function for fetching entity objects by a natural key.
     *  Note that because we do not have a generic entity object factory, we cannot factory-create
     *  the return value; if this is used to fetch a member of a base class, the result will be
     *  of the base class, not any derived class.
     *  
     * @param <T>
     *      The entity object class
     * @param <P>
     *      The key class
     * @param clazz
     *      The entity class object
     * @param key
     *      The key
     * @param em
     *      An entityManager
     * @return
     *      The entity object if found, null if not found.
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public <T extends IEntity<P>, P extends Serializable> List<T> readByAttribute(
      Class<T>                          clazz
    , String                            name
    , Object                            key
    )
    {
        Class<T>                        entityClass;
        Query                           query;
        List<T>                         results;
        
        try
        {
            //
            // Locate the entity class.
            //
            entityClass = clazz;
            //
            // Find the entity.
            //
            query       = getEntityManager().createQuery( "from " + entityClass.getName() + " where " + name + " = :propValue" );
            query.setParameter( "propValue", key );
            results     = query.getResultList();
        }
        catch( Exception e )    // IllegalAccessException, InstantiationException
        {
            throw ServiceException.create( logger
                                      , "Error finding business objects of class " + clazz.getName()
                                      , e
                                      , "readByAttribute"
                                      , getClass()
            );
        }
        
        return results;
   }

    /**
     * Process the exception and throw a common exception.
     * 
     * @param exception
     * @param method
     * @param message
     */
    protected void handleException(
      Exception                         exception 
    , String                            method
    , String                            message
    , IEntity<?>    					ent
    ) 
    {
        
        throw ServiceException.create( logger
                                  , message
                                  , exception
                                  , method
                                  , ( ent != null ? ent.getClass() : IEntity.class )
        );
    }

    @Override
    public void flush(){
        try 
        {
			getEntityManager().flush();
		} 
        catch( Exception e ) 
        {
            handleException( e, "flush", "Flush failed.", null );
		}
    }
    
    @Override
    public <T extends IEntity<?>> void insert(
      T                                 ent
    )
    {
        IEntity<?>                      entity;
        
        validate( ent, "insert" );
        try
        {
            entity  = ent;
            getEntityManager().persist( entity );
            getEntityManager().flush();
        }
        catch( Exception e )
        {
            handleException( e, "insert", "Insert failed for entity with id:" + ent.getUniqueId(), ent );
        }
    }

    @Override
    public <T extends IEntity<?>> T merge(
      T                                 ent
    )
    {
        T                         		entity;
        
        
        entity = getEntityManager().merge( ent );
        
        return entity;
    }

    /**
     * Insert the entity object
     * 
     * @param busn
     */
    public <T extends IEntity<?>> void persist( 
      T                                 busn
    ) 
    {
        if( busn.getUniqueId() != null )
        {
            update( busn );
        }
        else
        {
            insert( busn );
        }
   }
   
    @SuppressWarnings("unchecked")
	@Override
    public <T extends IEntity<?>> T reloadEntity(
      T                                 ent
    )
    {
        T                         		entity;  
        
        entity = getEntityManager().find( ( Class<T> )ent.getClass(), ent.getUniqueId() );
        
        return entity;
    }

    @Override
    public <T extends IEntity<?>> void update(
      T                                 ent
    )
    {
        T                         		entity;
        
        validate( ent, "update" );
        try
        {
            entity  = ent;
            if( ent.getUniqueId() != null && !getEntityManager().contains( ent ) )
            {
            	entity  = merge( ent );
            	if( ent instanceof IAuditable ){
            		( ( IAuditable )ent ).setLastModifiedDate( ( ( IAuditable )entity ).getLastModifiedDate() );
            	}
            }
            getEntityManager().persist( entity );
            getEntityManager().flush();
        }
        catch( Exception e )
        {
            handleException( e, "update", "Update failed for entity with id:" + ent.getUniqueId(), ent );
        }
    }

    public <T extends IEntity<?>> void updateNoFlush(
      T                                 ent
    )
    {
        T                         		entity;
        
        validate( ent, "update" );
        try
        {
            entity  = ent;
            if( ent.getUniqueId() != null && !getEntityManager().contains( entity ) )
            {
                entity  = merge( entity );
            }
            getEntityManager().persist( entity );
        }
        catch( Exception e )
        {
            handleException( e, "updateNoFlush", "Update failed for entity with id:" + ent.getUniqueId(), ent );
        }
    }
    
    @Override
    public <T extends IEntity<?>> void validate(
      T                                 ent
    , String                            methodName
    )
    {
        validateEntity( ent, methodName );    
    }

    @Override
    public <T extends IEntity<?>> void validateEntity(
      T                                 ent
    , String                            methodName
    ) 
    {
    	Set<ConstraintViolation<T>> 	violations;
    	
    	violations	= validator.validate( ent );
    	if( !violations.isEmpty() ){
            logger.warn( "entity object validation failed." );
    		throw new ConstraintViolationException( "Validation failed " + methodName + ":", violations );
    	}
    }
}
