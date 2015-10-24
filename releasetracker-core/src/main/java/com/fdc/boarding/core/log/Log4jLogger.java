package com.fdc.boarding.core.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *  Class used to map Log4j functions to a common format.
 *  This is intended to work much the same as jakarta commons logging
 *  without the need for additional jar files.
 */
public class Log4jLogger extends AbstractLogger
{
    private static final long           serialVersionUID = 1L;
    
    //~ Instance fields ****************************************************************************
    protected Logger                    mLogger;

    //~ Constructors *******************************************************************************
    /**
     * @param name
     *      The logger name.
     */
    public Log4jLogger(
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
        mLogger.debug( obj );
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
        mLogger.debug( obj, except );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#error()
     */
    @Override
    public void error(
      Object                            obj
    )
    {
        mLogger.error( obj );
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
        mLogger.error( obj, except );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#fatal()
     */
    @Override
    public void fatal(
      Object                            obj
    )
    {
        mLogger.fatal( obj );
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
        mLogger.fatal( obj, except );
    }

    /**
     * @param name
     *      The logger to retrieve.
     *
     * @return
     *      The logger.
     */
    public static AbstractLogger getLogger(
      String                            name
    )
    {
        return new Log4jLogger( name );
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
        mLogger.info( obj );
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
        mLogger.info( obj, except );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled(
    )
    {
        return mLogger.isDebugEnabled();
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled(
    )
    {
        return mLogger.isEnabledFor( Level.ERROR );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isFatalEnabled()
     */
    @Override
    public boolean isFatalEnabled(
    )
    {
        return mLogger.isEnabledFor( Level.FATAL );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled(
    )
    {
        return mLogger.isInfoEnabled();
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled(
    )
    {
        return mLogger.isEnabledFor( Level.WARN );
    }

    /* (non-Javadoc)
     * @see com.bhmi.util.log.AbstractLogger#warn()
     */
    @Override
    public void warn(
      Object                            obj
    )
    {
        mLogger.warn( obj );
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
        mLogger.warn( obj, except );
    }

    @Override
    public void setLevel(
      com.fdc.boarding.core.log.Level           level 
    )
    {
        switch( level )
        {
            case DEBUG:
                mLogger.setLevel( Level.DEBUG );
                break;
            case INFO:
                mLogger.setLevel( Level.INFO );
                break;
            case WARN:
                mLogger.setLevel( Level.WARN );
                break;
            case ERROR:
                mLogger.setLevel( Level.ERROR );
                break;
            default:
                mLogger.setLevel( Level.FATAL );
                break;
            
        }
    }
}
