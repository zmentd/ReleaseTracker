package com.fdc.boarding.releasetracker.domain.workflow;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IPhaseApprovalType extends IAuditable, IEntity<Long> {

	public abstract Long getId();
	public abstract String getName();
	public abstract IPhase getPhase();
	public abstract Integer getSequence();
	public abstract ApprovalType getType();
	public abstract void setId(Long id);
	public abstract void setName(String name);
	public abstract void setPhase(IPhase phase);
	public abstract void setSequence(Integer sequence);
	public abstract void setType(ApprovalType type);
}