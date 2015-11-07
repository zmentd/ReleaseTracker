package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.List;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.release.MilestoneType;

public interface IPhase extends IAuditable, IEntity<Long> {
	public abstract List<IStatus> getAvailableStatuses();
	public abstract String getDescription();
	public abstract Long getId();
	public abstract int getIndex();
	public abstract String getName();
	public abstract List<IPhase> getNextPhases();
	public abstract List<IPhaseApprovalType> getRequiredApprovalTypes();
	public abstract MilestoneType getType();
	public abstract void setAvailableStatuses(List<IStatus> availableStatuses);
	public abstract void setDescription(String description);
	public abstract void setId(Long id);
	public abstract void setIndex(int index);
	public abstract void setName(String name);
	public abstract void setNextPhases(List<IPhase> nextPhases);
	public abstract void setRequiredApprovalTypes(List<IPhaseApprovalType> requiredApprovalTypes);
	public abstract void setType(MilestoneType type);
}