package com.fdc.boarding.releasetracker.domain.idea;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum IdeaGroupBy implements IEnumDisplayValue {
	Release( "Release", "Release" ),
	Phase( "Phase", "Phase" ),
	MilestoneStatus( "MilestoneStatus", "Milestone Status" ),
	ReleaseStatus( "ReleaseStatus", "Release Status" )
	;
    private static String[]              	displayValues;
    private static Map<String , IdeaGroupBy>	map;

    static
    {
        synchronized( IdeaGroupBy.class )
        {
            map = new HashMap<String , IdeaGroupBy>();

            for( IdeaGroupBy x : IdeaGroupBy.values() )
            {
                map.put( x.toString() , x );
            }
        }
    }

    public synchronized static String[] displayValues(
    )
    {
        int                             indx = 0;

        if( displayValues == null )
        {
            displayValues = new String[ map.keySet().size() ];

            for( IdeaGroupBy stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static IdeaGroupBy locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;

    //  ~ Constructor **********************************************************
    IdeaGroupBy(
      String                            value
    , String                            display
    )
    {
        this.value      = value;
        this.display    = display;
    }


    //  ~ Methods **************************************************************
    @Override
    public String display(
    )
    {
        return display;
    }
    
    public void set(
      String                            value
    )
    {
        this.value = value;
    }

    @Override
    public String toString(
    )
    {
        return value;
    }

    @Override
    public String value(
    )
    {
        return value;
    }

	@Override
	public IEnumDisplayValue locateByDisplay(String value) {
		return map.get( value );
	}
}
