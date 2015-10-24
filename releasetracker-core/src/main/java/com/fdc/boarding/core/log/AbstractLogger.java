package com.fdc.boarding.core.log;

import java.io.Serializable;

/**
 * Base logger.
 */
public abstract class AbstractLogger implements Serializable
{
	private static final long serialVersionUID = 1L;
	//~ Instance fields ****************************************************************************
    protected String                    mLoggerName;

    //~ Constructors *******************************************************************************
    /**
     * Default Constructor.
     */
    public AbstractLogger(
      String                            name
    )
    {
        mLoggerName = name;
    }

    //~ Methods ************************************************************************************
    /**
     * Log a message object with the DEBUG level.
     *
     * @param obj
     *      The message object to log.
     */
    public abstract void debug(
      Object                            obj
    );

    /**
     * Log a message object with the DEBUG level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public abstract void debug(
      Object                            obj
    , Exception                         except
    );

    /**
     * Log a message object with the ERROR level.
     *
     * @param obj
     *      The message object to log.
     */
    public abstract void error(
      Object                            obj
    );

    /**
     * Log a message object with the ERROR level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public abstract void error(
      Object                            obj
    , Exception                         except
    );

    /**
     * Log a message object with the FATAL level.
     *
     * @param obj
     *      The message object to log.
     */
    public abstract void fatal(
      Object                            obj
    );

    /**
     * Log a message object with the FATAL level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public abstract void fatal(
      Object                            obj
    , Exception                         except
    );
    
    /**
     * @return
     */
    public Level getLevel(
    )
    {
    	com.fdc.boarding.core.log.Level         commonLevel;
        
        if( isDebugEnabled() )
        {
            commonLevel    = com.fdc.boarding.core.log.Level.DEBUG;
        }
        else if( isInfoEnabled() )
        {
            commonLevel    = com.fdc.boarding.core.log.Level.INFO;
        }
        else if( isWarnEnabled() )
        {
            commonLevel    = com.fdc.boarding.core.log.Level.WARN;
        }
        else if( isErrorEnabled() )
        {
            commonLevel    = com.fdc.boarding.core.log.Level.ERROR;
        }
        else
        {
            commonLevel    = com.fdc.boarding.core.log.Level.FATAL;
        }
            
        return commonLevel;
    }
    
    /**
     * @return
     *      The underlying logger;
     */
    public abstract Object getRawLogger(
    );

    /**
     * Log a message object with the INFO level.
     *
     * @param obj
     *      The message object to log.
     */
    public abstract void info(
      Object                            obj
    );

    /**
     * Log a message object with the INFO level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public abstract void info(
      Object                            obj
    , Exception                         except
    );

    /**
     * Check whether this logger is enabled for the DEBUG Level.
     *
     * @return
     *      true if this logger is debug enabled, false otherwise.
     */
    public abstract boolean isDebugEnabled(
    );

    /**
     * Check whether this logger is enabled for the ERROR Level.
     *
     * @return
     *      true if this logger is ERROR enabled, false otherwise.
     */
    public abstract boolean isErrorEnabled(
    );

    /**
     * Check whether this logger is enabled for the FATAL Level.
     *
     * @return
     *      true if this logger is FATAL enabled, false otherwise.
     */
    public abstract boolean isFatalEnabled(
    );

    /**
     * Check whether this logger is enabled for the INFO Level.
     *
     * @return
     *      true if this logger is INFO enabled, false otherwise.
     */
    public abstract boolean isInfoEnabled(
    );

    /**
     * Check whether this logger is enabled for the WARN Level.
     *
     * @return
     *      true if this logger is WARN enabled, false otherwise.
     */
    public abstract boolean isWarnEnabled(
    );

    /**
     * @param level
     */
    public abstract void setLevel(
      Level                             level
    );
    
    
    /**
     * Log a message object with the WARN level.
     *
     * @param obj
     *      The message object to log.
     */
    public abstract void warn(
      Object                            obj
    );
    
    /**
     * Log a message object with the WARN level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public abstract void warn(
      Object                            obj
    , Exception                         except
    );

}
