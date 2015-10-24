/**
/////////////////////////////////////////////////////////////////////////////
//
//                                core
//
//                                 BusnException.java
//
//                           Proprietary Software Product
//
//                Copyright (c) 2005 by Baldwin Hackett & Meeks, Inc.
//
//                             All Rights Reserved
//
/////////////////////////////////////////////////////////////////////////////
//
//Date        Person  Comment
//----------  ------  -----------------------------------------------------
//Aug 24, 2008    TZman  Initial Release
//
/////////////////////////////////////////////////////////////////////////////
*/
package com.fdc.boarding.core.service.exception;

import com.fdc.boarding.core.log.LoggerProxy;

public class ServiceException extends RuntimeException
{
    //~ Static fields/initializers *********************************************
    private static final long           serialVersionUID = 1L;

    //~ Constructors ***********************************************************
    /**
     * @param message
     */
    protected ServiceException(
    )
    {
        super();
    }

    protected ServiceException(
      String                            msg
    )
    {
        super( msg );
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
    public static ServiceException create(
      LoggerProxy                       logger
    , String                            msg
    , Throwable                         cause
    , String                            method
    , Class<?>                          clazz
    )
    {
        StringBuffer                    info;
        ServiceException             	exception;

        info    = new StringBuffer();
        info.append( "An exception is being thrown from " );
        info.append( clazz.getName() );
        info.append( '.' );
        info.append( method );
        info.append( ':' );
        info.append( msg );
        exception = new ServiceException(msg);
        if( cause != null )
        {
            exception.initCause( cause );
            info.append( ":Caused by:" );
            info.append( cause.getMessage() );
        }
//        exception.setInfo( info );
//        exception.setLogger( logger );
//        exception.setClazz( clazz );
//        exception.setMethod( method );

        return exception;
    }
}
