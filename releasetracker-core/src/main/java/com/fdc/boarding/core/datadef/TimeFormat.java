package com.fdc.boarding.core.datadef;

import java.util.HashMap;
import java.util.Map;

/**
*
*/
public enum TimeFormat implements IEnumDisplayValue
{
    //  ~ Static fields/initializers *******************************************
      HMSA    ( "hh:mm:ss a"  , "hh:mm:ss a (12 hour clock)"  )
    , HMS     ( "HH:mm:ss"    , "HH:mm:ss (24 hour clock)"    )
    ;

    private static String[]                   displayValues;
    private static Map<String , TimeFormat>   map;

    static
    {
        synchronized( TimeFormat.class )
        {
            map = new HashMap<>();

            for( TimeFormat x : TimeFormat.values() )
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

            for( TimeFormat stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static TimeFormat locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;


    //  ~ Constructor **********************************************************
    TimeFormat(
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

