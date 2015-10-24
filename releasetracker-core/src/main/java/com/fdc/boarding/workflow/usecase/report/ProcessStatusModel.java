package com.fdc.boarding.workflow.usecase.report;

import java.io.Serializable;

public class ProcessStatusModel implements Serializable{
	private static final long 			serialVersionUID = 1L;
	
	private String						process;
	private String						status;
	private long						currentCount;
	private long						totalCount;
	private String						averageDuration;
	private String						minDuration;
	private String						maxDuration;

	public ProcessStatusModel(){
		super();
	}

	public String getAverageDuration() {
		return averageDuration;
	}

	public long getCurrentCount() {
		return currentCount;
	}

	public String getMaxDuration() {
		return maxDuration;
	}

	public String getMinDuration() {
		return minDuration;
	}

	public String getProcess() {
		return process;
	}

	public String getStatus() {
		return status;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setAverageDuration(String averageDuration) {
		this.averageDuration = averageDuration;
	}

	public void setCurrentCount(long currentCount) {
		this.currentCount = currentCount;
	}

	public void setMaxDuration(String maxDuration) {
		this.maxDuration = maxDuration;
	}

	public void setMinDuration(String minDuration) {
		this.minDuration = minDuration;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
