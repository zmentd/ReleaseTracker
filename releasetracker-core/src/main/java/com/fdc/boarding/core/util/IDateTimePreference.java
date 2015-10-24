package com.fdc.boarding.core.util;

/**
 * Interface for user preferences on Date/Time formatting.
 */
public interface IDateTimePreference {
    public String getDateFormat(
    );
    
    public String getTimeFormat(
    );
    
    public String getTimezone(
    );
    
    public Boolean isDayLightSavings(
    );

}
