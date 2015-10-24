package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;


public enum StatusType implements IEnumDisplayValue {
	Queued( "Q", "Queued" ),
	Reviewing( "R", "Reveiweing" ),
	InProgress( "IP", "In Progress" ),
	PendingApproval( "PA", "Pending Approval" ),
	Complete( "C", "Complete" ),
	Waiting( "W", "Waiting" ),
	OnHold( "OH", "On Hold" ),
	Cancelled( "CA", "Cancelled" ),
	Other( "O", "Other" )
	
	;

    private static String[]                   displayValues;
    private static Map<String , StatusType>   map;

    static
    {
        synchronized( StatusType.class )
        {
            map = new HashMap<String , StatusType>();

            for( StatusType x : StatusType.values() )
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

            for( StatusType stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static StatusType locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;


    //  ~ Constructor **********************************************************
    StatusType(
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
