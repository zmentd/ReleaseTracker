package com.fdc.boarding.core.persistence.type;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import com.fdc.boarding.core.datadef.IEnumDisplayValue;

public class EnumType implements UserType, ParameterizedType
{ 
    protected final static Logger               logger              = Logger.getLogger( EnumType.class.getName() );
    protected final static String               CHAR_TYPE           = "CHAR";
    protected final static String               VARCHAR_TYPE        = "VARCHAR";

    private int[]                               SQL_TYPES;
    private int                                 sql_type;
    private Class<?>                            clazz               = null;
    private String                              enumType;

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
        return value;
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

    @Override
    public boolean equals(
      Object                            x
    , Object                            y
    ) throws HibernateException
    {
        if( x == y ) //NOPMD
        {
            return true;
        }
        if( null == x || null == y )
        {
            return false;
        }

        return x.equals( y );
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

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
     */
    @Override
    public Object nullSafeGet(
      ResultSet                         resultSet
    , String[]                          names
    , SessionImplementor                session
    , Object                            owner
    ) throws HibernateException, SQLException
    {
        String                          name;
        Object                          result = null;
        Method                          method = null;

        name = resultSet.getString( names[0] );
        if( !resultSet.wasNull() )
        {
            try
            {
                method  = clazz.getDeclaredMethod( "locate", String.class );
                result  = method.invoke( null, name );
                if( result == null )
                {
                    throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ) enum type value not found." );
                }
            }
            catch( HibernateException e )
            {
                throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ).", e );
            }
            catch( IllegalArgumentException e )
            {
                throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ).", e );
            }
            catch( SecurityException e )
            {
                throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ).", e );
            }
            catch( NoSuchMethodException e )
            {
                throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ).", e );
            }
            catch( IllegalAccessException e )
            {
                throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ).", e );
            }
            catch( InvocationTargetException e )
            {
                throw new HibernateException( "Unable to create enum of type ( " + enumType + " ) from value ( " + name + " ).", e );
            }
        }

        return result;
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
        if( null == value )
        {
            preparedStatement.setNull( index, sql_type );
        }
        else
        {
            if( value instanceof IEnumDisplayValue )
            {
                preparedStatement.setString( index, ( ( IEnumDisplayValue )value ).value() );
            }
            else
            {
                preparedStatement.setString( index, value.toString() );
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
        return clazz;
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.ParameterizedType#setParameterValues(java.util.Properties)
     */
    @Override
    public void setParameterValues(
      Properties                        params
    )
    {
        String                          sqlType;

        enumType = params.getProperty( "enum_type" );
        if( enumType == null )
        {
            throw new MappingException( "enum_type parameter not specified" );
        }
        sqlType = params.getProperty( "sql_type" );
        if( sqlType == null )
        {
            sqlType = VARCHAR_TYPE;
        }

        if( sqlType.equalsIgnoreCase( CHAR_TYPE ) )
        {
            sql_type    = Types.CHAR;
            SQL_TYPES   = new int[]{ Types.CHAR };
        }
        else
        {
            sql_type    = Types.VARCHAR;
            SQL_TYPES   = new int[]{ Types.VARCHAR };
        }
        try
        {
            this.clazz = Class.forName( enumType );
        }
        catch( java.lang.ClassNotFoundException e )
        {
            throw new MappingException( "enum_type " + enumType + " not found", e );
        }
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
}
