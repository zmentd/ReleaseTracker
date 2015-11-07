package com.fdc.boarding.releasetracker.usecase.workflow;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseApprovalTypeDto;

public class PhaseApprovalTypeResponse extends AbstractResponse{
	private static final long 			serialVersionUID	= 1L;

	private PhaseApprovalTypeDto		phaseApprovalType;

	public PhaseApprovalTypeResponse() {
		super();
	}

	public PhaseApprovalTypeDto getPhaseApprovalType() {
		return phaseApprovalType;
	}

	public void setPhaseApprovalType(PhaseApprovalTypeDto phaseApprovalType) {
		this.phaseApprovalType = phaseApprovalType;
	}
	
}
