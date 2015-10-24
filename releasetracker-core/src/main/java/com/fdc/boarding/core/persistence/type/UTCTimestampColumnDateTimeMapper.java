package com.fdc.boarding.core.persistence.type;

import java.sql.Timestamp;

import org.jadira.usertype.dateandtime.joda.util.ZoneHelper;
import org.jadira.usertype.spi.shared.AbstractVersionableTimestampColumnMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class UTCTimestampColumnDateTimeMapper extends AbstractVersionableTimestampColumnMapper<DateTime> {

    private static final long serialVersionUID = -7670411089210984705L;

    public static final DateTimeFormatter DATETIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss'.'").appendFractionOfSecond(0, 9).toFormatter();

    private DateTimeZone databaseZone = DateTimeZone.UTC;

    @Override
    public DateTime fromNonNullString(String s) {
        return new DateTime(s);
    }

    @Override
    public DateTime fromNonNullValue(Timestamp value) {

        DateTimeZone currentDatabaseZone = databaseZone == null ? ZoneHelper.getDefault() : databaseZone;
        DateTime dateTime = DATETIME_FORMATTER.withZone(currentDatabaseZone).parseDateTime(value.toString());
        
        return dateTime;
    }

    @Override
    public String toNonNullString(DateTime value) {
        return value.toString();
    }

    @Override
    public Timestamp toNonNullValue(DateTime value) {

        DateTimeZone currentDatabaseZone = databaseZone == null ? ZoneHelper.getDefault() : databaseZone;

        value = value.withZone(currentDatabaseZone);

        String formattedTimestamp = DATETIME_FORMATTER.print(value);
        if (formattedTimestamp.endsWith(".")) {
            formattedTimestamp = formattedTimestamp.substring(0, formattedTimestamp.length() - 1);
        }

        final Timestamp timestamp = Timestamp.valueOf(formattedTimestamp);
        return timestamp;
    }

    public void setDatabaseZone(DateTimeZone databaseZone) {
        this.databaseZone = databaseZone;
    }

    @Override
    public Timestamp generateCurrentValue() {
        Timestamp						ts;
        
    	ts	= new Timestamp( DateTimeZone.getDefault().convertLocalToUTC( System.currentTimeMillis(), false ) );
    	
    	return ts;
    }

}

