package com.fdc.boarding.releasetracker.console;

import javax.persistence.EntityManagerFactory;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.ServiceRegistry;

import fdinet.core.FDiNetServiceGroup;

public class ReleaseTrackerServicesMain {

	public ReleaseTrackerServicesMain(){
		super();
	}
	
	public void init(){
		try{
			//
			// Initialize our dependency injection framework. This will register all
			// of the services we have created.
			//
			CDIContext.getInstance();
			//
			// Load the EntityManagerFactory
			//
			CDIContext.getInstance().getBean( EntityManagerFactory.class );
			//
			// Initialize the FDiNet Services.
			//
			FDiNetServiceGroup.initServices( ServiceRegistry.getInstance().getServices() );
		} 
		catch( Exception e ){
			throw new RuntimeException( "Unable to start ReleaseTracker Services Server.", e );
		}
	}
	
	public static void main( String[] argv ) throws Exception{
		ReleaseTrackerServicesMain			main;
		
		main	= new ReleaseTrackerServicesMain();
		main.init();
	}
}
