package com.fdc.boarding.core.jpa;

import java.util.Properties;

public class JpaProperties {
	private static volatile JpaProperties 	instance;
	
	public static JpaProperties getInstance(
	)
	{
		if( null == instance ) 
		{
			synchronized( JpaProperties.class )
			{
				if( null == instance )
				{
					instance = new JpaProperties();
				}
			}
		}
		
		return instance;
	}

	private Properties					properties;
	
	public JpaProperties(){
		super();
		properties	= new Properties();
	}
	
	public Properties getProperties(){
		return properties;
	}
	
	public void setProperty( String key, String value ){
		properties.setProperty( key, value );
	}
}
