package com.fdc.boarding.core.task;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 *
 */
public class TaskException extends RuntimeException
{
    //~ Static fields/initializers *********************************************
    private static final long           serialVersionUID    = 1L;

    public enum CODE
    {
        NESTED
      , INVALID
    }

    //~ Constructors ***********************************************************
    /**
     * Private constructor.
     * Call one of the static create methods.
     */
    protected TaskException(
    )
    {
        super();
    }

    /**
     * Private constructor.
     * Call one of the static create methods.
     */
    protected TaskException(
      StringBuffer                      buffer
    )
    {
        super( buffer.toString() );
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
    public static TaskException create(
      LoggerProxy                       logger
    , String                            msg
    , Throwable                         cause
    , String                            method
    , Class<?>                          clazz
    , CODE                              code
    )
    {
        StringBuffer                    info;
        TaskException                   exception;

        info    = new StringBuffer();
        info.append( "An exception is being thrown from " );
        info.append( clazz.getName() );
        info.append( '.' );
        info.append( method );
        info.append( ":Exception Code " );
        info.append( code );
        info.append( ':' );
        info.append( msg );
        if( cause != null )
        {
            info.append( ":Caused by:" );
            info.append( cause.getMessage() );
        }
        exception = new TaskException( info );
        exception.initCause( cause );

        return exception;
    }
}
