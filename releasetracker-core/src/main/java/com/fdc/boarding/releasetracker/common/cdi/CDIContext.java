package com.fdc.boarding.releasetracker.common.cdi;

import java.lang.annotation.Annotation;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class CDIContext 
{
	private static volatile CDIContext 	instance;
	
	public static CDIContext getInstance(
	)
	{
		if( null == instance ) 
		{
			synchronized( CDIContext.class )
			{
				if( null == instance )
				{
					instance = new CDIContext();
				}
			}
		}
		
		return instance;
	}

    private final Weld 					weld;
    private final WeldContainer 		container;

    private CDIContext()
    {
        try
        {
			weld 		= new Weld();
			container	= weld.initialize();
//			Runtime.getRuntime().addShutdownHook( new Thread()
//			{
//			    @Override
//			    public void run()
//			    {
//			        weld.shutdown();
//			    }
//			});
		}catch( Exception e )
        {
			throw new RuntimeException( e );
		}
    }

    public <T> T getBean( Class<T> type )
    {
        return container.instance().select( type ).get();
    }

    public <T> T getBean( Class<T> type, Annotation... qualifiers )
    {
        return container.instance().select( type, qualifiers).get();
    }
   
    public void shutdown(){
    	weld.shutdown();
    	instance	= null;
    }
}
