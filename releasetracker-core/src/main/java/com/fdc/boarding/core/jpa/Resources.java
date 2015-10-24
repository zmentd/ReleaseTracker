package com.fdc.boarding.core.jpa;

import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdc.boarding.core.log.LoggerProxy;


@ApplicationScoped
public class Resources {
	final LoggerProxy 					logger 				= LoggerProxy.getLogger();

	private EntityManagerFactory		emf;
	
    @Produces
    public EntityManagerFactory getEntityManager(InjectionPoint ip) {
    	Properties						properties;
    	
    	if( emf == null ){
    		logger.info( "Initializing EntityManagerFactory." );
    		properties	= System.getProperties();
    		emf			= Persistence.createEntityManagerFactory( "primary", properties );
    	}
    
        return emf;
    }
}
