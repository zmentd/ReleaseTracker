package com.fdc.boarding.core.query;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.fdc.boarding.core.domain.IUniqueIdProvider;
import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.core.query.exception.QueryException;

@SuppressWarnings("rawtypes")
public abstract class AbstractQuerySvc implements Serializable
{
    private static final long           serialVersionUID    = 1L;

    protected LoggerProxy               logger              = LoggerProxy.getLogger( getClass() );

    @Inject
    private EntityManager               entityManager;

    /**
     *
     */
    public AbstractQuerySvc(
    )
    {
        //
        // Default constructor.
        //
        super();
    }

    /**
     * @param accessPath
     * @return
     * @throws QueryException
     */
    public abstract <T extends IUniqueIdProvider, A extends AbstractAp> int getCount(
      A                                 accessPath
    ) throws QueryException;


    /**
     * @param accessPath
     * @param firstResult
     * @param readCount
     * @return
     * @throws QueryException
     */
    public abstract <T extends IUniqueIdProvider, A extends AbstractAp> List<T> find(
      A                                 accessPath
    , int                               firstResult
    , int                               readCount
    ) throws QueryException;

    /**
     * @return
     */
    public EntityManager getEntityManager(
    )
    {
        return entityManager;

    }

    /**
     * @return
     */
    public Session getSession(
    )
    {
        return entityManager.unwrap( Session.class );
    }

    /**
     * @param entityManager
     *    The entityManager to set
     */
    public void setEntityManager( EntityManager entityManager )
    {
        this.entityManager = entityManager;
    }

}
