package com.fdc.boarding.core.query;

import java.util.List;

import javax.enterprise.context.Dependent;

import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fdc.boarding.core.domain.IActivatable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.domain.ILogicallyDeletable;
import com.fdc.boarding.core.domain.IUniqueIdProvider;
import com.fdc.boarding.core.domain.IVersioned;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.util.DateTimeUtil;


/**
 *
 */
@Dependent
public class EntityQuery extends AbstractQuerySvc
{
    private static final long           serialVersionUID    = 1L;

    /**
     *
     */
    public EntityQuery(
    )
    {
        //
        // Default constructor.
        //
        super();
    }

    /**
     * Build the criteria.
     *
     * @param accessPath
     * @return
     */
    protected <T extends IEntity<?>, A extends EntityQueryAp> Criteria buildCriteria(
      A                                 accessPath
    )
    {
        Class<?>                        persistentClass;
        Criteria                        criteria;
        Criterion                       restrict;
        Criterion                       activeRestrictions;
        Criterion                       versionedRestrictions;
        Criterion                       deleteRestrictions;

        persistentClass         = accessPath.getEntityClass();
        criteria                = getSession().createCriteria( persistentClass );
        activeRestrictions      = getActiveRestrictions( accessPath );
        deleteRestrictions      = getLogicallyDeletedRestrictions( accessPath );

        //
        // Add the aliases
        //
        for( String alias : accessPath.getAliases() )
        {
            criteria.createAlias( alias, alias );
        }
        
        for( AliasSpecification alias : accessPath.getAliasSpecifications() )
        {
            criteria.createAlias( alias.getAssociationPath(), alias.getAlias(), alias.getJoinType().specification() );
        }
        if( deleteRestrictions != null )
        {
            criteria        = criteria.add( deleteRestrictions );    
        }
        if( activeRestrictions != null )
        {
            criteria        = criteria.add( activeRestrictions );    
        }
        versionedRestrictions   = getVersionedRestrictions( accessPath );
        if( versionedRestrictions != null )
        {
            criteria        = criteria.add( versionedRestrictions );    
        }
       
        if( accessPath.getRestrictions() != null )
        {
            restrict        = toCriterion( accessPath.getRestrictions() );
            if( restrict != null )
            {
                criteria    = criteria.add( restrict );
            }
        }
        else if( accessPath.getConjunction() != null )
        {
            restrict        = toCriterion( accessPath.getConjunction() );
            if( restrict != null )
            {
                criteria    = criteria.add( restrict );
            }
        }

        return criteria;
    }

    /**
     * Get a page of data.
     *
     * @param accessPath
     * @param firstResult
     * @param readCount
     * @return
     * @throws QueryException
     */
    @Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public <T extends IUniqueIdProvider, A extends AbstractAp> List<T> find(
      A                                 accessPath
    , int                               firstResult
    , int                               readCount
    ) throws QueryException
    {
        Criteria                        criteria;
        List<T>                         entities;
        String                          abapSort        = null;
        EntityQueryAp					eap;
        
        try
        {
        	eap			= ( EntityQueryAp )accessPath;
            criteria    = buildCriteria( eap );
            if( accessPath.getSort() != null && !accessPath.getSort().isEmpty() )
            {
                abapSort    = accessPath.getSort();
                if( accessPath.isDescending() )
                {
                    criteria.addOrder( Order.desc( accessPath.getSort() ).ignoreCase().nulls( NullPrecedence.LAST ) );
                }
                else
                {
                    criteria.addOrder( Order.asc( accessPath.getSort() ).ignoreCase().nulls( NullPrecedence.LAST ) );
                }
            }
            //
            // If multiple sorts are available, add them.
            //
            if( eap.getMultiSorts() != null && !eap.getMultiSorts().isEmpty() )
            {
                for( SortOrder so : eap.getMultiSorts() )
                {
                    if( abapSort != null && abapSort.equals( so.getName() ) )
                    {
                        //
                        // Skip this one as the name matches the single sort.
                        //
                        continue;
                    }
                    if( so.isDescending() )
                    {
                        criteria.addOrder( Order.desc( so.getName() ).ignoreCase().nulls( NullPrecedence.LAST ) );
                    }
                    else
                    {
                        criteria.addOrder( Order.asc( so.getName() ).ignoreCase().nulls( NullPrecedence.LAST ) );
                    }
                }
            }
            criteria.setFirstResult( firstResult );
            if( readCount != -1 )
            {
                criteria.setMaxResults( readCount );
            }
            entities    = criteria.list();
        }
        catch( Exception e )
        {
            throw QueryException.create( logger
                                       , "Query execution failed for access path:" + accessPath
                                       , e
                                       , "find"
                                       , EntityQuery.class
                                       , QueryException.QUERY_FAILED
                                       );
        }

        return entities;
    }

