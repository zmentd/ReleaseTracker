package com.fdc.boarding.core.configuration.exception;

import com.fdc.boarding.core.log.LoggerProxy;

public class ConfigurationException extends RuntimeException {

	private static final long 			serialVersionUID = 1L;

	//~ Constructors *******************************************************************************
    /**
     *
     */
    public ConfigurationException(
    )
    {
        super();
    }

    /**
     * @param message
     */
    public ConfigurationException(
      String                            message
    )
    {
        super( message );
    }

    /**
     * @param cause
     */
    public ConfigurationException(
      Throwable                         cause
    )
    {
        super( cause );
    }

    /**
     * @param message
     * @param cause
     */
    public ConfigurationException(
      String                            message
    , Throwable                         cause
    )
    {
        super( message, cause );
    }
    
    //~ Methods ************************************************************************************
    /**
     * Creates a new exception intialized with the given message
     * and initializes the cause to the one given.
     * The message given is passed to the Logger.severe method of JDK and the Logger.throwing
     * method is invoked.
     *
     * @param logger
     *      The logger to act on.
     * @param msg
     *      The error message.
     * @param cause
     *      The cause of this exception.
     * @param method
     *      The name of the method throwing this exception.
     * @param clazz
     *      The class throwing exception.
     *
     * @return
     *      The newly created exception.
     */
    public static ConfigurationException create(
      LoggerProxy                       logger
    , String                            msg
    , Throwable                         cause
    , String                            method
    , Class<?>                          clazz
    )
    {
        StringBuffer                    info;
        ConfigurationException    		exception;

        info    = new StringBuffer();
        info.append( "An exception is being thrown from " );
        info.append( clazz.getName() );
        info.append( '.' );
        info.append( method );
        info.append( ':' );
        info.append( msg );
        if( cause != null )
        {
            info.append( ":Caused by:" );
            info.append( cause.getMessage() );
        }
        exception = new ConfigurationException( info.toString() );
        exception.initCause( cause );

        return exception;
    }

}
