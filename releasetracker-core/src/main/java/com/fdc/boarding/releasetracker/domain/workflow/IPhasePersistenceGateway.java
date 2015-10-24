package com.fdc.boarding.releasetracker.domain.workflow;

import java.io.Serializable;
import java.util.List;

public interface IPhasePersistenceGateway extends Serializable {

	public abstract List<IPhase> findAllPhases();

}