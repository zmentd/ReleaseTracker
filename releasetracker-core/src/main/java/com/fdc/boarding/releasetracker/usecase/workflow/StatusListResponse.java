package com.fdc.boarding.releasetracker.usecase.workflow;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.StatusDto;

public class StatusListResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private List<StatusDto>				list;
	
	public StatusListResponse(){
		super();
	}

	public List<StatusDto> getList() {
		return list;
	}

	public void setList(List<StatusDto> list) {
		this.list = list;
	}
}
