package com.fdc.boarding.releasetracker.usecase.workflow;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseCompletionDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.StatusCompletionDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.WorkflowDto;

public class StatusProgressionResponse  extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;
	
	private WorkflowDto					workflow;
	private PhaseCompletionDto			phaseCompletion;
	private StatusCompletionDto			statusCompletion;
	
	public StatusProgressionResponse(){
		super();
	}

	public PhaseCompletionDto getPhaseCompletion() {
		return phaseCompletion;
	}

	public StatusCompletionDto getStatusCompletion() {
		return statusCompletion;
	}

	public WorkflowDto getWorkflow() {
		return workflow;
	}

	public void setPhaseCompletion(PhaseCompletionDto completion) {
		this.phaseCompletion = completion;
	}

	public void setStatusCompletion(StatusCompletionDto statusCompletion) {
		this.statusCompletion = statusCompletion;
	}

	public void setWorkflow(WorkflowDto workflow) {
		this.workflow = workflow;
	}
}
