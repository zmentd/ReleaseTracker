package com.fdc.boarding.core.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class StringUtil
{
    public final String                 COMMA       = ",";

    public static String unqualify( String qualifiedName )
    {
        int loc = qualifiedName.lastIndexOf( "." );
        return ( loc < 0 ) ? qualifiedName : qualifiedName.substring( loc + 1 );
    }

    public static String qualifier( String qualifiedName )
    {
        int loc = qualifiedName.lastIndexOf( "." );
        return ( loc < 0 ) ? "" : qualifiedName.substring( 0, loc );
    }
	
    public static String bytesToString( 
      byte[]                            bytes 
    )
    {
        char[]                          buffer;
        int                             bpos;
        char                            c;
        
        buffer = new char[bytes.length >> 1];
        for( int i = 0; i < buffer.length; i++ )
        {
            bpos    = i << 1;
            c       = ( char )( ( ( bytes[bpos] & 0x00FF ) << 8 ) + ( bytes[bpos + 1] & 0x00FF ) );
            buffer[i] = c;
        }
        
        return new String( buffer );
    }

    /**
     * Capitalize the given String.
     *
     * @param value
     *      The String to capitalize.
     *
     * @return
     *      The capitalized String.
     */
    public static String capitalize(
      String                            value
    )
    {
        char                            chars[];
        String                          newValue;

        if( value.length() == 0 )
        {
            return value;
        }
        chars       = value.toCharArray();
        chars[0]    = Character.toUpperCase(chars[0]);
        newValue    = new String( chars );

        return newValue;
    }

    /**
     * Capitalize the given String.
     *
     * @param value
     *      The String to capitalize.
     *
     * @return
     *      The capitalized String.
     */
    public static String decapitalize(
      String                            value
    )
    {
        char                            chars[];
        String                          newValue;

        if( value.length() == 0 )
        {
            return value;
        }
        chars       = value.toCharArray();
        chars[0]    = Character.toLowerCase(chars[0]);
        newValue    = new String( chars );

        return newValue;
    }

    public static boolean equal(
      String                            value
    , String                            other
    )
    {
        boolean                         equal   = false;

        if( value == null && other == null )
        {
            equal = true;
        }
        else if( value != null && other != null )
        {
            equal = value.equals( other );
        }

        return equal;
    }

    /**
     * Returns true if the values equal, ignoring case.
     * Handles null.
     *
     * @param value1
     * @param value2
     * @return
     */
    public static boolean equalsIgnoreCase(
      String                            value1
    , String                            value2
    )
    {
        boolean                         equals  = false;

        if( value1 == null && value2 == null )
        {
            equals = true;
        }
        else if( value1 != null && value2 != null )
        {
            equals  = value1.equalsIgnoreCase( value2 );
        }

        return equals;
    }

    public static String fillLeft(
      String                            value
    , int                               totalPlaces
    , char                              fillChar
    )
    {
        String                          newValue;

        newValue    = value;
        while( newValue.length() < totalPlaces )
        {
            newValue    = fillChar + newValue;
        }

        return newValue;
    }

    /**
     * Convert the long to a string, without comma separations.
     * @param value
     * @return
     */
    public static String longToString(
      Long                              value
    )
    {
        String                          str;

        str = new DecimalFormat( "#" ).format(value);

        return str;
    }

    /**
     * @param value
     * @return
     */
    public static Long safeStringToLong(
      String                            value
    )
    {
        Long                            longValue   = null;

        try
        {
            longValue   = Long.valueOf( value );
        }
        catch( NumberFormatException e )
        {
            //
            // ignored.
            //
        }

        return longValue;
    }

    /**
     * Create list of all upper case string from the provided list.
     *
     * @param values
     *      The Strings to upper case.
     *
     * @return
     *      The upper case Strings.
     */
    public static List<String> toUpperCaseAll(
      List<String>                      values
    )
    {
        ArrayList<String>               uppers = null;

        if( values != null && !values.isEmpty() )
        {
            uppers = new ArrayList<String>();
            for( String s : values )
            {
                uppers.add( s.toUpperCase() );
            }
        }
        return uppers;
    }

    /**
     * Upper case the value if it is not null;
     * 
     * @param value
     *      The value to up shift.
     *      
     * @return
     *      The upper cased value or null.
     */
    public static String getToUpperValue(
      String                            value       
    )
    {
        String                          returnValue = null;
        
        returnValue = value;
        if( returnValue != null )
        {
            returnValue = returnValue.toUpperCase( Locale.getDefault() );
        }

        return returnValue;
    }

    /**
     * Lower case the value if it is not null;
     * 
     * @param value
     *      The value to down shift.
     *      
     * @return
     *      The lower cased value or null.
     */
    public static String getToLowerValue(
      String                            value       
    )
    {
        String                          returnValue = null;
        
        returnValue = value;
        if( returnValue != null )
        {
            returnValue = returnValue.toLowerCase( Locale.getDefault() );
        }

        return returnValue;
    }

    public static String qualify( 
      String                            prefix
    , String                            name 
    )
    {
        if( name == null || prefix == null )
        {
            throw new NullPointerException();
        }
        
        return new StringBuffer( prefix.length() + name.length() + 1 )
                        .append( prefix )
                        .append( '.' )
                        .append( name )
                        .toString();
    }

    public static String[] qualify( 
      String                            prefix
    , String[]                          names 
    )
    {
        int                             len;
        String[] qualified;
        
        if( prefix == null )
        {
            return names;
        }
        len         = names.length;
        qualified   = new String[len];
        for( int i = 0; i < len; i++ )
        {
            qualified[i] = qualify( prefix, names[i] );
        }
        
        return qualified;
    }

    /**
     * @param delimiter
     * @param values
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static String joinAll(
      String                            delimiter
    , boolean                           useSingleQuote
    , Collection                        values
    )
    {
        StringBuilder                   builder;
        boolean                         first   = true;

        builder = new StringBuilder();
        for( Object obj : values )
        {
            if( useSingleQuote )
            {
                obj = "'" + obj + "'";
            }
            if( first )
            {
                builder.append( obj );
                first   = false;
            }
            else
            {
                builder.append( delimiter );
                builder.append( obj );
            }
        }

        return builder.toString();
    }

    /**
     * @param delimiter
     * @param values
     * @return
     */
    public static String joinAll(
      String                            delimiter
    , boolean                           useSingleQuote
    , Object[]                          values
    )
    {
        StringBuilder                   builder;
        boolean                         first   = true;

        builder = new StringBuilder();
        for( Object obj : values )
        {
            if( useSingleQuote )
            {
                obj = "'" + obj + "'";
            }
            if( first )
            {
                builder.append( obj );
                first   = false;
            }
            else
            {
                builder.append( delimiter );
                builder.append( obj );
            }
        }

        return builder.toString();
    }
}
