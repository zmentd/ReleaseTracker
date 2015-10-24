package com.fdc.boarding.releasetracker.domain.team;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.ITeamImpactWorkflow;

public interface ITeamImpact extends IAuditable, IEntity<Long> {
	public abstract IIdea getIdea();
	public abstract Long getId();
	public abstract ITeam getTeam();
	public abstract IUser getRomOwner();
	public abstract void setIdea(IIdea idea);
	public abstract void setId(Long id);
	public abstract void setTeam(ITeam team);
	public abstract void setRomOwner(IUser romOwner);
	public abstract void setPlannedEffortNotOpen(boolean plannedEffortNotOpen);
	public abstract void setSupportOnly(boolean supportOnly);
	public abstract boolean isPlannedEffortNotOpen();
	public abstract boolean isSupportOnly();
	public abstract void setNoImpact(boolean noImpact);
	public abstract boolean isNoImpact();
	public abstract void setNoImpactDate(LocalDate noImpactDate);
	public abstract LocalDate getNoImpactDate();
	public abstract void setWorkflow(ITeamImpactWorkflow workflow);
	public abstract ITeamImpactWorkflow getWorkflow();
}