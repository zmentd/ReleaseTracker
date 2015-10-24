package com.fdc.boarding.workflow.usecase.process;



public interface IProcessUC {
	public abstract void processBegin(ProcessStatusRequest request);
	public abstract void processStatus(ProcessStatusRequest request);
	public abstract void processComplete(ProcessStatusRequest request);
}