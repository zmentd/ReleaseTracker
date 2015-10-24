package com.fdc.boarding.workflow.persistence.report;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.workflow.domain.process.IProcessStatus;

public class ProcessReportPersistenceGateway implements IProcessReportPersistenceGateway {

	@Inject
	private EntityManager				em;

	public ProcessReportPersistenceGateway(){
		super();
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<IProcessStatus> find(){
		List<IProcessStatus>		entity	= null;
		Query 						query;
		 
		try {
			query	= em.createQuery( "from ProcessStatusEntity entity " 
			);
			entity	= query.getResultList();
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
}
