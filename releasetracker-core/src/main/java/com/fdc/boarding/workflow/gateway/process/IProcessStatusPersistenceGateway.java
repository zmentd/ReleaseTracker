package com.fdc.boarding.workflow.gateway.process;

import com.fdc.boarding.workflow.domain.process.IProcessStatus;


public interface IProcessStatusPersistenceGateway {
	public abstract IProcessStatus find(String correlationId, String process);
	public abstract IProcessStatus find(String correlationId, String process, String status );
	public abstract void add(IProcessStatus entity);
	public abstract void update(IProcessStatus entity);
	public abstract void persist(IProcessStatus entity);
}