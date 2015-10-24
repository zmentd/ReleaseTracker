package com.fdc.boarding.core.task;

import java.util.Collection;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 * Task manager.
 */
public class TaskManager
{
    protected LoggerProxy               logger  = LoggerProxy.getLogger( getClass() );
    
    /**
     * @param tasks
     * @param timeout
     * @return
     */
    public Collection<AbstractTask> executeAndWait(
      Collection<AbstractTask>          tasks
    , long                              timeout
    )
    {
        long                            usedTimeout;
        TaskList                        completedTasks;
        
        if( ( tasks == null ) || ( tasks.size() == 0 ) )
        {
            return null;
        }
        if( timeout < 1 )
        {
            usedTimeout = 1;
        }
        else
        {
            usedTimeout = timeout;
        }
        completedTasks = scheduleTasks( tasks );
        completedTasks.waitForCompletion( tasks.size(), usedTimeout );
        logger.debug( "All tasks completed." );
        
        return completedTasks;    
        
    }
    
    /**
     * @param tasks
     * @return
     */
    private TaskList scheduleTasks(
      Collection<AbstractTask>          tasks
    )
    {
        TaskList                        completedTasks;

        completedTasks = new TaskList();
        for( AbstractTask task : tasks )
        {
            task.setListener( completedTasks );
            logger.debug( "Scheduling concurrent send task." );
            new Thread( task ).start();
        }
        
        return completedTasks;
    }
    
}
