package com.fdc.boarding.core.task;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public abstract class AbstractWaitedOperation
{
    private ReentrantLock           responseLock        = new ReentrantLock();
    private Condition               responseReceived    = responseLock.newCondition();
    protected Exception             exception;
    
    public AbstractWaitedOperation(
    )
    {
        super();
    }

    public abstract void execute(
    ) throws Exception;
    
    /**
     * @return 
     *    The exception
     */
    public Exception getException(
    )
    {
        return exception;
    }
    
    public void operationCompleted(
    )
    {
        responseLock.lock();
        try
        {
            responseReceived.signalAll();
        }
        finally
        {
            responseLock.unlock();
        }
    }

    /**
     * A waited run method.
     *
     * @throws InterruptedException
     *      If the thread is interrupted.
     */
    public void runAndWait(
    ) throws InterruptedException
    {
        responseLock.lock();
        try
        {
            new Runnable( )
            {
                public void run(
                )
                {
                    try
                    {
                        execute();
                    }
                    catch( Exception e )
                    {
                        exception   = e;
                    }
                }
            }.run();
           
            responseReceived.await();
        }
        finally
        {
            responseLock.unlock();
        }
    }
}
