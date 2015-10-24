package com.fdc.boarding.releasetracker.persistence.workflow;

import java.util.List;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusPersistenceGateway;

public class StatusPersistenceGateway extends GenericDao<StatusEntity, Long> implements IStatusPersistenceGateway{

	public StatusPersistenceGateway() {
		super( StatusEntity.class );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IStatus> findAllStatuses() {
		List<? extends IStatus>		list;
		
		list	= findAll();
		
		return ( List<IStatus> )list;
	}
}