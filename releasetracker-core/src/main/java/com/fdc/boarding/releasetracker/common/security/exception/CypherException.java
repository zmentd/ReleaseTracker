package com.fdc.boarding.releasetracker.common.security.exception;

import com.fdc.boarding.core.log.LoggerProxy;

public class CypherException extends RuntimeException {
    //~ Static fields/initializers *********************************************
    private static final long           serialVersionUID    = 1L;
    public final static int             PASSWORD_ENCRYPT    = 0;
    public final static int             PASSWORD_DECRYPT    = 1;

    //~ Constructors ***********************************************************
    /**
     * Private constructor.
     * Call one of the static create methods.
     */
    protected CypherException(
    )
    {
        super();
    }
    /**
     * Private constructor.
     * Call one of the static create methods.
     */
    protected CypherException(
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
    public static CypherException create(
      LoggerProxy                       logger
    , String                            msg
    , Throwable                         cause
    , String                            method
    , Class<?>                          clazz
    , int                               code
    )
    {
        StringBuffer                    info;
        CypherException                 exception;

        info    = new StringBuffer();
        info.append( "An exception is being thrown from " );
        info.append( clazz.getName() );
        info.append( '.' );
        info.append( method );
        info.append( " :Exception Code " );
        info.append( code );
        info.append( ':' );
        info.append( msg );
        exception = new CypherException( info.toString() );
        logger.error( info.toString() );

        return exception;
    }

}
