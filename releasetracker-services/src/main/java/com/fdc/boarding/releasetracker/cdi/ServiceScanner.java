package com.fdc.boarding.releasetracker.cdi;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

import com.fdc.boarding.core.configuration.PropertiesLoader;
import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.ServiceRegistry;
import com.fdc.boarding.releasetracker.registry.annotation.Register;

import fdinet.core.FDiNetServiceBase;
import fdinet.core.annotations.FDiNetService;

public class ServiceScanner implements Extension {
	private LoggerProxy								logger				= LoggerProxy.getLogger();
	private Set<Class<? extends FDiNetServiceBase>>	serviceClasses;
	
	public ServiceScanner(){
		super();
		serviceClasses	= new HashSet<>();
		//
		// We need to load the properties here as this will be before classes are loaded
		// and FDiNet grabs it's properties during class loading.
		//
		PropertiesLoader.loadFromClassloader( "workflow.properties", getClass() );
	}
	
	public <T> void processAnnotatedServiceType( 
      @Observes
      @WithAnnotations( { Register.class } )
      ProcessAnnotatedType<T>           processAnnotatedTypeEvent 
    , BeanManager                       beanManager
    )
    {
        final AnnotatedType<T>          type;
        Class<? extends FDiNetServiceBase>		baseClass;
        
        type    = processAnnotatedTypeEvent.getAnnotatedType();
        if( FDiNetServiceBase.class.isAssignableFrom( type.getJavaClass() ) ){
        	baseClass	= type.getJavaClass().asSubclass( FDiNetServiceBase.class );
	        for( Class<?> inter : baseClass.getSuperclass().getInterfaces() ){
	        	if( inter.isAnnotationPresent( FDiNetService.class ) ){
	        		logger.info( "Located service " + baseClass.getName() );
	        		serviceClasses.add( baseClass );
	        	}
	        }
        }
    }
    
    void afterBeanDiscovery(
      @Observes 
      AfterBeanDiscovery 				abd
    , BeanManager 						bm
    ) 
    {
        FDiNetServiceBase				base;
    
        for( Class<? extends FDiNetServiceBase> serviceClass : serviceClasses )
        {
        	try 
        	{
				logger.info( "Registering service " + serviceClass.getName() );
				base	= serviceClass.newInstance();
//				base	= CDIContext.getInstance().getBean( serviceClass );
				ServiceRegistry.getInstance().registerService( base );
			} 
        	catch( Exception e ) 
        	{
				throw new RuntimeException( "Unable to instantiate service " + serviceClass.getName(), e );
			}
        }
    }
}
