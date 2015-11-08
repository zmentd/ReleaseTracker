package com.fdc.boarding.releasetracker.usecase.workflow;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.common.dto.CommentDto;

public class WorkflowCommentsResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private List<CommentDto>			list;
	
	public WorkflowCommentsResponse(){
		super();
	}

	public List<CommentDto> getList() {
		return list;
	}

	public void setList(List<CommentDto> list) {
		this.list = list;
	}
}
