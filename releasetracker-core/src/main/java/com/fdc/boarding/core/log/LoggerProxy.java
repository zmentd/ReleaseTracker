package com.fdc.boarding.core.log;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdc.boarding.core.util.StringUtil;


/**
 *  The proxy for all logging methods.
 *
 *  This logging facility will first check for log4j libraries
 *  in the path. If none exist, the Java Util logging will be used.
 *
 *  All log methods will first check for the level to be enabled before
 *  performing the log. This does not mean that is<Level>Enbaled methods
 *  should not be used. The can significantly increase performance when
 *  debug messages are being assembled.
 *
 *  All exception messages will have the exception stack trace and all
 *  parent causes written to the log, if the log level is sufficient.
 */
public class LoggerProxy implements Serializable
{
    private static final long                           serialVersionUID = 1L;
    private static transient Map<String, LoggerProxy>   loggers;
    private static List<String>                         loggerNames;
    
    static 
    {
        loggers     = new HashMap<String, LoggerProxy>();
        loggerNames = new ArrayList<String>();
    }
    
    /**
     * Convience function to be used for printing the canonical form of a path
     * name.  The function will handle all errors in determing the path, allways 
     * returning something resonable. 
     * 
     * @param fname
     *      The file name
     * 
     * @return
     *      The cannical path name for the specified file name; "null" if
     *      a null fname was passed in; or "NULL" if a zero length string 
     *      was passed in.
     *      
     */
    public static String getCanonicalPath( 
      String                            fname 
    ) 
    {
        String                          retVal;
        File                            file;
        
        if ( fname == null )
        {
            return "null";
        }
        if ( fname.length() == 0 )
        {
            return "NULL"; 
        }
        
        file = new File( fname );
        try 
        { 
            retVal = file.getCanonicalPath(); 
        }
        catch ( Exception ignored )
        {
            return fname;
        }
        
        return retVal;
    }
    
    /**
     * Shorthand for getLogger( clazz.getName() ). Retrieves the class 
     * for the calling class;
     *
     * @return
     *      The named LoggerProxy.
     */
    public static LoggerProxy getLogger(
    )
    {
        Throwable                       t;
        StackTraceElement               directCaller;
        
        t               = new Throwable();
        directCaller    = t.getStackTrace()[1];
        
        return getLogger( directCaller.getClassName() );
    }
  
    /**
     * Shorthand for getLogger( clazz.getName() ).
     *
     * @param clazz
     *      The name of clazz will be used as the name of the logger to
     *      retrieve. See getLogger( String ) for more detailed information.
     *
     * @return
     *      The named LoggerProxy.
     */
    public static LoggerProxy getLogger(
      Class<?>                          clazz
    )
    {
        return getLogger( clazz.getName() );
    }
    
    /**
     * Retrieve a logger named according to the value of the name parameter.
     * If the named logger already exists, then the existing instance will
     * be returned. Otherwise, a new instance is created.
     * By default, loggers do not have a set level but inherit it from their
     * neareast ancestor with a set level.
     *
     * @param name
     *      The name of the logger to retrieve.
     *
     * @return
     *      The named LoggerProxy.
     */
    public static LoggerProxy getLogger(
      String                            name
    )
    {
        LoggerProxy                     logger;
        
        if( !loggers.containsKey( name ) )
        {
            logger = new LoggerProxy( name );
            loggers.put( name, logger );
            if( !loggerNames.contains( name ) )
            {
                loggerNames.add( name );
            }
        }
        else
        {
            logger = loggers.get( name );
        }
        
        return logger;
    }

    //~ Instance fields ****************************************************************************
    protected transient AbstractLogger      mLogger;

    private String                          loggerFor;

    //~ Methods ************************************************************************************
    
    //~ Constructors *******************************************************************************
    /**
     * Constructor.
     *
     * @param name
     *      The logger name.
     */
    public LoggerProxy(
      String                            name
    )
    {
        loggerFor   = name;
        mLogger     = LogFactory.getLogger( name );
    }
    
    /**
     * Log a message object with the DEBUG level.
     *
     * @param obj
     *      The message object to log.
     */
    public void debug(
      Object                            obj
    )
    {
        if( !mLogger.isDebugEnabled() )
        {
            return;
        }
        mLogger.debug( obj );
    }

