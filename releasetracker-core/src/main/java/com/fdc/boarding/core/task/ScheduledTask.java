package com.fdc.boarding.core.task;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 *
 */
public class ScheduledTask<V> implements ScheduledFuture<V>
{
    private LoggerProxy                 logger          = LoggerProxy.getLogger( getClass() );
    private ScheduledFuture<V>          future;
    private Runnable                    task;
    private ScheduledExecutorService    scheduler;
    private long                        initialDelay;
    private TimeUnit                    unit;
    
    public ScheduledTask(
      ScheduledExecutorService          scheduler
    )
    {
        super();
        this.scheduler  = scheduler;
    }
    
    /* (non-Javadoc)
     * @see java.util.concurrent.Future#cancel(boolean)
     */
    public boolean cancel( 
      boolean                           mayInterruptIfRunning 
    )
    {
        return future.cancel( mayInterruptIfRunning );
    }
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo( 
      Delayed                           o 
    )
    {
        return future.compareTo( o );
    }
    
    @SuppressWarnings( "unchecked" )
    public void executeImmediately(
    )
    {
        logger.debug( "Canceling scheduled task to execute immediately." );
        future.cancel( true );
        logger.debug( "Scheduling task:delay=" + 0 + " timeunit=" + TimeUnit.MILLISECONDS );
        future  = ( ScheduledFuture<V> )scheduler.schedule( task, 0, TimeUnit.MILLISECONDS );
    }
    
    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get()
     */
    public V get(
    ) throws InterruptedException, ExecutionException
    {
        return future.get();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get(long, java.util.concurrent.TimeUnit)
     */
    public V get( 
      long                              timeout
    , TimeUnit                          timeUnit 
    ) throws InterruptedException, ExecutionException, TimeoutException
    {
        return future.get( timeout, timeUnit );
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
     */
    public long getDelay( 
      TimeUnit                          timeUnit 
    )
    {
        return future.getDelay( timeUnit );
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isCancelled()
     */
    public boolean isCancelled(
    )
    {
        return future.isCancelled();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isDone()
     */
    public boolean isDone(
    )
    {
        return future.isDone();
    }

    @SuppressWarnings( "unchecked" )
    public void rescheduleTask(
      long                              delay
    )
    {
        logger.debug( "Scheduling task:delay=" + delay + " timeunit=" + TimeUnit.SECONDS );
        future      = ( ScheduledFuture<V> )scheduler.schedule( task, delay, TimeUnit.SECONDS );
    }

    @SuppressWarnings( "unchecked" )
    public void schedule(
      Runnable                          command
    , long                              delay
    , TimeUnit                          timeUnit
    )
    {
        task            = command;
        initialDelay    = delay;
        unit            = timeUnit;
        future          = ( ScheduledFuture<V> )scheduler.schedule( task, initialDelay, unit );
    }
}
