package com.fdc.boarding.core.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Class used to map java.util.logger functions to a common format.
 *  This is intended to work much the same as jakarta commons logging
 *  without the need for additional jar files.
 */
public class JavaUtilLogger extends AbstractLogger
{
    private static final long           serialVersionUID = 1L;
    
    /**
     * @param name
     *      The logger name.
     *
     * @return
     *      The retrieved logger.
     */
    public static AbstractLogger getLogger(
      String                            name
    )
    {
        return new JavaUtilLogger( name );
    }

    //~ Instance fields ****************************************************************************
    protected Logger                    mLogger;

    //~ Constructors *******************************************************************************
    /**
     * @param name
     *      The logger name.
     */
    public JavaUtilLogger(
      String                            name
    )
    {
        super( name );
        mLogger = Logger.getLogger( mLoggerName );
    }

    //~ Methods ************************************************************************************
    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#debug()
     */
    @Override
    public void debug(
      Object                            obj
    )
    {
        mLogger.fine( obj.toString() );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#debug()
     */
    @Override
    public void debug(
      Object                            obj
    , Exception                         except
    )
    {
        mLogger.log( Level.FINE, obj.toString(), except );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#error()
     */
    @Override
    public void error(
      Object                            obj
    )
    {
        mLogger.severe( obj.toString() );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#error()
     */
    @Override
    public void error(
      Object                            obj
    , Exception                         except
    )
    {
        mLogger.log( Level.SEVERE, obj.toString(), except );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#fatal()
     */
    @Override
    public void fatal(
      Object                            obj
    )
    {
        mLogger.severe( obj.toString() );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#fatal()
     */
    @Override
    public void fatal(
      Object                            obj
    , Exception                         except
    )
    {
        mLogger.log( Level.SEVERE, obj.toString(), except );
    }
    
    /**
     * @return
     *      The underlying logger;
     */
    @Override
    public Object getRawLogger(
    )
    {
        return mLogger;
    }
    
    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#info()
     */
    @Override
    public void info(
      Object                            obj
    )
    {
        mLogger.info( obj.toString() );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#info()
     */
    @Override
    public void info(
      Object                            obj
    , Exception                         except
    )
    {
        mLogger.log( Level.INFO, obj.toString(), except );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled(
    )
    {
        return mLogger.isLoggable( Level.FINE );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled(
    )
    {
        return mLogger.isLoggable( Level.SEVERE );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isFatalEnabled()
     */
    @Override
    public boolean isFatalEnabled(
    )
    {
        return mLogger.isLoggable( Level.SEVERE );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled(
    )
    {
        return mLogger.isLoggable( Level.INFO );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled(
    )
    {
        return mLogger.isLoggable( Level.WARNING );
    }

    @Override
    public void setLevel(
      com.fdc.boarding.core.log.Level           level 
    )
    {
        switch( level )
        {
            case DEBUG:
                mLogger.setLevel( Level.FINE );
                break;
            case INFO:
                mLogger.setLevel( Level.INFO );
                break;
            case WARN:
                mLogger.setLevel( Level.WARNING );
                break;
            case ERROR:
            default:
                mLogger.setLevel( Level.SEVERE );
                break;
            
        }
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#warn()
     */
    @Override
    public void warn(
      Object                            obj
    )
    {
        mLogger.warning( obj.toString() );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#warn()
     */
    @Override
    public void warn(
      Object                            obj
    , Exception                         except
    )
    {
        mLogger.log( Level.WARNING, obj.toString(), except );
    }
}
