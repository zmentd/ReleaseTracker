package com.fdc.boarding.releasetracker.domain.release;

import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IReleaseEntry extends IAuditable, IEntity<Long> {

	public void addMilestone(IMilestone milestone);

	public Long getId();

	public abstract LocalDate getLastCatRevDate();

	public abstract LocalDate getLockDownDate();

	public List<IMilestone> getMilestones();

	public Boolean getMinor();

	public LocalDate getReleaseDate();

	public void removeMilestone(IMilestone milestone);

	public void setId(Long id);

	public abstract void setLastCatRevDate(LocalDate lastCatRevDate);

	public abstract void setLockDownDate(LocalDate lockDownDate);

	public void setMilestones(List<IMilestone> milestones);

	public void setMinor(Boolean minor);

	public void setReleaseDate(LocalDate releaseDate);

}