package com.fdc.boarding.core.configuration;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.fdc.boarding.core.configuration.exception.ConfigurationException;
import com.fdc.boarding.core.log.LoggerProxy;

/**
 *
 */
public class SystemProperties
{
    protected static volatile SystemProperties	instance;

    /**
     *
     * @return The instance of this Param object.
     */
    public static SystemProperties getInstance(
    )
    {
        if( instance == null )
        {
        	synchronized( SystemProperties.class )
        	{
				if( null == instance )
				{
					instance = new SystemProperties();
				}
			}
        }
        
        return instance;
    }
    
    protected LoggerProxy               logger          = LoggerProxy.getLogger( getClass() );
    protected Properties                properties;

    /**
     * 
     */
    public SystemProperties(
    )
    {
        //
        // Default constructor.
        //
        super();
        properties  = System.getProperties();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     *
     * @throws ConfigException
     *      If the value was not found.
     */
    public boolean getBoolean(
      String                            key
    ) throws ConfigurationException
    {
        Boolean                         value;

        value = getBooleanProperty( key );
        if( value == null )
        {
            throw new ConfigurationException( "Unable to load boolean value ( "
                                     + key
                                     + "). "
                                     );
        }

        return value.booleanValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Boolean.
     */
    public boolean getBoolean(
      String                            key
    , boolean                           defaultValue
    )
    {
        return getBooleanProperty( key, Boolean.valueOf( defaultValue ) ).booleanValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Boolean.
     */
    public boolean getBoolean(
      String                            key
    , Boolean                           defaultValue
    )
    {
        return getBooleanProperty( key, defaultValue ).booleanValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be found or
     *      converted to an Boolean.
     */
    public Boolean getBooleanProperty(
      String                            key
    )
    {
        String                          sValue;
        Boolean                         value       = null;

        sValue = properties.getProperty( key );

        if( sValue == null )
        {
            return null;
        }

        sValue = sValue.trim();
        if( "ON".equalsIgnoreCase( sValue ) )
        {
            sValue =  "TRUE";
        }
        else if( "OFF".equalsIgnoreCase( sValue ) )
        {
            sValue =  "FALSE";
        }
        else if( "TRUE".equalsIgnoreCase( sValue ) )
        {
            sValue =  "TRUE";
        }
        else if( "1".equalsIgnoreCase( sValue ) )
        {
            sValue =  "TRUE";
        }
        else if( "FALSE".equalsIgnoreCase( sValue ) )
        {
            sValue =  "FALSE";
        }
        else if( "YES".equalsIgnoreCase( sValue ) )
        {
            sValue =  "TRUE";
        }
        else if( "NO".equalsIgnoreCase( sValue ) )
        {
            sValue =  "FALSE";
        }
        else if( "0".equalsIgnoreCase( sValue ) )
        {
            sValue =  "FALSE";
        }
        else
        {
            logger.warn( "The value found for the requested Boolean key ( "
                       + key
                       + " ) cannot be matched ( case in-sensitive ) "
                       + "against one of the following: YES/NO; TRUE/FALSE; ON/OFF; 1/0."
                       + " Treating this as though it was not found."
                       );

            return null;
        }

        value = Boolean.valueOf( sValue );

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Boolean.
     */
    public Boolean getBooleanProperty(
      String                            key
    , Boolean                           defaultValue
    )
    {
        Boolean                         value       = null;

        value = getBooleanProperty( key );

        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find value for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public float getDouble(
      String                            key
    , Double                            defaultValue
    )
    {
        return getDoubleProperty( key, defaultValue ).floatValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be found or
     *      converted to an Integer.
     */
    public Double getDoubleProperty(
      String                            key
    )
    {
        String                          sValue;
        Double                          value       = null;

        sValue = getStringProperty( key );
        if( sValue == null )
        {
            return null;
        }

        sValue = sValue.trim();
        try
        {
            value = Double.valueOf( sValue );
        }
        catch( NumberFormatException nfe )
        {
            logger.error( "Unable to convert Paramater value ( "
                        + sValue
                        + " ) to an Integer. Key ( "
                        + key
                        + " ) used in retrieval."
                        , nfe
                        );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Integer.
     */
    public Double getDoubleProperty(
      String                            key
    , Double                            defaultValue
    )
    {
        Double                          value       = null;

        value = getDoubleProperty( key );
        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find value for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public File getFile(
      String                            key
    ) throws ConfigurationException
    {
        File                           value;

        value = getFileProperty( key );
        if( value == null )
        {
            throw new ConfigurationException( "Unable to load file value ( "
                                     + key
                                     + "). "
                                     );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public File getFile(
      String                            key
    , File                              defaultValue
    )
    {
        return getFileProperty( key, defaultValue );
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public File getFileProperty(
      String                            key
    )
    {
        String                          path;
        File                            value   = null;

        try
        {
            path    = getStringProperty( key );
            value   = new File( path );
        }
        catch( RuntimeException ignored )
        {
            logger.info( "Unable to load the file for property key ( "
                       + key
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public File getFileProperty(
      String                            key
    , File                              defaultValue
    )
    {
        File                            value;

        value = getFileProperty( key );
        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find file for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     *
     * @throws ConfigException
     *      If the value was not found.
     */
    public float getFloat(
      String                            key
    ) throws ConfigurationException
    {
        Float                           value;

        value = getFloatProperty( key );
        if( value == null )
        {
            throw new ConfigurationException( "Unable to load float value ( "
                                     + key
                                     + "). "
                                     );
        }

        return value.floatValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public float getFloat(
      String                            key
    , float                             defaultValue
    )
    {
        return getFloatProperty( key, Float.valueOf( defaultValue ) ).floatValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public float getFloat(
      String                            key
    , Float                             defaultValue
    )
    {
        return getFloatProperty( key, defaultValue ).floatValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be found or
     *      converted to an Integer.
     */
    public Float getFloatProperty(
      String                            key
    )
    {
        String                          sValue;
        Float                           value       = null;

        sValue = properties.getProperty( key );
        if( sValue == null )
        {
            return null;
        }

        sValue = sValue.trim();
        try
        {
            value = Float.valueOf( sValue );
        }
        catch( NumberFormatException nfe )
        {
            logger.error( "Unable to convert Paramater value ( "
                        + sValue
                        + " ) to an Integer. Key ( "
                        + key
                        + " ) used in retrieval."
                        , nfe
                        );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Integer.
     */
    public Float getFloatProperty(
      String                            key
    , Float                             defaultValue
    )
    {
        Float                           value       = null;

        value = getFloatProperty( key );
        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find value for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     *
     * @throws ConfigException
     *      If the value was not found.
     */
    public int getInt(
      String                            key
    ) throws ConfigurationException
    {
        Integer                         value;

        value = getIntegerProperty( key );
        if( value == null )
        {
            throw new ConfigurationException( "Unable to load int value ( "
                                     + key
                                     + "). "
                                     );
        }

        return value.intValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Integer.
     */
    public int getInt(
      String                            key
    , int                               defaultValue
    )
    {
        return getIntegerProperty( key, Integer.valueOf( defaultValue ) );
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Integer.
     */
    public int getInt(
      String                            key
    , Integer                           defaultValue
    )
    {
        return getIntegerProperty( key, defaultValue ).intValue();
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be found or
     *      converted to an Integer.
     */
    public Integer getIntegerProperty(
      String                            key
    )
    {
        String                          sValue;
        Integer                         value       = null;

        sValue = properties.getProperty( key );
        if( sValue == null )
        {
            return null;
        }

        sValue = sValue.trim();
        try
        {
            value = Integer.valueOf( sValue );
        }
        catch( NumberFormatException nfe )
        {
            logger.error( "Unable to convert Paramater value ( "
                        + sValue
                        + " ) to an Integer. Key ( "
                        + key
                        + " ) used in retrieval."
                        , nfe
                        );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Integer.
     */
    public Integer getIntegerProperty(
      String                            key
    , Integer                           defaultValue
    )
    {
        Integer                         value       = null;

        value = getIntegerProperty( key );
        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find value for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be found or
     *      converted to a Long.
     */
    public Long getLongProperty(
      String                            key
    )
    {
        String                          sValue;
        Long                            value       = null;

        sValue = getStringProperty( key );
        if( sValue == null )
        {
            return null;
        }

        sValue = sValue.trim();
        try
        {
            value = Long.valueOf( sValue );
        }
        catch( NumberFormatException nfe )
        {
            logger.error( "Unable to convert Paramater value ( "
                        + sValue
                        + " ) to an Integer. Key ( "
                        + key
                        + " ) used in retrieval."
                        , nfe
                        );
        }

        return value;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value. This
     *      function may return null if the value could not be converted to an
     *      Long.
     */
    public Long getLongProperty(
      String                            key
    , Long                              defaultValue
    )
    {
        Long                            value       = null;

        value = getLongProperty( key );
        if( value == null )
        {
            value = defaultValue;
            logger.warn( "Unable to find value for property key ("
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     *
     * @return The properties
     */
    public Properties getProperties(
    )
    {
        return properties;
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public Object getProperty(
      String                            key
    )
    {
        return properties.getProperty( key );
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public Object getProperty(
      String                            key
    , String                            defaultValue
    )
    {
        Object                          value;

        value = getProperty( key );

        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find value for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Retrieve a property as an array of strings, a comma 
     * will be used to parse the value as a list.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public String[] getPropertyArray(
      String                            key
    )
    {
        return getPropertyArray( key, "," );
    }

    /**
     * Retrieve a property as an array of strings, the given delimiter 
     * will be used to parse the value as a list.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public String[] getPropertyArray(
      String                            key
    , String                            delim
    )
    {
        String                          value;
        String[]                        array;
        
        value   = getStringProperty( key );
        array   = value.split( delim );
        
        return array;
    }

    /**
     * Retrieve a property as a list of strings, a comma 
     * will be used to parse the value as a list.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public List<String> getPropertyList(
      String                            key
    )
    {
        return getPropertyList( key, "," );
    }

    /**
     * Retrieve a property as a list of strings, the given delimiter 
     * will be used to parse the value as a list.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public List<String> getPropertyList(
      String                            key
    , String                            delim
    )
    {
        String                          value;
        List<String>                    list;
        
        value   = getStringProperty( key );
        list    = Arrays.asList( value.split( delim ) );
        
        return list;
    }
    
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public String getStringProperty(
      String                            key
    )
    {
        String                          sValue;

        sValue = properties.getProperty( key );
        if( sValue == null )
        {
            return null;
        }

        sValue = sValue.trim();

        return sValue;
    }
    
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     *
     * @param key
     *      The property key.
     * @param defaultValue
     *      A default value.
     *
     * @return
     *      The value in this property list with the specified key value.
     */
    public String getStringProperty(
      String                            key
    , String                            defaultValue
    )
    {
        String                          value;

        value = getStringProperty( key );

        if( value == null )
        {
            value = defaultValue;
            logger.info( "Unable to find value for property key ( "
                       + key
                       + " ), using default value ( "
                       + defaultValue
                       + " )."
                       );
        }

        return value;
    }

    /**
     * Retrieve a group of properties where the property key
     * begins with the given prefix.
     *
     * @param prefix
     *      The property prefix.
     *
     * @return
     *      The value in this property list with the specified key prefix.
     */
    public List<String> getStringPropertyGroup(
      String                            prefix
    )
    {
        String                          key;
        List<String>                    list;
        
        list    = new ArrayList<String>();
        for( Object obj : properties.keySet() )
        {
            key = ( String )obj;
            if( key.startsWith( prefix ) )
            {
                list.add( key );
            }
        }
        
        return list;
    }

    /**
     * Prints this property list out to the specified output stream.
     * This method is useful for debugging.
     *
     * @param out
     *      An output stream.
     */
    public void list(
      PrintStream                       out
    )
    {
        properties.list( out );
    }

    /**
     * Prints this property list out to the specified output stream.
     * This method is useful for debugging.
     *
     * @param out
     *      An output stream.
     */
    public void list(
      PrintWriter                       out
    )
    {
        properties.list( out );
    }

    public void logProperties(
    )
    {
        String                          key;
        Iterator<?>                     iter;


        if( logger.isDebugEnabled() )
        {
            iter = properties.keySet().iterator();
            while( iter.hasNext() )
            {
                key = ( String )iter.next();
                logger.debug( "Property found " + key + " = " + properties.getProperty( key ) );
            }
        }
    }

    /**
     * Returns an enumeration of all the keys in this property list, including
     * distinct keys in the default property list if a key of the same name has
     * not already been found from the main properties list.
     *
     * @return
     *      An enumeration of all the keys in this property list, including the
     *      keys in the default property list.
     */
    public Enumeration<?> propertyNames(
    )
    {
        return properties.propertyNames();
    }
    
    /**
     * @param key
     * @param value
     */
    public void setBooleanProperty(
      String                            key
    , boolean                           value
    )
    {
        setProperty( key, String.valueOf( value ) );
    }
    
    /**
     * @param key
     * @param value
     */
    public void setDoubleProperty(
      String                            key
    , double                            value
    )
    {
        setProperty( key, String.valueOf( value ) );
    }
    
    /**
     * @param key
     * @param value
     */
    public void setFloatProperty(
      String                            key
    , float                             value
    )
    {
        setProperty( key, String.valueOf( value ) );
    }
    
    /**
     * @param key
     * @param value
     */
    public void setIntProperty(
      String                            key
    , int                               value
    )
    {
        setProperty( key, String.valueOf( value ) );
    }
    
    /**
     * @param key
     * @param value
     */
    public void setLongProperty(
      String                            key
    , long                              value
    )
    {
        setProperty( key, String.valueOf( value ) );
    }
    
    /**
     * Properties are stored as strings.
     * 
     * @param key
     * @param value
     */
    public void setProperty(
      String                            key
    , String                            value
    )
    {
        properties.put( key, value );
    }
}
