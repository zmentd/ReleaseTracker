package com.fdc.boarding.core.datadef;

import java.util.HashMap;
import java.util.Map;

public enum DateFormat implements IEnumDisplayValue
{
    //  ~ Static fields/initializers *******************************************

      MDY         ( "MM/dd/yyyy"   , "MM/dd/yyyy" )
    , DMY         ( "dd/MM/yyyy"   , "dd/MM/yyyy" )
    , YMD         ( "yyyy/MM/dd"   , "yyyy/MM/dd" )
    ;

    private static String[]                   displayValues;
    private static Map<String , DateFormat>   map;

    static
    {
        synchronized( DateFormat.class )
        {
            map = new HashMap<>();

            for( DateFormat x : DateFormat.values() )
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

            for( DateFormat stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static DateFormat locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;


    //  ~ Constructor **********************************************************
    DateFormat(
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

