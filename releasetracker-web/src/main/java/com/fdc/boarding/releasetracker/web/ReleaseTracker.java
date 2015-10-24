package com.fdc.boarding.releasetracker.web;

import com.fdc.boarding.core.configuration.PropertiesLoader;
import com.fdc.boarding.core.configuration.SystemProperties;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;

import fdinet.core.FDiNetServiceGroup;

public class ReleaseTracker {

	public ReleaseTracker(){
		super();
	}
	
	public void init(){
		WebServer				server;
		
		try{
			//
			// Initialize our dependency injection framework. This will register all
			// of the services we have created.
			//
			PropertiesLoader.loadFromClassloader( "workflow.properties", getClass() );
			CDIContext.getInstance();
			server	= new WebServer( SystemProperties.getInstance().getStringProperty( "workflow.webproxy.service.name" ) );
			FDiNetServiceGroup.initServices( server );
		} 
		catch( Exception e ){
			throw new RuntimeException( "Unable to start Release Tracker Web Server.", e );
		}
	}

	public static void main(String[] argv) throws Exception {
		ReleaseTracker			main;
		
		main	= new ReleaseTracker();
		main.init();
	}


}
