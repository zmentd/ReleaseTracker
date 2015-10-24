package com.fdc.boarding.core.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *  Sorting:
 *      There are 2 means of adding sort order.
 *          1.Using AbstractAp.setSort()
 *          2.Using EntityQueryAp.addSort*
 */
public class EntityQueryAp extends AbstractAp
{
    private static final long           serialVersionUID    = 1L;

    private Restriction                 restrictions;
    private Class<?>                    entityClass;
    private boolean                     onlyCurrent         = false;
    private boolean                     onlyCurentAndFuture = false;
    private boolean                     active              = false;
    private Conjunction                 conjunction;
    private List<String>                aliases             = new ArrayList<String>();
    private List<AliasSpecification>    aliasSpecifications = new ArrayList<>();
    private Set<SortOrder>              multiSorts          = new HashSet<>();
    
    /**
     *
     */
    public EntityQueryAp(
    )
    {
        super();
    }

    /**
     *
     */
    public EntityQueryAp(
      Class<?>                          busnObjClass
    )
    {
        super();
        this.entityClass   = busnObjClass;
    }

    /**
     * @return
     *    The active
     */
    public boolean active(
    )
    {
        return active;
    }
    /**
     * @param alias
     */
    public void addAlias(
      AliasSpecification                         alias        
    )
    {
        aliasSpecifications.add( alias );
    }


    /**
     * @param alias
     */
    public void addAlias(
      String                            alias
    )
    {
        aliases.add( alias );
    }

    /**
     * @param alias
     */
    public void addAlias(
      String                            associationPath
    , String                            alias
    , JoinType                          joinType
    )
    {
        aliasSpecifications.add( new AliasSpecification( associationPath, alias, joinType ) );
    }

    /**
     * @param sortOrder
     */
    public void addSort(
      SortOrder                         sortOrder
    )
    {
        multiSorts.add( sortOrder );
    }

    /**
     * @param name
     */
    public void addSortAsc(
      String                            name
    )
    {
        multiSorts.add( SortOrder.sortAscending( name ) );
    }

    /**
     * @param name
     */
    public void addSortDesc(
      String                            name
    )
    {
        multiSorts.add( SortOrder.sortDescending( name ) );
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( 
      Object                            obj 
    )
    {
        EntityQueryAp                  other;
        
        if( this == obj )
        {
            return true;
        }
        if( !super.equals( obj ) )
        {
            return false;
        }
        if( getClass() != obj.getClass() )
        {
            return false;
        }
        
        other = ( EntityQueryAp )obj;
        if( active != other.active )
        {
            return false;
        }
        if( aliasSpecifications == null )
        {
            if( other.aliasSpecifications != null )
            {
                return false;
            }
        }
        else if( !aliasSpecifications.equals( other.aliasSpecifications ) )
        {
            return false;
        }
        if( aliases == null )
        {
            if( other.aliases != null )
            {
                return false;
            }
        }
        else if( !aliases.equals( other.aliases ) )
        {
            return false;
        }
        if( entityClass == null )
        {
            if( other.entityClass != null )
            {
                return false;
            }
        }
        else if( !entityClass.equals( other.entityClass ) )
        {
            return false;
        }
        if( conjunction == null )
        {
            if( other.conjunction != null )
            {
                return false;
            }
        }
        else if( !conjunction.equals( other.conjunction ) )
        {
            return false;
        }
        if( onlyCurentAndFuture != other.onlyCurentAndFuture )
        {
            return false;
        }
        if( onlyCurrent != other.onlyCurrent )
        {
            return false;
        }
        if( restrictions == null )
        {
            if( other.restrictions != null )
            {
                return false;
            }
        }
        else if( !restrictions.equals( other.restrictions ) )
        {
            return false;
        }
        
        return true;
    }

    /**
     * @param alias
     */
    public List<String> getAliases(
    )
    {
        return aliases;
    }

    /**
     * @param alias
     */
    public List<AliasSpecification> getAliasSpecifications(
    )
    {
        return aliasSpecifications;
    }

    /**
     * @return
     *    The entityClass
     */
    public Class<?> getEntityClass(
    )
    {
        return entityClass;
    }

    /**
     * @return
     *    The conjunction
     */
    public Conjunction getConjunction(
    )
    {
        return conjunction;
    }

    /**
     * @return the multiSorts
     */
    public Set<SortOrder> getMultiSorts(
    )
    {
        return multiSorts;
    }

    /**
     * @return
     *    The restrictions
     */
    public Restriction getRestrictions(
    )
    {
        return restrictions;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode(
    )
    {
        final int                       prime       = 31;
        int                             result      = super.hashCode();
        
        result = prime * result + ( active ? 1231 : 1237 );
        result = prime * result + ( ( aliasSpecifications == null ) ? 0 : aliasSpecifications.hashCode() );
        result = prime * result + ( ( aliases == null ) ? 0 : aliases.hashCode() );
        result = prime * result + ( ( entityClass == null ) ? 0 : entityClass.hashCode() );
        result = prime * result + ( ( conjunction == null ) ? 0 : conjunction.hashCode() );
        result = prime * result + ( onlyCurentAndFuture ? 1231 : 1237 );
        result = prime * result + ( onlyCurrent ? 1231 : 1237 );
        result = prime * result + ( ( restrictions == null ) ? 0 : restrictions.hashCode() );
        
        return result;
    }

    /**
     * @return
     *    The onlyCurentAndFuture
     */
    public boolean onlyCurentAndFuture(
    )
    {
        return onlyCurentAndFuture;
    }

    /**
     * @return
     *    The onlyCurrent
     */
    public boolean onlyCurrent(
    )
    {
        return onlyCurrent;
    }

    /**
     * @param active
     *    The active to set
     */
    public void setActive(
      boolean                           active
    )
    {
        this.active = active;
    }

    /**
     * @param entityClass
     *    The entityClass to set
     */
    public void setEntityClass(
      Class<?>                          busnObjClass
    )
    {
        this.entityClass = busnObjClass;
    }

    /**
     * @param conjunction
     *    The conjunction to set
     */
    public void setConjunction(
      Conjunction                       conjunction
    )
    {
        this.conjunction = conjunction;
    }

    /**
     * @param multiSorts the multiSorts to set
     */
    public void setMultiSorts( 
      Set<SortOrder>                    multiSorts 
    )
    {
        this.multiSorts = multiSorts;
    }

    /**
     * @param onlyCurentAndFuture
     *    The onlyCurentAndFuture to set
     */
    public void setOnlyCurentAndFuture(
      boolean                           onlyCurentAndFuture
    )
    {
        this.onlyCurentAndFuture = onlyCurentAndFuture;
    }

    /**
     * @param onlyCurrent
     *    The onlyCurrent to set
     */
    public void setOnlyCurrent(
      boolean                           onlyCurrent
    )
    {
        this.onlyCurrent = onlyCurrent;
    }

    /**
     * @param restrictions
     *    The restrictions to set
     */
    public void setRestrictions(
      Restriction                       restrictions
    )
    {
        this.restrictions = restrictions;
    }
}
