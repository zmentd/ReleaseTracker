package com.fdc.boarding.releasetracker.domain.common;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum Rom implements IEnumDisplayValue{
	XSmall( "XS", "X Small", 0, 49 ),
	Small( "S", "Small", 50, 249 ),
	Medium( "M", "Medium", 250, 499 ),
	Large( "L", "Large", 500, 999 ),
	XLarge( "XL", "X Large", 1000, 2499 ),
	XXLarge( "XXL", "XX Large", 2500, 4999 ),
	XXXLarge( "XXXL", "XXX Large", 5000, 25000 )
	;

    private static String[]              	displayValues;
    private static Map<String , Rom>	map;

    static
    {
        synchronized( Rom.class )
        {
            map = new HashMap<String , Rom>();

            for( Rom x : Rom.values() )
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

            for( Rom stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static Rom locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;
    private int							min;
    private int							max;

    //  ~ Constructor **********************************************************
    Rom(
      String                            value
    , String                            display
    , int								min
    , int								max
    )
    {
        this.value      = value;
        this.display    = display;
        this.min		= min;
        this.max		= max;
    }


    //  ~ Methods **************************************************************
    @Override
    public String display(
    )
    {
        return display;
    }

    public int max(
    )
    {
    	return max;
    }

    public int min(
    )
    {
    	return min;
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