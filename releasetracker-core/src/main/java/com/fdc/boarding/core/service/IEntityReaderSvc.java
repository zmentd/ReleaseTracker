package com.fdc.boarding.core.service;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.query.Conjunction;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.query.exception.QueryException;


public interface IEntityReaderSvc
{
	   
    /**
     * @param clazz
     * @param key
     * @return
     */
    public <T extends IEntity<P>, P extends Serializable> T findByKey(
      Class<T>                        	clazz
    , P                               	key
	, String...							initialize
    );
    
    /**
     *  Function for fetching entity objects by a natural key.
     *  Note that because we do not have a generic entity object factory, we cannot factory-create
     *  the return value; if this is used to fetch a member of a base class, the result will be
     *  of the base class, not any derived class.
     *  
     * @param <T>
     *      The business object class
     * @param <P>
     *      The key class
     * @param clazz
     *      The business class object
     * @param key
     *      The key
     * @param em
     *      An entityManager
     * @return
     *      The entity object if found, null if not found.
     */
    public <T extends IEntity<P>, P extends Serializable> T findByNaturalKey(
      Class<T>                          clazz
    , String                            name
    , Object                            value
    , String...							initialize
    );
    
    /**
     * @param clazz
     * @param name
     * @param key
     * @return
     */
    public <T extends IEntity<P>, P extends Serializable> List<T> readByAttribute(
      Class<T>                          clazz
    , String                            name
    , Object                            key
    );

    /**
     * Find all entities. Default ordering and no proxies returned.
     * 
     * @param entityClass
     * @return
     * @throws QueryException
     */
    public abstract <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
  	, String...							initialize
    ) throws QueryException;

    /**
     * Find all entities using the given order.
     * 
     * @param entityClass
     * @param orderBy
     * @param descending
     * @return
     * @throws QueryException
     */
    public abstract <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
    , String                            orderBy
    , Boolean                           descending
	, String...							initialize
    ) throws QueryException;
    
    /**
     * Find all entities using the given order.
     * 
     * @param entityClass
     * @param orderBy
     * @param descending
     * @return
     * @throws QueryException
     */
    public <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
    , String                            orderBy
    , Boolean                           descending
    , Restriction                       restrictions
	, String...							initialize
    ) throws QueryException;
    
    /**
     * Find all entities using the given order.
     * 
     * @param entityClass
     * @param orderBy
     * @param descending
     * @return
     * @throws QueryException
     */
    public <T extends IEntity<P>, P extends Serializable> List<T> find(
      Class<T>                          entityClass
    , String                            orderBy
    , Boolean                           descending
    , Conjunction                       conjunction   
	, String...							initialize
    ) throws QueryException;

}