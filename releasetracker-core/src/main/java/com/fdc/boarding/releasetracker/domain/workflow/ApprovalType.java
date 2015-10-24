package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum ApprovalType implements IEnumDisplayValue {
	Development( "D", "Development" ),
	Business( "B", "Business" ),
	Other( "O", "Other" )
	;

    private static String[]              	displayValues;
    private static Map<String , ApprovalType>	map;

    static
    {
        synchronized( ApprovalType.class )
        {
            map = new HashMap<String , ApprovalType>();

            for( ApprovalType x : ApprovalType.values() )
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

            for( ApprovalType stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static ApprovalType locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;


    //  ~ Constructor **********************************************************
    ApprovalType(
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
