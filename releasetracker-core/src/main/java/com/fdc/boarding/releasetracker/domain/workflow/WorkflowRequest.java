package com.fdc.boarding.releasetracker.domain.workflow;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.domain.AbstractRequest;

public class WorkflowRequest extends AbstractRequest implements Serializable {
	private static final long 			serialVersionUID 	= 1L;

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
