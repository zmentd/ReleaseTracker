package com.fdc.boarding.core.log;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Utility class for dumping values in hex.
 */
public class HexDump
{
    /**
     *  Convert the given buffer into hex.
     *
     *  @param  buff
     *      The buffer to be converted.
     *  @param len
     *      The length of the given buffer.
     */
    public static void dump(
      byte[]                            buff
    , int                               len
    , LoggerProxy                       logger
    , String                            prefix
    )
    {
        StringBuffer                    sb;
        StringBuffer                    sbHexBuff;
        DecimalFormat                   vFormat     = new DecimalFormat( "00000" );
        int                             vBuffLen    = buff.length;

        vFormat.setMaximumIntegerDigits( 5 );
        sb = new StringBuffer();
        logger.info( "Length:" + len );
        logger.info( prefix + "IMsg Dump:" );
        for( int j = 0; j < len + 17; j += 17 )
        {
            sbHexBuff   = new StringBuffer();
            sbHexBuff.append( vFormat.format( j ) + " | " );
            sbHexBuff.append( hexLine( j, buff, vBuffLen ) );
            sb.append( "\n" + sbHexBuff.toString().trim() );
        }
        logger.info( "\n" + sb.toString().trim() );
   }

    /**
     * Outputs the given ByteBuffer to the given logger
     * in standard hex format.
     *
     * @param buffer
     *      The buffer to convert.
     * @param logger
     *      The logger to output to.
     */
    public static void dump(
      ByteBuffer                        buffer
    , LoggerProxy                       logger
    )
    {
        byte[]                          rawBytes;

        rawBytes = new byte[buffer.capacity()];
        buffer.get( rawBytes );
        dump( rawBytes, rawBytes.length, logger, "" );
        buffer.flip();
    }

    /**
     * Outputs the given ByteBuffer to the given logger
     * in standard hex format.
     *
     * @param buffer
     *      The buffer to convert.
     * @param logger
     *      The logger to output to.
     */
    public static void dump(
      ByteBuffer                        buffer
    , LoggerProxy                       logger
    , String                            prefix
    )
    {
        byte[]                          rawBytes;

        rawBytes = new byte[buffer.capacity()];
        buffer.get( rawBytes );
        dump( rawBytes, rawBytes.length, logger, prefix );
        buffer.flip();
    }

    /**
     * Outputs the given ByteBuffers to the given logger
     * in standard hex format.
     *
     * @param buffers
     *      The buffers to convert.
     * @param logger
     *      The logger to output to.
     */
    public static void dump(
      ByteBuffer[]                      buffers
    , LoggerProxy                       logger
    )
    {
        int                             len         = 0;
        int                             pos         = 0;
        byte[]                          rawBytes;

        for( int i = 0; i < buffers.length; i++ )
        {
            len += buffers[i].capacity();
        }

        rawBytes = new byte[len];
        for( int i = 0; i < buffers.length; i++ )
        {
            buffers[i].get( rawBytes, pos, buffers[i].capacity() );
            pos += buffers[i].capacity();
            buffers[i].flip();
        }

        dump( rawBytes, rawBytes.length, logger, "" );
    }

    /**
     *  Convert the given buffer into hex.
     *
     *  @param  buff
     *      The buffer to be converted.
     *  @param len
     *      The length of the given buffer.
     */
    public static void dumpError(
      byte[]                            buff
    , int                               len
    , LoggerProxy                       logger
    , String                            prefix
    )
    {
        StringBuffer                    sb;
        StringBuffer                    sbHexBuff;
        DecimalFormat                   vFormat     = new DecimalFormat( "00000" );
        int                             vBuffLen    = buff.length;

        vFormat.setMaximumIntegerDigits( 5 );
        sb = new StringBuffer();
        logger.error( "Length:" + len );
        logger.error( prefix + "IMsg Dump:" );
        for( int j = 0; j < len + 17; j += 17 )
        {
            sbHexBuff   = new StringBuffer();
            sbHexBuff.append( vFormat.format( j ) + " | " );
            sbHexBuff.append( hexLine( j, buff, vBuffLen ) );
            sb.append( "\n" + sbHexBuff.toString().trim() );
        }
        logger.error( "\n" + sb.toString().trim() );
   }

    //~ Methods ************************************************************************************
    /**
     *  Convert the given character to Hex.
     *
     *  @param      intChat
     *                  The int to be converted.
     *
     *  @return     The converted character.
     */
    public static String hex(
      int                               intChat
    )
    {
        String                          sChar = Integer.toHexString( intChat );
        if( sChar.length() == 1 )
        {
            sChar = "0" + intChat;
        }
        return sChar.substring( sChar.length() - 2 );
    }

    /**
     *  Convert the 17 characters of the given buffer to hex, starting at
     *  the given position.
     *
     *  @param      pos
     *                  The position within the buffer to start converting.
     *
     *  @param      buff
     *                  The buffer to be converted.
     *
     *  @param      len
     *                  The length of the given buffer.
     *
     *  @return     Buffer containing the converted data.
     */
    public static StringBuffer hexLine(
      int                               pos
    , byte[]                            buff
    , int                               len
    )
    {
        StringBuffer                    sb      = new StringBuffer();
        String                          hex    = "";

        for( int i = pos; i < pos + 17; i++ )
        {
            if( i < len )
            {
                hex = hex( buff[i] );
                sb.append( hex.toUpperCase( Locale.getDefault() ) );
                sb.append( " " );
            }
            else
            {
                    sb.append( "   " );
            }
        }
        sb.append( "|" );
        for( int i = pos; i < pos + 17; i++ )
        {
            if( i < len )
            {
                if( isPrintable( ( char )buff[i] ) )
                {
                    sb.append( Character.valueOf( ( char )buff[i] ) );
                }
                else
                {
                    sb.append( '.' );
                }
            }
            else
            {
                    sb.append( " " );
            }
        }
        sb.append( "|\n" );
        return sb;
    }

    /**
     * @param intChar
     *      The char to check.
     *
     * @return
     *      True if the char is printable.
     */
    static boolean isPrintable(
      int                               intChar
    )
    {
        return( ( intChar - 0x20 ) | ( 0x7E - intChar ) ) >= 0;
    }
}
