package com.fdc.boarding.releasetracker.domain.workflow;


public interface IWorkflowOwner {
	public Long getId();
	public IWorkflow getWorkflow();
	public void setId( Long id );
	public void setWorkflow( IWorkflow workflow );
}
