package com.fdc.boarding.core.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 */
public abstract class AbstractPersistentSvc extends AbstractSvc
{
    private static final long           serialVersionUID    = 1L;

    @Inject
    private EntityManager               entityManager;
    
    /**
     * @return
     */
    public EntityManager getEntityManager(
    )
    {
        return entityManager;
    }
}
