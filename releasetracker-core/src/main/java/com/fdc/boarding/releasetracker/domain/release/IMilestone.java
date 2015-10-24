package com.fdc.boarding.releasetracker.domain.release;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

public interface IMilestone extends IAuditable, IEntity<Long> {

	public String getDescription();
	public abstract LocalDate getDueDate();
	public Long getId();
	public LocalDate getLargeDueDate();
	public LocalDate getMediumDueDate();
	public String getName();
	public IReleaseEntry getReleaseEntry();
	public LocalDate getSmallDueDate();
	public void setDescription(String description);
	public abstract void setDueDate(LocalDate dueDate);
	public void setId(Long id);
	public void setLargeDueDate(LocalDate largeDueDate);
	public void setMediumDueDate(LocalDate mediumDueDate);
	public void setName(String name);
	public void setReleaseEntry(IReleaseEntry releaseEntry);
	public void setSmallDueDate(LocalDate smallDueDate);
	public abstract void setPhaseType(PhaseType phaseType);
	public abstract PhaseType getPhaseType();
}