    /**
     * Log a message object with the DEBUG level.
     *
     * @param obj
     *      The message object to log.
     */
    public void debug(
      Object...                         obj
    )
    {
        if( !mLogger.isDebugEnabled() )
        {
            return;
        }
        mLogger.debug( StringUtil.joinAll( "", false, obj ) );
    }

    /**
     * Log a message object with the DEBUG level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public void debug(
      Object                            obj
    , Throwable                         except
    )
    {
        if( !mLogger.isDebugEnabled() )
        {
            return;
        }
        mLogger.debug( obj + " Exception Stack Trace:" + getStackTrace( except ) );
    }

    /**
     * Log a message object with the ERROR level.
     *
     * @param obj
     *      The message object to log.
     */
    public void error(
      Object                            obj
    )
    {
        if( !mLogger.isErrorEnabled() )
        {
            return;
        }
        mLogger.error( obj );
    }

    /**
     * Log a message object with the ERROR level.
     *
     * @param obj
     *      The message object to log.
     */
    public void error(
      Object...                         obj
    )
    {
        if( !mLogger.isErrorEnabled() )
        {
            return;
        }
        mLogger.error( StringUtil.joinAll( "", false, obj ) );
    }

    /**
     * Log a message object with the ERROR level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public void error(
      Object                            obj
    , Throwable                         except
    )
    {
        if( !mLogger.isErrorEnabled() )
        {
            return;
        }
        mLogger.error( obj + " Exception Stack Trace:" + getStackTrace( except ) );
    }

    /**
     * Log a message object with the ERROR level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public void error(
      Throwable                         except
    , Object...                         obj
    )
    {
        if( !mLogger.isErrorEnabled() )
        {
            return;
        }
        mLogger.error( StringUtil.joinAll( "", false, obj ) + " Exception Stack Trace:" + getStackTrace( except ) );
    }

    /**
     * Log a message object with the FATAL level.
     *
     * @param obj
     *      The message object to log.
     */
    public void fatal(
      Object                            obj
    )
    {
        if( !mLogger.isFatalEnabled() )
        {
            return;
        }
        mLogger.fatal( obj );
    }

    /**
     * Log a message object with the FATAL level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public void fatal(
      Object                            obj
    , Throwable                         except
    )
    {
        if( !mLogger.isFatalEnabled() )
        {
            return;
        }
        mLogger.fatal( obj + " Exception Stack Trace:" + getStackTrace( except ) );
    }
   
    /**
     * @return
     */
    public Level getLevel(
    )
    {
        Level                           level;
        
        level = mLogger.getLevel();
        if( level == null )
        {
            level = Level.WARN;
        }
        
        return level;
    }
    
    /**
     * @return
     *      The underlying logger;
     */
    public Object getRawLogger(
    )
    {
        return mLogger.getRawLogger();
    }
    /**
     * Create a string value of the entire stacktrace.
     *
     * @param except
     *      The exception.
     *
     * @return
     *      The stacktrace as a string.
     */
    public String getStackTrace(
      Throwable                         except
    )
    {
        StringWriter                    sw;
        PrintWriter                     pw;
        Throwable                       initExcept;

        sw          = new StringWriter();
        pw          = new PrintWriter( sw );
        initExcept  = except;
        while( initExcept != null )
        {
            initExcept.printStackTrace( pw );
            initExcept = initExcept.getCause();
        }

        return sw.toString();
    }
    
    /**
     * Log a message object with the INFO level.
     *
     * @param obj
     *      The message object to log.
     */
    public void info(
      Object                            obj
    )
    {
        if( !mLogger.isInfoEnabled() )
        {
            return;
        }
        mLogger.info( obj );
    }

    /**
     * Log a message object with the INFO level.
     *
     * @param obj
     *      The message object to log.
     */
    public void info(
      Object...                         obj
    )
    {
        if( !mLogger.isInfoEnabled() )
        {
            return;
        }
        mLogger.info( StringUtil.joinAll( "", false, obj ) );
    }

