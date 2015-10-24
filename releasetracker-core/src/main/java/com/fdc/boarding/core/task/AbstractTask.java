package com.fdc.boarding.core.task;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 * Common definition for a runnable task.
 */
public abstract class AbstractTask implements Runnable
{
    public enum Status
    {
        Inactive
      , Started
      , Completed
    }
    protected LoggerProxy               logger;
    protected Exception                 exception;
    private Status                      status              = Status.Inactive;
    private ITaskListener               listener;

    /**
     * 
     */
    public AbstractTask(
    )
    {
        //
        // Default constructor.
        //
        super();
        logger      = LoggerProxy.getLogger( getClass() );
    }

    /**
     * To be filled in
     */
    public abstract void doExecute(
    );
    
    /**
     * @return
     */
    public Exception getException(
    )
    {
        return exception;
    }

    /**
     * @return
     */
    public Status getStatus(
    )
    {
        return status;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run(
    )
    {
        try
        {
            setStatus( Status.Started );
            doExecute();
        }
        catch( Exception e )
        {
            e.printStackTrace();
            if( e instanceof TaskException )
            {
                exception = e;
            }
            else
            {
                exception =  TaskException.create( logger
                                                 , "Task failed:"
                                                 , e
                                                 , "run"
                                                 , this.getClass()
                                                 , TaskException.CODE.NESTED
                                                 );
            }
        }
        finally
        {
            setStatus( Status.Completed );
            if( listener != null )
            {
                listener.taskCompleted( this );
            }
        }
    }

    /**
     * @param listener
     */
    public void setListener(
      ITaskListener                     listener
    )
    {
        this.listener   = listener;
    }

    /**
     * @param status
     */
    public void setStatus(
      Status                            status
    )
    {
        this.status = status;
    }

}
