package com.fdc.boarding.core.query;

import java.io.Serializable;

import com.fdc.boarding.core.log.LoggerProxy;

public abstract class AbstractAp implements Serializable
{
    private static final long           serialVersionUID        = 1L;
    protected static final LoggerProxy  logger                  = LoggerProxy.getLogger( AbstractAp.class );


    //  ~ Instance fields ******************************************************
    protected String                    sort                    = "";
    protected boolean                   descending              = false;
    protected boolean                   includeLogicallyDeleted = false;
    
    //  ~ Constructor **********************************************************
    public AbstractAp(
    )
    {
        super();
    }


    //  ~ Methods **************************************************************
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(
      Object                            obj
    )
    {
        final AbstractAp                other;

        if( this == obj )
        {
            return true;
        }
        if( obj == null )
        {
            return false;
        }
        if( getClass() != obj.getClass() )
        {
            return false;
        }
        other = ( AbstractAp )obj;
        if( descending != other.descending )
        {
            return false;
        }
        if( sort == null )
        {
            if( other.sort != null )
            {
                return false;
            }
        }
        else
        if( ! sort.equals( other.sort ) )
        {
            return false;
        }

        return true;
    }

    //  ~ Getters **************************************************************
    /**
     * @return
     *    The sort
     */
    public String getSort(
    )
    {
        return sort;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode(
    )
    {
        final int                       prime   = 31;
        int                             result  = 1;

        result = prime * result + ( descending ? 1231 : 1237 );
        result = prime * result + ( ( sort == null ) ? 0 : sort.hashCode() );

        return result;
    }

    /**
     * @return
     *    The descending
     */
    public boolean isDescending(
    )
    {
        return descending;
    }

    /**
     * @return the includeLogicallyDeleted
     */
    public boolean isIncludeLogicallyDeleted(
    )
    {
        return includeLogicallyDeleted;
    }

    /**
     * Reset the query.
     */
    public void reset(
    )
    {
        setDescending( false );
    }

    //  ~ Setters **************************************************************
    /**
     * @param descending
     *    The descending to set
     */
    public void setDescending(
      boolean                           descending
    )
    {
        this.descending = descending;
    }


    /**
     * @param includeLogicallyDeleted the includeLogicallyDeleted to set
     */
    public void setIncludeLogicallyDeleted( 
      boolean                           includeLogicallyDeleted 
    )
    {
        this.includeLogicallyDeleted = includeLogicallyDeleted;
    }


    /**
     * @param sort
     *    The sort to set
     */
    public void setSort(
      String                            sort
    )
    {
        this.sort = sort;
    }
}
