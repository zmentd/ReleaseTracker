package com.fdc.boarding.core.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.query.Conjunction;
import com.fdc.boarding.core.query.EntityQuery;
import com.fdc.boarding.core.query.EntityQueryAp;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.transaction.annotation.Transactional;

/**
 *
 */
public class EntityReaderSvc implements IEntityReaderSvc, Serializable {
	private static final long 			serialVersionUID = 1L;

	@Inject
	private EntityQuery					entityQuery;

	@Inject
	private IEntityDao					entityDao;
	
    /**
     * 
     */
    public EntityReaderSvc(
    )
    {
        //
        // Default constructor.
        //
        super();
    }
    
    @Override
    @Transactional
    public <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
  	, String...							initialize
    ) throws QueryException
    {
        List<T>                         busns;
        
        busns   = find( entityClass, null, null, initialize );
        
        return busns;
    }
 
    @Override
    @Transactional
    public <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
    , String                            orderBy
    , Boolean                           descending
	, String...							initialize
    ) throws QueryException
    {
        EntityQueryAp                  	accessPath;
        List<T>                         results;
        
        accessPath  = new EntityQueryAp( entityClass );
        if( orderBy != null )
        {
            accessPath.setSort( orderBy );
            if( descending != null )
            {
                accessPath.setDescending( descending );
            }
        }
        results   = entityQuery.find( accessPath, 0, -1 );
        if( initialize != null && initialize.length > 0 )
        {
	        for( T e : results )
	        {
	        	EntityDao.initializeLazyAttributes( e, entityClass, ( String[] )initialize );
	        }
        }
        
        return results;
    }
    
    @Override
    @Transactional
    public <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
    , String                            orderBy
    , Boolean                           descending
    , Conjunction                       conjunction
	, String...							initialize
    ) throws QueryException
    {
    	EntityQueryAp                  	accessPath;
        List<T>                         results;
        
        accessPath  = new EntityQueryAp( entityClass );
        accessPath.setConjunction( conjunction );
        if( orderBy != null )
        {
            accessPath.setSort( orderBy );
            if( descending != null )
            {
                accessPath.setDescending( descending );
            }
        }
        results   = entityQuery.find( accessPath, 0, -1 );
        if( initialize != null && initialize.length > 0 )
        {
	        for( T e : results )
	        {
	        	EntityDao.initializeLazyAttributes( e, entityClass, ( String[] )initialize );
	        }
        }
        
        return results;
    }
    
    @Override
    @Transactional
    public <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
    , String                            orderBy
    , Boolean                           descending
    , Restriction                       restrictions
	, String...							initialize
    ) throws QueryException
    {
    	EntityQueryAp                  	accessPath;
        List<T>                     	results;
        
        accessPath  = new EntityQueryAp( entityClass );
        accessPath.setRestrictions( restrictions );
        if( orderBy != null )
        {
            accessPath.setSort( orderBy );
            if( descending != null )
            {
                accessPath.setDescending( descending );
            }
        }
        results   = entityQuery.find( accessPath, 0, -1 );
        if( initialize != null && initialize.length > 0 )
        {
	        for( T e : results )
	        {
	        	EntityDao.initializeLazyAttributes( e, entityClass, ( String[] )initialize );
	        }
        }
        
        return results;
    }

	@Override
    @Transactional
	public <T extends IEntity<P>, P extends Serializable> T findByKey(
	  Class<T> 							clazz
	, P 								key
	, String...							initialize
	) 
	{
		return entityDao.findByKey( clazz, key, initialize );
	}

	@Override
    @Transactional
	public <T extends IEntity<P>, P extends Serializable> T findByNaturalKey(
	  Class<T> 							clazz
	, String 							name
	, Object 							value
	, String...							initialize
	) 
	{
		return entityDao.findByNaturalKey( clazz, name, value, initialize );
	}

	@Override
    @Transactional
	public <T extends IEntity<P>, P extends Serializable> List<T> readByAttribute(
			Class<T> clazz, String name, Object key) {
		return entityDao.readByAttribute( clazz, name, key );
	}
}