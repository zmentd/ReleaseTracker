package com.fdc.boarding.releasetracker.usecase.workflow;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseCompletionDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.WorkflowDto;

public class PhaseProgressionResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;
	
	private WorkflowDto					workflow;
	private PhaseCompletionDto			completion;
	
	public PhaseProgressionResponse(){
		super();
	}

	public PhaseCompletionDto getCompletion() {
		return completion;
	}

	public WorkflowDto getWorkflow() {
		return workflow;
	}

	public void setCompletion(PhaseCompletionDto completion) {
		this.completion = completion;
	}

	public void setWorkflow(WorkflowDto workflow) {
		this.workflow = workflow;
	}
}