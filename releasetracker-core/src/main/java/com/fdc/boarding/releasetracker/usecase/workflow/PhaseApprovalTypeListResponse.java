package com.fdc.boarding.releasetracker.usecase.workflow;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseApprovalTypeDto;

public class PhaseApprovalTypeListResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private List<PhaseApprovalTypeDto>				list;
	
	public PhaseApprovalTypeListResponse(){
		super();
	}

	public List<PhaseApprovalTypeDto> getList() {
		return list;
	}

	public void setList(List<PhaseApprovalTypeDto> list) {
		this.list = list;
	}
}

