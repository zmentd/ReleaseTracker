package com.fdc.boarding.workflow.domain.process;

import org.joda.time.DateTime;

public interface IProcessStatus {
	public abstract Long getId();
	public abstract String getCorrelationId();
	public abstract String getProcess();
	public abstract DateTime getProcessEntryDateTime();
	public abstract DateTime getProcessCompleteDateTime();
	public abstract Long getProcessDuration();
	public abstract String getStatus();
//	public abstract DateTime getStatusEntryDateTime();
//	public abstract Long getStatusDuration();
	public abstract void setId(Long id);
	public abstract void setCorrelationId(String correlationId);
	public abstract void setProcess(String process);
	public abstract void setProcessEntryDateTime(DateTime entryDateTime);
	public abstract void setProcessCompleteDateTime(DateTime completeDateTime);
	public abstract void setProcessDuration(Long processDuration);
	public abstract void setStatus(String status);
//	public abstract void setStatusEntryDateTime(DateTime statusDateTime);
//	public abstract void setStatusDuration(Long statusDuration);
//	public abstract void setStatusCompleteDateTime(DateTime statusCompleteDateTime);
//	public abstract DateTime getStatusCompleteDateTime();
}