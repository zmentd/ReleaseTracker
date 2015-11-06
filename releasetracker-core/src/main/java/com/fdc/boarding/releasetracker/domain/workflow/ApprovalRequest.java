package com.fdc.boarding.releasetracker.domain.workflow;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.domain.AbstractRequest;
import com.fdc.boarding.releasetracker.domain.common.dto.CommentDto;

public class ApprovalRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	private Long						workflowId;
	private Long						phaseApprovalTypeId;
	private CommentDto					comment;
	
	public ApprovalRequest(){
		super();
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public Long getPhaseApprovalTypeId() {
		return phaseApprovalTypeId;
	}

	public CommentDto getComment() {
		return comment;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public void setPhaseApprovalTypeId(Long phaseApprovalTypeId) {
		this.phaseApprovalTypeId = phaseApprovalTypeId;
	}

	public void setComment(CommentDto comment) {
		this.comment = comment;
	}

}
