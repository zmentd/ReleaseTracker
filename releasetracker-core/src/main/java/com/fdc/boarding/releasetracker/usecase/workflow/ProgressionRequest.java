package com.fdc.boarding.releasetracker.usecase.workflow;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.usecase.AbstractRequest;

public class ProgressionRequest extends AbstractRequest implements Serializable {
	private static final long 			serialVersionUID 	= 1L;

	@NotNull
	@EntityExists( IWorkflow.class )
	private Long						workflowId;

	@NotNull( groups = PhaseProgressionValidation.class )
	@EntityExists( IPhase.class )
	private Long						phaseId;
	
	@NotNull( groups = StatusProgressionValidation.class )
	@EntityExists( IStatus.class )
	private Long						statusId;
	
	public ProgressionRequest(){
		super();
	}

	public Long getPhaseId() {
		return phaseId;
	}
	
	public Long getStatusId() {
		return statusId;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
}
