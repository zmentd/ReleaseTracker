package com.fdc.boarding.releasetracker.domain.workflow;

import com.fdc.boarding.releasetracker.domain.AbstractResponse;
import com.fdc.boarding.releasetracker.domain.workflow.dto.PhaseApprovalDto;

public class ApprovalResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;
	
	private PhaseApprovalDto 			phaseApproval;
	
	public ApprovalResponse(){
		super();
	}

	public PhaseApprovalDto getPhaseApproval() {
		return phaseApproval;
	}

	public void setPhaseApproval(PhaseApprovalDto approval) {
		this.phaseApproval = approval;
	}
}
