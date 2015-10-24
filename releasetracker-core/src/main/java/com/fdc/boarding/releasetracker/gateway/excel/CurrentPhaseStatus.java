package com.fdc.boarding.releasetracker.gateway.excel;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;

public class CurrentPhaseStatus {
	private IPhase						phase;
	private IStatus						status;
	private LocalDate					phaseEntry;
	private LocalDate					statusEntry;
	
	public CurrentPhaseStatus(){
		super();
	}

	public IPhase getPhase() {
		return phase;
	}

	public IStatus getStatus() {
		return status;
	}

	public LocalDate getPhaseEntry() {
		return phaseEntry;
	}

	public LocalDate getStatusEntry() {
		return statusEntry;
	}

	public void setPhase(IPhase phase) {
		this.phase = phase;
	}

	public void setStatus(IStatus status) {
		this.status = status;
	}

	public void setPhaseEntry(LocalDate phaseEntry) {
		this.phaseEntry = phaseEntry;
	}

	public void setStatusEntry(LocalDate statusEntry) {
		this.statusEntry = statusEntry;
	}
}
