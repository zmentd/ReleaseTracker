package com.fdc.boarding.core.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.fdc.boarding.core.configuration.exception.ConfigurationException;
import com.fdc.boarding.core.log.LoggerProxy;

public class PropertiesLoader {
    protected static LoggerProxy        logger      = LoggerProxy.getLogger( PropertiesLoader.class );
    
    
    
    /**
     *  If we have not yet loaded the config file, or it has changed since we
     *  last loaded it, load it.
     *
     *  @exception      ConfigurationException
     *                      An error occurred when loading the file.
     */
    public static void loadFromClassloader(
      String                          	propsFile
    , Class<?>							clazz
    ) throws ConfigurationException
    {
		URL							resource;
		
		try {
			resource = clazz.getClassLoader().getResource( propsFile );
			PropertiesLoader.load( resource.openStream() );
		} 
        catch( IOException fnfe )
        {
            throw new ConfigurationException( "Missing properties file ( " + propsFile + " )" );
        }
    }
    
    /**
     *  If we have not yet loaded the config file, or it has changed since we
     *  last loaded it, load it.
     *
     *  @exception      ConfigurationException
     *                      An error occurred when loading the file.
     */
    public static void load(
      File                              propsFile
    ) throws ConfigurationException
    {
        FileInputStream                 propertyFileStream  = null;

        try
        {
            propertyFileStream = new FileInputStream( propsFile.getPath() );
            load( propertyFileStream );
            logger.debug( "Properties file " + propsFile + " loaded" );
        }
        catch( FileNotFoundException fnfe )
        {
            throw new ConfigurationException( "Missing properties file ( " + propsFile + " )" );
        }
        finally
        {
            if( propertyFileStream != null )
            {
                try
                {
                    propertyFileStream.close();
                }
                catch( IOException ioe )
                {
                    throw new ConfigurationException( "Unable to load properties file ( " + propsFile + " ); " + ioe );
                }
            }
        }
    }

    /**
     * Reads a property list (key and element pairs) from the input stream.
     *
     * @param input
     *      the input stream.
     *
     * @throws ConfigurationException
     *      If an error occurred when reading from the input stream.
     */
    public static void load(
      InputStream                       input
    ) throws ConfigurationException
    {
        Properties                      properties;

        try
        {
            properties      = System.getProperties();
            properties.load( input );
            logger.debug( "Properties file loaded" );
        }
        catch( IOException ioe )
        {
            throw new ConfigurationException( "Unable to load properties file from inputstream; " + ioe );
        }
    }

    /**
     * Loads a set of properties into this property.
     *
     * @param properties
     *      The properties to load.
     *
     * @throws IOException
     *      If an error occurred when reading from the input stream.
     */
    public static void load(
      Properties                        properties
    )
    {
        Properties                      systemProperties;

        systemProperties    = System.getProperties();
        systemProperties.putAll( properties );
        logger.debug( "Properties file loaded" );
    }

}
