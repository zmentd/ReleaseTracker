package com.fdc.boarding.releasetracker.usecase.workflow;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.usecase.AbstractRequest;

public class WorkflowRequest extends AbstractRequest implements Serializable {
	private static final long 			serialVersionUID 	= 1L;

	@NotNull
	@EntityExists( IWorkflow.class )
	private Long						workflowId;

	public WorkflowRequest(){
		super();
	}

	public Long getWorkflowId() {
		return workflowId;
	}
	
	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
}
