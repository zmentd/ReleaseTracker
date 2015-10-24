package com.fdc.boarding.core.service;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.core.domain.IEntity;

public interface IEntityDao
{
    /**
     * @param ent
     */
    public abstract <T extends IEntity<?>> void delete(
      T                                 ent 
    );
   
    /**
     * @param clazz
     * @param key
     * @return
     */
    public <T extends IEntity<P>, P extends Serializable> T findByKey(
        Class<T>                        clazz
    ,   P                               key
	, String...							initialize
    );
    
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
     *      The business object if found, null if not found.
     */
    public <T extends IEntity<P>, P extends Serializable> T findByNaturalKey(
      Class<T>                          clazz
    , String                            name
    , Object                            value
	, String...							initialize
    );

    /**
     * @param ent
     */
    public abstract <T extends IEntity<?>> void insert(
      T                                 ent 
    );

    public void flush();
    
    /**
     * @param ent
     */
    public abstract <T extends IEntity<?>> T merge(
      T                                 ent 
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
     * @param ent
     */
    public abstract <T extends IEntity<?>> T reloadEntity(
      T                                 ent 
    );

    /**
     * @param ent
     */
    public abstract <T extends IEntity<?>> void update(
      T                                 ent 
    );

    /**
     * @param methodName
     */
    public abstract <T extends IEntity<?>> void validate(
      T                                 ent
    , String                            methodName 
    );

    /**
     * Validate the entity object.
     *
     */
    public abstract <T extends IEntity<?>> void validateEntity(
      T                                 ent
    , String                            methodName 
    );
}