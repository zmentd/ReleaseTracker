package com.fdc.boarding.releasetracker.domain.common;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum CommentType implements IEnumDisplayValue{
	Public( "P", "Public" ),
	Team( "T", "Team"  ),
	User( "U", "User" ),
	;

    private static String[]              		displayValues;
    private static Map<String , CommentType>	map;

    static
    {
        synchronized( CommentType.class )
        {
            map = new HashMap<String , CommentType>();

            for( CommentType x : CommentType.values() )
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

            for( CommentType stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static CommentType locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;

    //  ~ Constructor **********************************************************
    CommentType(
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