package com.fdc.boarding.releasetracker.usecase.workflow;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.usecase.AbstractRequest;
import com.fdc.boarding.releasetracker.usecase.common.dto.CommentDto;

public class ApprovalRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	@NotNull
	@EntityExists( IWorkflow.class )
	private Long						workflowId;
	
	@NotNull
	@EntityExists( IPhaseApprovalType.class )
	private Long						phaseApprovalTypeId;
	
	@NotNull( groups = ApprovalValidation.class )
	@EntityExists( IUser.class )
	private Long						approverId;
	
	@Valid
	private CommentDto					comment;

	public ApprovalRequest(){
		super();
	}

	public Long getApproverId() {
		return approverId;
	}

	public CommentDto getComment() {
		return comment;
	}

	public Long getPhaseApprovalTypeId() {
		return phaseApprovalTypeId;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setApproverId(Long userId) {
		this.approverId = userId;
	}

	public void setComment(CommentDto comment) {
		this.comment = comment;
	}

	public void setPhaseApprovalTypeId(Long phaseApprovalTypeId) {
		this.phaseApprovalTypeId = phaseApprovalTypeId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

}
