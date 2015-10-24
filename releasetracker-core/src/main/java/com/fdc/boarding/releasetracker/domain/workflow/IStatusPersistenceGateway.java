package com.fdc.boarding.releasetracker.domain.workflow;

import java.io.Serializable;
import java.util.List;

public interface IStatusPersistenceGateway extends Serializable {

	public abstract List<IStatus> findAllStatuses();

}