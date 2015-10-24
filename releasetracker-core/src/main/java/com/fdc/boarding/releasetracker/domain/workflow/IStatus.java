package com.fdc.boarding.releasetracker.domain.workflow;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;


public interface IStatus extends IAuditable, IEntity<Long> {
	public abstract Long getId();
	public abstract String getName();
	public abstract String getDescription();
	public abstract StatusType getType();
	public abstract void setId(Long id);
	public abstract void setName(String name);
	public abstract void setDescription(String description);
	public abstract void setType(StatusType type);
}