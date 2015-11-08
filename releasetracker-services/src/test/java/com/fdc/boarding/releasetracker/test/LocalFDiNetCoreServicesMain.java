package com.fdc.boarding.releasetracker.test;

import com.fdc.boarding.core.configuration.PropertiesLoader;

import fdinet.core.local.LocalCoreServices;

public class LocalFDiNetCoreServicesMain {

	public LocalFDiNetCoreServicesMain(){
		super();
	}
	
	public void init(){
		try{
			PropertiesLoader.loadFromClassloader( "workflow.properties", getClass() );
			LocalCoreServices.main( new String[]{} );
		} 
		catch( Exception e ){
			e.printStackTrace();
		}
	}
	
	public static void main( String[] argv ) throws Exception{
		LocalFDiNetCoreServicesMain			main;
		
		main	= new LocalFDiNetCoreServicesMain();
		main.init();
	}

}
