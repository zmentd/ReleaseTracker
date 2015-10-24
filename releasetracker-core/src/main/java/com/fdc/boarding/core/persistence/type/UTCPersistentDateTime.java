package com.fdc.boarding.core.persistence.type;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.jadira.usertype.spi.shared.AbstractVersionableUserType;
import org.jadira.usertype.spi.shared.ConfigurationHelper;
import org.jadira.usertype.spi.shared.IntegratorConfiguredType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class UTCPersistentDateTime extends AbstractVersionableUserType<DateTime, Timestamp, UTCTimestampColumnDateTimeMapper> implements ParameterizedType, IntegratorConfiguredType {

    private static final long serialVersionUID = -6656619988954550389L;

    
    @Override
    public int compare(Object o1, Object o2) {
        return ((DateTime) o1).compareTo((DateTime) o2);
    }

	@Override
	public void applyConfiguration(SessionFactory sessionFactory) {
		
		super.applyConfiguration(sessionFactory);

		UTCTimestampColumnDateTimeMapper columnMapper = (UTCTimestampColumnDateTimeMapper) getColumnMapper();

        String databaseZone = null;
        if (getParameterValues() != null) {
        	databaseZone = getParameterValues().getProperty("databaseZone");
        }
		if (databaseZone == null) {
			databaseZone = ConfigurationHelper.getProperty("databaseZone");
		}
		
        if (databaseZone != null) {
            if ("jvm".equals(databaseZone)) {
                columnMapper.setDatabaseZone(null);
            } else {
                columnMapper.setDatabaseZone(DateTimeZone.forID(databaseZone));
            }
        }
	}

	@Override
	public DateTime seed(SessionImplementor session) {
		return super.seed(session);
	}
}
