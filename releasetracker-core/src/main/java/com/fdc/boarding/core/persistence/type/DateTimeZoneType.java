package com.fdc.boarding.core.persistence.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTimeZone;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.core.util.DateTimeUtil;

/**
 *
 */
public class DateTimeZoneType implements UserType, ParameterizedType
{ //NOPMD
    private static final int[]                      SQL_TYPES           = new int[]
    {
        Types.VARCHAR,
    };

    /*
     * Logger object
     */
    protected static LoggerProxy                    logger = LoggerProxy.getLogger( DateTimeZoneType.class );


    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
     */
    @Override
    public Object assemble(
      Serializable                      cached
    , Object                            owner
    ) throws HibernateException
    {
        return cached;
    }


    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
     */
    @Override
    public Object deepCopy(
      Object                            value
    ) throws HibernateException
    {
        if( value == null )
        {
            return null;
        }

        return newDateTimeZone( value );
    }


    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
     */
    @Override
    public Serializable disassemble(
      Object                            value
    ) throws HibernateException
    {
        return ( Serializable )value;
    }


    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#equals(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean equals(
      Object                            x
    , Object                            y
    ) throws HibernateException
    {
        boolean                         equals;
        DateTimeZone                    dtx;
        DateTimeZone                    dty;

        if( x == y ) //NOPMD
        {
            equals = true;
        }
        else if( x == null || y == null )
        {
            equals = false;
        }
        else
        {
            dtx     = ( DateTimeZone )x;
            dty     = ( DateTimeZone )y;
            equals  = dtx.equals( dty );
        }

        return equals;
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
     */
    @Override
    public int hashCode(
      Object                            x
    ) throws HibernateException
    {
        return x.hashCode();
    }


    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#isMutable()
     */
    @Override
    public boolean isMutable(
    )
    {
        return false;
    }


    /**
     * @param obj
     * @return
     */
    protected DateTimeZone newDateTimeZone(
      Object                            obj
    )
    {
        DateTimeZone                    dt = null;

        if( obj == null )
        {
            dt  = DateTimeUtil.DEFAULT_TIME_ZONE;
        }
        else
        {
            if( obj instanceof String )
            {
                dt = DateTimeZone.forID( (String) obj );
            }
            else if( obj instanceof DateTimeZone )
            {
                dt = ( DateTimeZone )obj;
            }


            if( dt == null )
            {
                logger.error( "Unexpected type (" + obj.getClass().getName() + ") reveived, expecting BigDecimal or CurrencyAmount." );
                dt  = DateTimeUtil.DEFAULT_TIME_ZONE;
            }
        }

        return dt;
    }

    /**
     * @param rs
     * @param name
     * @return
     * @throws HibernateException
     * @throws SQLException
     */
    public Object nullSafeGet(
      ResultSet                         resultSet
    , String[]                          names
    , SessionImplementor                session
    , Object                            owner
    ) throws HibernateException, SQLException
    {
        Object                          timeZone;
        DateTimeZone                    dt;

        timeZone = resultSet.getString( names[0] );
        timeZone   = StringType.INSTANCE.nullSafeGet( resultSet, names, session, owner);
        if (timeZone == null)
        {
            return null;
        }
        dt  = newDateTimeZone( timeZone );

        return dt;
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
     */
    @Override
    public void nullSafeSet(
      PreparedStatement                 preparedStatement
    , Object                            value
    , int                               index
    , SessionImplementor                session 
    ) throws HibernateException, SQLException
    {
        String                          timeZone;
        DateTimeZone                    dateTimeZone;

        if( value == null )
        {
        	StringType.INSTANCE.nullSafeSet( preparedStatement, null, index, session);
        }
        else
        {
            if( value instanceof DateTimeZone )
            {
                dateTimeZone    = ( DateTimeZone )value;
                timeZone        =  dateTimeZone.getID();
            	StringType.INSTANCE.nullSafeSet( preparedStatement, timeZone, index, session);
           }
        }
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Object replace(
      Object                            original
    , Object                            target
    , Object                            owner
    ) throws HibernateException
    {
        return original;
    }


    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#returnedClass()
     */
    @Override
    public Class<?> returnedClass(
    )
    {
        return DateTimeZone.class;
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#sqlTypes()
     */
    @Override
    public int[] sqlTypes(
    )
    {
        return SQL_TYPES;
    }


	@Override
	public void setParameterValues(Properties parameters) {
		
	}
}
