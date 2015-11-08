package com.fdc.boarding.releasetracker.usecase.workflow;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.usecase.AbstractRequest;

public class WorkflowCommentsRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	@NotNull
	@EntityExists( IWorkflow.class )
	private Long						workflowId;
	
	@NotNull( groups = ApprovalValidation.class )
	@EntityExists( IUser.class )
	private Long						userId;
	
	private int							count;
	
	public WorkflowCommentsRequest(){
		super();
	}

	public int getCount() {
		return count;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	
}