    /**
     * Log a message object with the INFO level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public void info(
      Object                            obj
    , Throwable                         except
    )
    {
        if( !mLogger.isInfoEnabled() )
        {
            return;
        }
        mLogger.info( obj + " Exception Stack Trace:" + getStackTrace( except ) );
    }

    /**
     * Check whether this logger is enabled for the DEBUG Level.
     * This function is intended to lessen the computational cost of disabled
     * log debug statements.
     *
     * For some cat Category object, when you write,
     *
     *       cat.debug("This is entry number: " + i );
     *
     * You incur the cost constructing the message, concatenatiion in this case,
     * regardless of whether the message is logged or not.
     *
     * If you are worried about speed, then you should write
     *
     *      if(cat.isDebugEnabled()) {
     *        cat.debug("This is entry number: " + i );
     *      }
     *
     * This way you will not incur the cost of parameter construction if debugging
     * is disabled for cat. On the other hand, if the cat is debug enabled, you
     * will incur the cost of evaluating whether the category is debug enabled twice.
     * Once in isDebugEnabled and once in the debug. This is an insignificant
     * overhead since evaluating a category takes about 1%% of the time it takes
     * to actually log.
     *
     * @return
     *      True if this category is enabled for DEBUG
     */
    public boolean isDebugEnabled(
    )
    {
        return mLogger.isDebugEnabled();
    }

    /**
     * Check whether this logger is enabled for the error Level.
     * See also isDebugEnabled().
     *
     * @return
     *      True if this category is enabled for error
     */
    public boolean isErrorEnabled(
    )
    {
        return mLogger.isErrorEnabled();
    }

    /**
     * Check whether this logger is enabled for the fatal Level.
     * See also isDebugEnabled().
     *
     * @return
     *      True if this category is enabled for fatal
     */
    public boolean isFatalEnabled(
    )
    {
        return mLogger.isFatalEnabled();
    }

    /**
     * Check whether this logger is enabled for the info Level.
     * See also isDebugEnabled().
     *
     * @return
     *      True if this category is enabled for info
     */
    public boolean isInfoEnabled(
    )
    {
        return mLogger.isInfoEnabled();
    }

    /**
     * Check whether this logger is enabled for the warn Level.
     * See also isDebugEnabled().
     *
     * @return
     *      True if this category is enabled for warn
     */
    public boolean isWarnEnabled(
    )
    {
        return mLogger.isWarnEnabled();
    }

    /**
     * Log given data in hex format.
     *
     * @param data
     *      The data to log.
     * @param prefix
     *      The prefix string to log with the hex data.
     */
    public void logHex(
      byte[]                            data
    , String                            prefix
    )
    {
        HexDump.dump( data, data.length, this, prefix );
    }

    /**
     * Log given data in hex format.
     *
     * @param data
     *      The data to log.
     * @param prefix
     *      The prefix string to log with the hex data.
     */
    public void logHexError(
      byte[]                            data
    , String                            prefix
    )
    {
        HexDump.dumpError( data, data.length, this, prefix );
    }

    /**
     * @param inputStream
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private void readObject(
      ObjectInputStream                 inputStream
    ) throws ClassNotFoundException, IOException 
    {
        List<String>                    names;
        
        inputStream.defaultReadObject();
        names   = new ArrayList<String>();
        loggers = new HashMap<String, LoggerProxy>();
        names.addAll( loggerNames );
        for( String name : names )
        {
            getLogger( name );
        }
        mLogger = LogFactory.getLogger( loggerFor );
    }

    /**
     * @param level
     */
    public void setLevel(
      Level                             level
    )
    {
        if( level != null )
        {
            mLogger.setLevel( level );
        }
    }
    
    /**
     * Log a message object with the WARN level.
     *
     * @param obj
     *      The message object to log.
     */
    public void warn(
      Object                            obj
    )
    {
        if( !mLogger.isWarnEnabled() )
        {
            return;
        }
        mLogger.warn( obj );
    }

    /**
     * Log a message object with the WARN level including
     * the stack trace of the Throwable t passed as parameter.
     *
     * @param obj
     *      The message object to log.
     * @param except
     *      The exception to log, including its stack trace.
     */
    public void warn(
      Object                            obj
    , Throwable                         except
    )
    {
        if( !mLogger.isWarnEnabled() )
        {
            return;
        }
        mLogger.warn( obj + " Exception Stack Trace:" + getStackTrace( except ) );
    }
}
