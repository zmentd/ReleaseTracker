package com.fdc.boarding.releasetracker.usecase.workflow;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseApprovalDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.WorkflowDto;

public class ApprovalResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;
	
	private PhaseApprovalDto 			phaseApproval;
	private WorkflowDto					workflow;
	
	public ApprovalResponse(){
		super();
	}

	public PhaseApprovalDto getPhaseApproval() {
		return phaseApproval;
	}

	public WorkflowDto getWorkflow() {
		return workflow;
	}

	public void setPhaseApproval(PhaseApprovalDto approval) {
		this.phaseApproval = approval;
	}

	public void setWorkflow(WorkflowDto workflow) {
		this.workflow = workflow;
	}
}
