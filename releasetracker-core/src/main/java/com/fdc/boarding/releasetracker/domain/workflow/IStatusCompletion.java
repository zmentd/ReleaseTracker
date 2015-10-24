package com.fdc.boarding.releasetracker.domain.workflow;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IStatusCompletion extends IAuditable, IEntity<Long> {
	public abstract LocalDate getCompletionDate();
	public abstract Integer getDaysInStatus();
	public abstract LocalDate getEntryDate();
	public abstract Long getId();
	public abstract IPhaseCompletion getPhaseCompletion();
	public abstract IStatus getStatus();
	public abstract void setCompletionDate(LocalDate completionDate);
	public abstract void setDaysInStatus(Integer daysInStatus);
	public abstract void setEntryDate(LocalDate entryDate);
	public abstract void setId(Long id);
	public abstract void setPhaseCompletion(IPhaseCompletion phaseCompletion);
	public abstract void setStatus(IStatus status);
}