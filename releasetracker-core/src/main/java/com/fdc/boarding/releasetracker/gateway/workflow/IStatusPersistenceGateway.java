package com.fdc.boarding.releasetracker.gateway.workflow;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.workflow.IStatus;

public interface IStatusPersistenceGateway extends Serializable {

	public abstract List<IStatus> findAllStatuses();

}