    /**
     * Build the criteria.
     * 
     * @param accessPath
     * @return
     */
    protected <T extends IEntity<?>, A extends EntityQueryAp> Criterion getActiveRestrictions(
      A                                 accessPath   
    )
    {
        Criterion                       criterion       = null;
        boolean                         activeAttrs;
        
        activeAttrs         = IActivatable.class.isAssignableFrom( accessPath.getEntityClass() );
        if( activeAttrs && accessPath.active() )
        {
            criterion = Restrictions.eq( "active" , true );
        }
        
        return criterion;
    }

    /**
     * Build the criteria.
     * 
     * @param accessPath
     * @return
     */
    protected <T extends IEntity<?>, A extends EntityQueryAp> Criterion getLogicallyDeletedRestrictions(
      A                                 accessPath   
    )
    {
        Criterion                       criterion               = null;
        boolean                         hasClass;
        
        hasClass    = ILogicallyDeletable.class.isAssignableFrom( accessPath.getEntityClass() )                        ||
                      ILogicallyDeletable.class.isAssignableFrom( accessPath.getEntityClass() )
        ;
        if( hasClass && !accessPath.includeLogicallyDeleted )
        {
            criterion = Restrictions.isNull( "logicallyDeletedTimestamp" );
        }
        
        return criterion;
    }

    /**
     * @param accessPath
     * @return
     */
    @Override
    @SuppressWarnings("rawtypes")
    public <T extends IUniqueIdProvider, A extends AbstractAp> int getCount(
       A                                accessPath
    )
    {
        int                             count       = 0;
        Criteria                        criteria;
        Object                          result;

        criteria    = buildCriteria( ( EntityQueryAp )accessPath );
        criteria.setProjection( Projections.rowCount() );
        result      = criteria.uniqueResult();
        if( result != null )
        {
            count       = ( ( Number )result ).intValue();
        }

        return count;

    }

    /**
     * Build the criteria.
     * 
     * @param accessPath
     * @return
     */
    protected <T extends IEntity<?>, A extends EntityQueryAp> Criterion getVersionedRestrictions(
      A                                 accessPath   
    )
    {
        Criterion                       criterion       = null;
        boolean                         versioned;
        
        versioned   = IVersioned.class.isAssignableFrom( accessPath.getEntityClass() );
        if( versioned && accessPath.onlyCurentAndFuture() )
        {
            //
            // Only include busns that have not expired.
            // Expiration timestamp after now or null
            //
            criterion   = Restrictions.or( Restrictions.gt( "expirationTimestamp"
                                                          , DateTimeUtil.getCurrentUtcDateTime() 
                                                          )
                                         , Restrictions.isNull( "expirationTimestamp" ) 
            );
        }
        else if( versioned && accessPath.onlyCurrent() )
        {
            //
            // Only include busns that are current.
            // Effective timestamp is before now and the Expiration timestamp after now or null
            //
            criterion   = Restrictions.and( Restrictions.lt( "effectiveTimestamp"
                                                           , DateTimeUtil.getCurrentUtcDateTime() 
                                                           )
                                          , Restrictions.or( Restrictions.gt( "expirationTimestamp"
                                                                            , DateTimeUtil.getCurrentUtcDateTime() 
                                                                            )
                                                           , Restrictions.isNull( "expirationTimestamp" ) 
                                                           ) 
            );
        }
        
        return criterion;
    }


    protected Criterion toCriterion(
      AbstractCriteria                  criteria
    )
    {
        if( criteria instanceof Restriction )
        {
            return toCriterion( ( Restriction )criteria );
        }

        return toCriterion( ( Conjunction )criteria );

    }

    protected Criterion toCriterion(
      Conjunction                       conjunction
    )
    {
        Criterion                       criterion   = null;

        if( Conjunction.TYPE.AND.equals( conjunction.getType() ) )
        {
            criterion   = Restrictions.and( toCriterion( conjunction.getLeftHandSide() ), toCriterion( conjunction.getRightHandSide() ) );
        }
        else if( Conjunction.TYPE.OR.equals( conjunction.getType() ) )
        {
            criterion   = Restrictions.or( toCriterion( conjunction.getLeftHandSide() ), toCriterion( conjunction.getRightHandSide() ) );
        }

        return criterion;

    }

    protected Criterion toCriterion(
      Restriction                       restriction
    )
    {
        Criterion                       restrict    = null;

        if( Restriction.TYPE.EQ.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.eq( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.ILIKE.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.ilike( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.GE.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.ge( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.GT.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.gt( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.LE.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.le( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.LIKE.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.like( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.LT.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.lt( restriction.getAttribute(), restriction.getValue() );
        }
        else if( Restriction.TYPE.NULL.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.isNull( restriction.getAttribute() );
        }
        else if( Restriction.TYPE.NOT_EQ.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.not( Restrictions.eq( restriction.getAttribute(), restriction.getValue() ) );
        }
        else if( Restriction.TYPE.NOT_NULL.equals( restriction.getType() ) )
        {
            restrict    = Restrictions.isNotNull( restriction.getAttribute() );
        }

        return restrict;

    }

}
