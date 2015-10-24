package com.fdc.boarding.core.query;

import java.io.Serializable;

/**
 *
 */
public class SortOrder implements Serializable
{
    private static final long           serialVersionUID        = 1L;

    /**
     * @param name
     * @return
     */
    public static SortOrder sortAscending(
      String                            name
    )
    {
        return new SortOrder( name, false );
    }

    /**
     * @param name
     * @return
     */
    public static SortOrder sortDescending(
      String                            name
    )
    {
        return new SortOrder( name, true );
    }
    
    private String                      name;
    private boolean                     descending;
    
    
    /**
     * 
     */
    public SortOrder(
    )
    {
        super();
    }
    
    /**
     * @param name
     * @param descending
     */
    public SortOrder(
      String                            name
    , boolean                           descending 
    )
    {
        super();
        this.name       = name;
        this.descending = descending;
    }

    /**
     * @return the name
     */
    public String getName(
    )
    {
        return name;
    }

    /**
     * @return the descending
     */
    public boolean isDescending(
    )
    {
        return descending;
    }

    /**
     * @param descending the descending to set
     */
    public void setDescending( 
      boolean                           descending 
    )
    {
        this.descending = descending;
    }

    /**
     * @param name the name to set
     */
    public void setName( 
      String                            name 
    )
    {
        this.name = name;
    }
}
