package com.fdc.boarding.core.log;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Factory used to create the desired type of logging utility.
 *
 * Currently if the log4j libraries are available they will be
 * used, otherwise java.util.logging will be used.
 */
public class LogFactory implements Serializable
{
    private static final long           serialVersionUID = 1L;
    
    //~ Constructors *******************************************************************************
    /**
     *
     */
    public LogFactory(
    )
    {
        super();
    }

    //~ Methods ************************************************************************************
    /**
     * Retrieve the Logger instance.
     *
     * @param name
     *      The name of the logger.
     *
     * @return
     *      The Logger.
     */
    public static AbstractLogger getLogger(
      String                            name
    )
    {
        AbstractLogger                  logger      = null;
        Class<?>                        clazz;
        Constructor<?>                  constructor;
        Object[]                        vargs;
        
        try
        {
            //
            // We want to dynamically load this class in case the
            // log4j libraries are not present.
            //
            if( Class.forName( "org.apache.log4j.Logger" ) != null )
            {
                vargs       = new String[]{ name };
                clazz       = Class.forName( "com.bhmi.util.log.Log4jLogger" );
                constructor = clazz.getConstructor( new Class[]{ String.class } );
                logger      = ( AbstractLogger )constructor.newInstance( vargs );

                return logger;
            }
        }
        catch( Exception ignored )
        {
            //
            // Ignore any exception, this means we do not
            // log4j and we need to use Java util logging.
            //
        }
        logger = JavaUtilLogger.getLogger( name );

        return logger;
    }

}
