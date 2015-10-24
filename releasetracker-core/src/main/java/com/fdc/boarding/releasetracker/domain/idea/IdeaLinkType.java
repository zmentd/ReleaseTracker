package com.fdc.boarding.releasetracker.domain.idea;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public enum IdeaLinkType implements IEnumDisplayValue  {

	Amendment( "Amendment", "Amendment" ),
	Umbrella( "Umbrella", "Umbrella" )
	;

    private static String[]                   		displayValues;
    private static Map<String , IdeaLinkType> 	map;

    static
    {
        synchronized( IdeaLinkType.class )
        {
            map = new HashMap<String , IdeaLinkType>();

            for( IdeaLinkType x : IdeaLinkType.values() )
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

            for( IdeaLinkType stat : map.values() )
            {
                displayValues[ indx ] = stat.display();
                indx++;
            }
        }

        return displayValues;
    }

    public static IdeaLinkType locate(
      String                        reqType
    )
    {
        return map.get( reqType );
    }


    //  ~ Instance fields ******************************************************
    private String                      display;
    private String                      value;


    //  ~ Constructor **********************************************************
    IdeaLinkType(
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
