package com.fdc.boarding.releasetracker.domain.release;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IMilestone extends IAuditable, IEntity<Long> {

	public String getDescription();
	public abstract LocalDate getDueDate();
	public Long getId();
	public abstract int getIndex();
	public LocalDate getLargeDueDate();
	public LocalDate getMediumDueDate();
	public String getName();
	public abstract MilestoneType getMilestoneType();
	public IReleaseEntry getReleaseEntry();
	public LocalDate getSmallDueDate();
	public void setDescription(String description);
	public abstract void setDueDate(LocalDate dueDate);
	public void setId(Long id);
	public abstract void setIndex(int index);
	public void setLargeDueDate(LocalDate largeDueDate);
	public void setMediumDueDate(LocalDate mediumDueDate);
	public void setName(String name);
	public abstract void setMilestoneType(MilestoneType milestoneType);
	public void setReleaseEntry(IReleaseEntry releaseEntry);
	public void setSmallDueDate(LocalDate smallDueDate);
}