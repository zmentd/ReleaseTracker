package com.fdc.boarding.core.util;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.fdc.boarding.core.datadef.DateFormat;
import com.fdc.boarding.core.datadef.TimeFormat;
import com.fdc.boarding.core.datadef.TimeZoneId;


/**
 *
 */
public class DateTimeUtil
{
    public static boolean               TRUNCATE_TIME           = false;
    public final static DateTimeZone    DEFAULT_TIME_ZONE       = DateTimeZone.UTC;
    public final static TimeZoneId      DEFAULT_TIME_ZONE_ID    = TimeZoneId.UTC;
    public final static DateFormat      DEFAULT_DATE_FORMAT     = DateFormat.MDY;
    public final static TimeFormat      DEFAULT_TIME_FORMAT     = TimeFormat.HMSA;
    public final static String          DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT.display() + " " + DEFAULT_TIME_FORMAT.display();
    
    private final static PeriodFormatter	PERIOD_FORMATTER;
    static{
    	PERIOD_FORMATTER = new PeriodFormatterBuilder().appendDays()
													   .appendSuffix("d ")
													   .appendHours()
													   .appendSuffix("h ")
													   .appendMinutes()
													   .appendSuffix("m ")
													   .appendSeconds()
													   .appendSuffix("s ")
													   .appendMillis()
													   .appendSuffix("ms")
													   .toFormatter();   	
    }
    /**
     * @return
     *  The time at the start of the current date ( 0:0:0.000 )
     */
    public static DateTime getCurrentUtcDateTime(
    )
    {
        return new DateTime( getCurrentMillis(), DateTimeUtil.DEFAULT_TIME_ZONE );
    }
    
    /**
     * @return
     *  The time at the end of the current date ( 23:59:59.999 )
     */
    public static DateTime getCurrentUtcDateTimeStartOfDay(
    )
    {
        return new DateTime( getCurrentMillis(), DateTimeUtil.DEFAULT_TIME_ZONE ).withTimeAtStartOfDay();
    }
    
    /**
     * @return
     */
    public static DateTime getCurrentUtcDateTimeEndOfDay(
    )
    {
        //
        // Joda-Time does not have a convienent method to get the time at the end of the day.
        // To over come issues, we start with the start of the day, add 1 day and subtract a millisecond.
        //
        return getCurrentUtcDateTimeStartOfDay().plusDays( 1 ).minusMillis( 1 );
    }
    
    /**
     * @return
     */
    public static LocalDate getCurrentUtcDate(
    )
    {
        return getCurrentUtcDateTime().toLocalDate();
    }
    
    /**
     * @return
     */
    public static long getCurrentMillis(
    )
    {
        long                            millis;
        
        if( TRUNCATE_TIME )
        {
            millis  = truncateMillis( System.currentTimeMillis() );
        }
        else
        {
            millis  = System.currentTimeMillis();
        }
        
        return millis;
    }
    
    public static PeriodFormatter getPeriodFormatter(
    )
    {
		return PERIOD_FORMATTER;
    }
    /**
     * @param millis
     * @return
     */
    public static Timestamp getTimestamp(
      long                              millis
    )
    {
        Timestamp                       time;
        
        if( TRUNCATE_TIME )
        {
            time    = new Timestamp( truncateMillis( millis ) );
        }
        else
        {
            time    = new Timestamp( millis );
        }

        return time;
    }

    /**
     * Determine if the current date is within the effective and
     * expiration dates.
     *  
     * @param effectiveDt
     * @param expirationDt
     * @return
     */
    public static boolean isEffectiveNow(
      DateTime                          effectiveDt
    , DateTime                          expirationDt
    )
    {
        boolean                         effective   = false;
        DateTime                        now;
        
        now	= DateTimeUtil.getCurrentUtcDateTime();
        if( now.isAfter( effectiveDt )    	&&
            ( expirationDt == null        	||
              now.isBefore( expirationDt )
            )
        )
        {
            effective   = true;
        }

        return effective;
    }

    /**
     * Determine if the current date is within the effective and
     * expiration dates.
     *  
     * @param effectiveDt
     * @param expirationDt
     * @return
     */
    public static boolean isEffectiveNow(
      LocalDate                    		effectiveDt
    , LocalDate                     	expirationDt
    )
    {
        boolean                         effective   = false;
        LocalDate                      	now;
        
        now	= new LocalDate();
        if( now.isAfter( effectiveDt )    	&&
            ( expirationDt == null        	||
              now.isBefore( expirationDt )
            )
        )
        {
            effective   = true;
        }

        return effective;
    }
  
    /**
     * Truncate the value to 1/100th of a second or 1 millisecond.
     * 
     * @param timestamp
     *      The timestamp to truncate.
     *  
     * @return
     *      A new truncated timestamp.
     */
    public static long truncateMillis(
      long                              millis        
    )
    {
        long                            truncated;
        
        truncated = millis - ( millis % 10 );
        
        return truncated;
    }
    
    /**
     * Truncate the value to 1/100th of a second or 1 millisecond.
     * 
     * @param timestamp
     *      The timestamp to truncate.
     *  
     * @return
     *      A new truncated timestamp.
     */
    public static Timestamp truncateTimestamp(
      Timestamp                         timestamp        
    )
    {
        Timestamp                       truncatedTmst;
        long                            truncated;
        
        truncated       = truncateMillis( timestamp.getTime() ); 
        truncatedTmst   = new Timestamp( truncated );
        
        return truncatedTmst;
    }
}
