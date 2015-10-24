package com.fdc.boarding.core.query.exception;

import java.io.Serializable;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 *
 */
public class QueryException extends Exception implements Serializable
{
    //~ Static fields/initializers *********************************************
    private static final long           serialVersionUID    = 1L;
    public final static int             INIT_FAILED         = 0;
    public final static int             QUERY_FAILED        = 1;

    //~ Constructors ***********************************************************
    /**
     * Private constructor.
     * Call one of the static create methods.
     */
    protected QueryException(
    )
    {
        super();
    }
    /**
     * Private constructor.
     * Call one of the static create methods.
     */
    protected QueryException(
      String                            message
    )
    {
        super( message );
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
    public static QueryException create(
      LoggerProxy                       logger
    , String                            msg
    , Throwable                         cause
    , String                            method
    , Class<?>                          clazz
    , int                               code
    )
    {
        StringBuffer                    info;
        QueryException         exception;

        info    = new StringBuffer();
        info.append( "An exception is being thrown from " );
        info.append( clazz.getName() );
        info.append( '.' );
        info.append( method );
        info.append( ":Exception Code " );
        info.append( code );
        info.append( ':' );
        info.append( msg );
        exception = new QueryException( info.toString() );
        if( cause != null )
        {
            exception.initCause( cause );
        }
        logger.error( info.toString() );

        return exception;
    }
}
