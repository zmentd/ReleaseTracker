package com.fdc.boarding.releasetracker.domain.release;

import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

public interface IReleasePersistenceGateway {
	public List<Integer> determineReleaseYears();
	public List<IReleaseEntry> findReleaseCalendarForYear(int year);
	public abstract List<IReleaseEntry> findAllReleases();
	public abstract MilestoneByRom findMilestoneByTargetDate(LocalDate date, Rom rom, PhaseType phaseType);
}