package com.fdc.boarding.core.task;

import java.util.ArrayList;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 * Task list, wait for all to complete.
 */
public class TaskList extends ArrayList<AbstractTask> implements ITaskListener
{
    private static final long           serialVersionUID    = 1L;
    protected LoggerProxy               logger  = LoggerProxy.getLogger( getClass() );
    private Object                      lock    = new Object();

    /* (non-Javadoc)
     * @see com.bhmi.util.task.ITaskListener#taskCompleted(com.bhmi.util.task.AbstractTask)
     */
    public void taskCompleted(
      AbstractTask                      task
    )
    {
        logger.debug( "Task completed." );
        add( task );
        synchronized( lock )
        {
            lock.notifyAll();
        }
    }

    /**
     * @param numTasks
     * @param timeout
     */
    public void waitForCompletion(
      int                               numTasks
    , long                              timeout
    )
    {
        synchronized( lock )
        {
            try
            {
                while( numTasks > size() )
                {
                    lock.wait( timeout );
                }
            }
            catch( InterruptedException ie )
            {
                logger.warn( "Task interrupted before completion:" + ie.getMessage() );
            }
        }
    }

}