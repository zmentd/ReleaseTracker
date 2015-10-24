package com.fdc.boarding.releasetracker.persistence.workflow;

import java.util.List;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhasePersistenceGateway;

public class PhasePersistenceGateway extends GenericDao<PhaseEntity, Long> implements IPhasePersistenceGateway{
	private static final long 			serialVersionUID = 1L;

	public PhasePersistenceGateway() {
		super( PhaseEntity.class );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IPhase> findAllPhases() {
		List<? extends IPhase>		list;
		
		list	= findAll();
		
		return ( List<IPhase> )list;
	}
}