package com.fdc.boarding.core.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.service.exception.ServiceException;
import com.fdc.boarding.core.util.StringUtil;

/**
 * Generic service for crud operations on a entityObj.
 * 
 * This service has will invoke a lister found by name. The name is comprised 
 * of this given business objects component name + .entityObjService
 * 
 * i.e. net.intersystems.iqs.common.entityobj.note.entityObjService
 * 
 * The listener should either extend IEntityServiceAdapter or
 * implement IEntityServiceListener
 */
@Dependent
public class EntityPersistenceService extends AbstractPersistentSvc
{
    private static final long           serialVersionUID    = 1L;
    public static final String          SERVICE_POSTFIX     = ".entityObjService";
    
    @Inject
    private IEntityDao					entityDao;
    
    /**
     * 
     */
    private IExceptionHandler           exceptionHandler;

    /**
     * 
     */
    public EntityPersistenceService(
    )
    {
        super();
    }

    /**
     * 
     */
    public EntityPersistenceService(
      IExceptionHandler                 exceptionHandler
    )
    {
        super();
        this.exceptionHandler   = exceptionHandler;
    }
    
    /**
     * Insert the business object
     * 
     */
//    @Transactional
	public <P extends IEntity<?>, C extends IEntity<?>> boolean addChild( 
      P                                 parent
    , C                                 child
    , String                            attributeName
    )
    {
        boolean                         success     = true;
        boolean                         execute     = true;
       
        try
        {
            //
            // If it is a proxy, only insert dirty objects.
            //
            if( execute )
            {
                associate( parent, new ChildReference( child, attributeName ) );
                //
                // Now persist the parent.
                //
                success = persist( parent );
            }
        }
        catch( Exception e )
        {
        	e.printStackTrace();
            handleException( e );
            success = false;
        }
        
        return success;
   }
    
    /**
     * Create the association between parent and children.
     * 
     * @param parent
     * @param references
     * @return
     */
	public <P extends IEntity<?>, C extends IEntity<?>> boolean associate(
      P                                 parent
    , ChildReference...                 references 
    )
    {
        boolean                         success     = true;
        Method							mutator;
        
        for( ChildReference reference : references )
        {
            if( reference.getChild().getUniqueId() != null ){
            	reference.setChild( entityDao.merge( reference.getChild() ) );
            }
        	mutator	= getAddMutator( parent.getClass(), reference.getClass(), reference.getAttributeName() );
            if( mutator != null )
            {
            	try 
            	{
					mutator.invoke( parent, reference.getChild() );
				} 
            	catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) 
            	{
					e.printStackTrace();
					success = false;
				}   
            }
            else
            {
                //
                // throw an exception
                //
                success = false;
            }
            for( ChildReference nested : reference.getNested() )
            {
                associate( reference.getChild(), nested );
            }
        }
        
        return success;
    }
    
    /**
     * Create the association between parent and child.
     * 
     * @param parent
     * @param reference
     * @return
     */
	public <P extends IEntity<?>, C extends IEntity<?>> boolean associate(
      P                                 parent
    , ChildReference                    reference 
    )
    {
        boolean                         success     = true;
        Method							mutator;
       
        if( reference.getChild().getUniqueId() != null ){
        	reference.setChild( entityDao.merge( reference.getChild() ) );
        }
    	mutator	= getAddMutator( parent.getClass(), reference.getChild().getClass(), reference.getAttributeName() );
        if( mutator != null )
        {
        	try 
        	{
				mutator.invoke( parent, reference.getChild() );
			} 
        	catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) 
        	{
				e.printStackTrace();
				success = false;
			}   
        }
        else
        {
            throw ServiceException.create( logger
                                      , "Unable to set child relationship " + reference.getAttributeName() + " on parent class " + parent.getClass().getName()
                                      , null
                                      , "associate"
                                      , getClass()
            );
        }
  
        return success;
    }
   
    /**
     * Delete the entity object
     * 
     * @param entity
     */
	public <T extends IEntity<?>> boolean delete( 
      T                                 entity
    )
    {
        boolean                         success     = true;
        boolean                         execute     = true;
        
        try
        {
            if( execute )
            {
                //
                // Delete the business object.
                //
            	entityDao.delete( entity );
            }
        }
        catch( Exception e )
        {
            handleException( e );
            success = false;
        }
        
        return success;
    }

    /**
     * Delete the business objects
     * 
     * @param entities
     */
	@SuppressWarnings( "unchecked" )
    public <T extends IEntity<?>> boolean delete( 
      T...                              entities
    )
    {
        boolean                         success     = true;
        
        for( T entity : entities )
        {
            success &= delete( entity );
        }
        
        return success;
    }
    
    /**
     * @param exception
     */
    protected void handleException(
      Exception                         exception
    )
    {
        logger.error( "Unable to perform requested operation.", exception );
        if( exceptionHandler != null )
        {
        	exceptionHandler.handleException( exception );
        }
        else
        {
        	throw new RuntimeException( exception );
        }
    }
  
    public void flush()
    {
    	entityDao.flush();
    }
    
    /**
     * Insert the entity objects
     * 
     * @param entities
     */
