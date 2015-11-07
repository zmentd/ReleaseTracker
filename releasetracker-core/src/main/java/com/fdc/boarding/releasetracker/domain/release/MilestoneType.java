package com.fdc.boarding.releasetracker.domain.release;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum MilestoneType implements IEnumDisplayValue {
	Requirements( "R", "Requirements" ),
	Queued( "Q", "Queued" ),
	HLAD( "HD", "HLAD" ),
	HLE( "HE", "HLE" ),
	Slotting( "S", "Slotting" ),
	DDD( "DD", "DDD" ),
	Development( "DV", "Development" ),
	QA( "QA", "QA" ),
	UAT( "UT", "UAT" ),
	Parked( "P", "Parked" ),
	Done( "D", "Done" ),
	Other( "O", "Other" )
	;

    private static String[]              	displayValues;
    private static Map<String , MilestoneType>	map;

    static
    {
        synchronized( MilestoneType.class )
        {
            map = new HashMap<String , MilestoneType>();

            for( MilestoneType x : MilestoneType.values() )
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

            for( MilestoneType stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static MilestoneType locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;


    //  ~ Constructor **********************************************************
    MilestoneType(
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
