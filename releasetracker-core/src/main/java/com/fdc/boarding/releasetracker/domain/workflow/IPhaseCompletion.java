package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IPhaseCompletion extends IAuditable, IEntity<Long> {
	public abstract void addPhaseApproval(IPhaseApproval phaseApproval);
	public abstract LocalDate getCompletionDate();
	public abstract Integer getDaysFromExpectedCompletion();
	public abstract Integer getDaysInPhase();
	public abstract LocalDate getEntryDate();
	public abstract LocalDate getExpectedCompletionDate();
	public abstract Long getId();
	public abstract IPhase getPhase();
	public abstract Set<IPhaseApproval> getPhaseApprovals();
	public abstract IWorkflow getWorkflow();
	public abstract void removePhaseApproval(IPhaseApproval phaseApproval);
	public abstract void setCompletionDate(LocalDate completionDate);
	public abstract void setDaysFromExpectedCompletion(Integer daysFromExpectedCompletion);
	public abstract void setDaysInPhase(Integer daysInPhase);
	public abstract void setEntryDate(LocalDate entryDate);
	public abstract void setExpectedCompletionDate( LocalDate expectedCompletionDate);
	public abstract void setId(Long id);
	public abstract void setPhase(IPhase phase);
	public abstract void setPhaseApprovals(Set<IPhaseApproval> phaseApprovals);
	public abstract void setWorkflow(IWorkflow workflow);
	public abstract void addStatusCompletion(IStatusCompletion statusCompletion);
	public abstract IStatusCompletion getCurrentStatusCompletion();
	public abstract List<IStatusCompletion> getStatusCompletions();
	public abstract void removeStatusCompletion(IStatusCompletion statusCompletion);
	public abstract void setCurrentStatusCompletion(IStatusCompletion currentStatusCompletion);
	public abstract void setStatusCompletions(List<IStatusCompletion> statusCompletions);
}