package com.fdc.boarding.releasetracker.gateway.workflow;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.workflow.IPhase;

public interface IPhasePersistenceGateway extends Serializable {

	public abstract List<IPhase> findAllPhases();

}