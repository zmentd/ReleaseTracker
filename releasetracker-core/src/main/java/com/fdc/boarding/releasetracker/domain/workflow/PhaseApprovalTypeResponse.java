package com.fdc.boarding.releasetracker.domain.workflow;

import com.fdc.boarding.releasetracker.domain.AbstractResponse;
import com.fdc.boarding.releasetracker.domain.workflow.dto.PhaseApprovalTypeDto;

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
