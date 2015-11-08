package com.fdc.boarding.releasetracker.usecase.idea;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.usecase.AbstractRequest;
import com.fdc.boarding.releasetracker.usecase.workflow.ApprovalValidation;

public class AssignedToRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	@NotNull( groups = ApprovalValidation.class )
	@EntityExists( IUser.class )
	private Long						assignedToId;
	
	public AssignedToRequest(){
		super();
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(Long assignedToId) {
		this.assignedToId = assignedToId;
	}
}
