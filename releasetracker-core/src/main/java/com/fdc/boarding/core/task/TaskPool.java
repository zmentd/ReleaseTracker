package com.fdc.boarding.core.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.Duration;

/**
 *
 */
public class TaskPool
{
    private static TaskPool                 instance;
 
    protected ScheduledTask<AbstractTask>   worker;
    protected ScheduledExecutorService      scheduler;

    public static TaskPool getInstance(
    )
    {
        return getInstance( 5 );
    }

    public static TaskPool getInstance(
      int                               nThreads
    )
    {
        if( instance == null )
        {
            instance    = new TaskPool( nThreads );    
        }
        
        return instance;
    }
   
    private TaskPool(
      int                               nThreads
    )
    {
        scheduler   = Executors.newScheduledThreadPool( nThreads );
    }
    
    public void shutdown(
    )
    {
        worker.cancel( true );
        scheduler.shutdownNow();
    }
    
    public ScheduledTask<AbstractTask> schedule( 
      AbstractTask                      task
    , Duration                          delay   
    )
    {
        worker  = new ScheduledTask<AbstractTask>( scheduler );
        worker.schedule( task, delay.getStandardSeconds(), TimeUnit.SECONDS );
        
        return worker;
    }
    
    public void scheduleAtFixedRate( 
      AbstractTask                      task
    , Duration                          period  
    )
    {
        scheduleAtFixedRate( task, period, period );
    }
    
    public void scheduleAtFixedRate( 
      AbstractTask                      task
    , Duration                          initialDelay  
    , Duration                          period  
    )
    {
        scheduler.scheduleAtFixedRate( task, initialDelay.getStandardSeconds(), period.getStandardSeconds(), TimeUnit.SECONDS );
    }
}
