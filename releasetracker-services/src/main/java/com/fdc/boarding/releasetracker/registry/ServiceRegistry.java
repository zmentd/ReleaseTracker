package com.fdc.boarding.releasetracker.registry;

import java.util.ArrayList;
import java.util.List;

import fdinet.core.FDiNetServiceBase;

public class ServiceRegistry {
	
	private static volatile ServiceRegistry instance;
	
	public static ServiceRegistry getInstance(){
		if (null == instance) {
			synchronized (ServiceRegistry.class) {
				if (null == instance) {
					instance = new ServiceRegistry();
				}
			}
		}
		
		return instance;
	}
	
	private List<FDiNetServiceBase>		services;
	
	public ServiceRegistry(){
		super();
		services	= new ArrayList<>();
	}
	
	public void registerService( FDiNetServiceBase service ){
		services.add( service );
	}
	
	public FDiNetServiceBase[] getServices(){
		return services.toArray( new FDiNetServiceBase[]{} );
	}
}
