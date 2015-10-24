package com.fdc.boarding.core.task;

/**
 * A task listener
 */
public interface ITaskListener
{
    /**
     * @param task
     *      The completed task.
     */
    public void taskCompleted(
      AbstractTask                      task
    );

}