//    @Transactional
	@SuppressWarnings( "unchecked" )
    public <T extends IEntity<?>> boolean insert( 
      T...                              entities
    )
    {
        boolean                         success     = true;
        
        for( T entity : entities )
        {
            success &= insert( entity );
        }
        
        return success;
    }
    
    /**
     * Insert the entity object
     * 
     * @param entity
     */
//    @Transactional
	public <T extends IEntity<?>> boolean insert( 
      T                                 entity
    )
    {
        boolean                         success     = true;
        boolean                         execute     = true;
       
        try
        {
            if( execute )
            {
                //
                // Insert the business object.
                //
                entityDao.insert( entity );
            }
        }
        catch( Exception e )
        {
            handleException( e );
            success = false;
        }
        
        return success;
   }
    
    /**
     * Persist the entity objects
     * 
     * @param entities
     */
//    @Transactional
	@SuppressWarnings( "unchecked" )
    public <T extends IEntity<?>> boolean persist( 
      T...                              entities
    )
    {
        boolean                         success     = true;
        
        for( T entity : entities )
        {
            success &= persist( entity );
        }
        
        return success;
    }

    /**
     * Insert the entity object
     * 
     * @param entity
     */
//    @Transactional
	public <T extends IEntity<?>> boolean persist( 
      T                                 entity
    )
    {
        boolean                         success     = true;

        if( entity.getUniqueId() != null )
        {
            success = update( entity );
        }
        else
        {
            success = insert( entity );
        }
        
        return success;
   }
    
    /**
     * Insert the entity object
     * 
     */
	public <P extends IEntity<?>, C extends IEntity<?>> boolean persistChild( 
      P                                 parent
    , C                                 child
    , String                            attributeName
    )
    {
        boolean                         success     = true;

        if( child.getUniqueId() != null )
        {
            success = update( child );
        }
        else
        {
            success = addChild( parent, child, attributeName );
        }
        
        return success;
    }

    /**
     * Insert the entity object
     * 
     */
	public <P extends IEntity<?>, C extends IEntity<?>> boolean persistChildren( 
      P                                 parent
    , ChildReference...                 references 
    )
    {
        boolean                         success     = true;

        success = associate( parent, references );
        if( success )
        {
            success = persistReferences( references );
            if( success )
            {
                success = persist( parent );
            }
        }
        
        return success;
    }

    /**
     * @param references
     * @return
     */
    protected boolean persistReferences(
      ChildReference...                 references 
    )
    {
        boolean                         success     = true;

        for( ChildReference reference : references )
        {
            // 
            // Owned Child, and it is an update - update child
            //
            if( reference.isOwned() )
            {
               if( reference.getChild().getUniqueId() != null )
               {
                   success  = update( reference.getChild() );
               }
            }
            else
            {
                success = persist( reference.getChild() );
            }
            if( success )
            {
                //
                // Insert the nested references.
                //
                for( ChildReference nested : references )
                {
                    persistReferences( nested.getNested() );
                }
            }
        }
        
        return success;
    }
   
    /**
     * Insert the entity object
     * 
     */
	public <P extends IEntity<?>, C extends IEntity<?>> boolean removeChild( 
      P                                 parent
    , C                                 child
    , String                            attributeName
    )
    {
        boolean                         success     = true;
        boolean                         execute     = true;
        Method							mutator;
       
        try
        {
            //
            // If it is a proxy, only insert dirty objects.
            //
            if( execute )
            {
            	mutator	= getRemoveMutator( parent.getClass(), child.getClass() );
                if( mutator != null )
                {
                	try 
                	{
                		if( mutator.getName().startsWith( "remove" ) )
                		{
                			mutator.invoke( parent, child );
                		}
                		else
                		{
                			mutator.invoke( parent, ( Object[] )null );
                		}
    				} 
                	catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) 
                	{
    					e.printStackTrace();
    					success = false;
    				}   
                }
                //
                // Parent cascades to child
                //
                success = update( parent );
            }
        }
        catch( Exception e )
        {
            handleException( e );
            success = false;
        }
        
        return success;
    }
    
    /**
     * @param exceptionHandler the exceptionHandler to set
     */
	public void setExceptionHandler( 
      IExceptionHandler                 exceptionHandler 
    )
    {
        this.exceptionHandler = exceptionHandler;
    }
    
    
    /**
     * Update the entity objects
     * 
     * @param entities
     */
	@SuppressWarnings( "unchecked" )
    public <T extends IEntity<?>> boolean update( 
      T...                              entities
    )
    {
        boolean                         success     = true;
        
        for( T entity : entities )
        {
            success &= update( entity );
        }
        
        return success;
    }
    
    /**
     * Update the entity object
     * 
     * @param entity
     */
	public <T extends IEntity<?>> boolean update( 
      T                                 entity
    )
    {
        boolean                         success     = true;
        boolean                         execute     = true;
       
        try
        {
            if( execute )
            {
                //
                // Update the business object.
                //
                entityDao.update( entity );
            }
        }
        catch( Exception e )
        {
            handleException( e );
            success = false;
        }
        
        return success;
    }

    private Method getAddMutator(
      Class<?>							parentClass
    , Class<?>							childClass
    , String							attrName
    )
    {
    	String							addName;
    	String							setName;
    	Method							mutator		= null;
    	
    	if( attrName != null )
    	{
	    	addName	= "add" + StringUtil.capitalize( attrName );
	    	setName	= "set" + StringUtil.capitalize( attrName );
	    	mutator	= getMethod( parentClass, addName, setName );
    	}
    	if( mutator == null )
    	{
	    	addName	= "add" + childClass.getSimpleName();
	    	addName	= addName.replace("Entity", "" );
	    	setName	= "set" + childClass.getSimpleName();
	    	setName	= setName.replace("Entity", "" );
    	}
    	
    	return mutator;
    }

    private Method getRemoveMutator(
      Class<?>							parentClass
    , Class<?>							childClass
    )
    {
    	String							addName;
    	String							setName;
    	Method							mutator;
    	
    	addName	= "remove" + childClass.getSimpleName();
    	addName	= addName.replace("Entity", "" );
    	setName	= "set" + childClass.getSimpleName();
    	setName	= setName.replace("Entity", "" );
    	mutator	= getMethod( parentClass, addName, setName );
    	
    	return mutator;
    }
   
    private Method getMethod( 
      Class<?>							parentClass
    , String							name
    , String							altName
    )
    {
    	Method							mutator	= null;

    	for( Method method : parentClass.getDeclaredMethods() )
    	{
    		if( method.getName().equals( name) || method.getName().equals( altName ) )
    		{
    			mutator	= method;
    			break;
    		}
    	}
    	if( mutator == null && parentClass.getSuperclass() != null )
    	{
    		mutator	= getMethod( parentClass.getSuperclass(), name, altName );
    	}
    	
    	return mutator;
    }
}
