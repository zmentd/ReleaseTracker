package com.fdc.boarding.releasetracker.usecase.workflow;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseDto;

public class PhaseListResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private List<PhaseDto>				list;
	
	public PhaseListResponse(){
		super();
	}

	public List<PhaseDto> getList() {
		return list;
	}

	public void setList(List<PhaseDto> list) {
		this.list = list;
	}
}
