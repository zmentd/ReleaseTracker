package com.fdc.boarding.workflow.usecase.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProcessModel implements Serializable{
	private static final long 			serialVersionUID = 1L;

	private String						process;
	private long						currentCount;
	private long						totalCount;
	private long						averageDuration;
	private long						minDuration;
	private long						maxDuration;
	private List<ProcessStatusModel>	statusList;
	
	public ProcessModel(){
		super();
		statusList	= new ArrayList<>();
	}

	public void addStatus( ProcessStatusModel status ){
		statusList.add( status );
	}
	public long getAverageDuration() {
		return averageDuration;
	}

	public long getCurrentCount() {
		return currentCount;
	}

	public long getMaxDuration() {
		return maxDuration;
	}

	public long getMinDuration() {
		return minDuration;
	}

	public String getProcess() {
		return process;
	}

	public List<ProcessStatusModel> getStatusList() {
		return statusList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setAverageDuration(long averageDuration) {
		this.averageDuration = averageDuration;
	}

	public void setCurrentCount(long currentCount) {
		this.currentCount = currentCount;
	}

	public void setMaxDuration(long maxDuration) {
		this.maxDuration = maxDuration;
	}

	public void setMinDuration(long minDuration) {
		this.minDuration = minDuration;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void setStatusList(List<ProcessStatusModel> statusList) {
		this.statusList = statusList;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
