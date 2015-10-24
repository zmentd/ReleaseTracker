package com.fdc.boarding.releasetracker.domain.idea;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum DaysToTargetStatus implements IEnumDisplayValue {
	Green( "Green", "Green" ),
	Yellow( "Yellow", "Yellow" ),
	Red( "Red", "Red" );
	;
	
	public static DaysToTargetStatus locateByDays( int days ){
		if( days >= 0 ){
			return DaysToTargetStatus.Green;
		}
		if( days == 0 ){
			return DaysToTargetStatus.Yellow;
		}

		return DaysToTargetStatus.Red;
	}
    private static String[]              	displayValues;
    private static Map<String , DaysToTargetStatus>	map;

    static
    {
        synchronized( DaysToTargetStatus.class )
        {
            map = new HashMap<String , DaysToTargetStatus>();

            for( DaysToTargetStatus x : DaysToTargetStatus.values() )
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

            for( DaysToTargetStatus stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static DaysToTargetStatus locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;

    //  ~ Constructor **********************************************************
    DaysToTargetStatus(
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
