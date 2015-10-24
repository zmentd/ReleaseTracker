package com.fdc.boarding.workflow.persistence.process;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.workflow.domain.process.IProcessStatus;
import com.fdc.boarding.workflow.gateway.process.IProcessStatusPersistenceGateway;

public class ProcessStatusPersistenceGateway implements IProcessStatusPersistenceGateway {

	@Inject
	private EntityManager				em;

	public ProcessStatusPersistenceGateway(){
		super();
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public IProcessStatus find( String correlationId, String process ){
		IProcessStatus					entity	= null;
		Query							query;
		List<IProcessStatus>			list;
		
		try {
			query	= em.createQuery( "from ProcessStatusEntity entity where entity.correlationId = '" 
								    + correlationId 
								    + "' and entity.process = '" 
								    + process 
								    + "'" 
									+ " and entity.processCompleteDateTime is null "
									+ "order by id desc " 
			);
			list	= query.getResultList();
			if( list.size() > 0 ){
				entity	= ( IProcessStatus )list.get( 0 );
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@Override
	@Transactional
	public IProcessStatus find( String correlationId, String process, String status ){
		IProcessStatus					entity	= null;
		Query							query;
		
		try {
			query	= em.createQuery( "from ProcessStatusEntity entity where entity.correlationId = '" 
									+ correlationId 
									+ "' and entity.process = '" 
									+ process + "'" 
									+ "' and entity.statusCompleteDateTime is null" 
			);
			entity	= ( IProcessStatus )query.getSingleResult();
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@Override
	@Transactional
	public void add( IProcessStatus entity ){
		
		em.persist( entity );
	}
	
	@Override
	@Transactional
	public void update( IProcessStatus entity ) {
		IProcessStatus 					merged;

		merged = entity;
		if( !em.contains( merged ) )
		{
			merged = em.merge( merged );
		}
		persist( merged );
	}

	@Override
	@Transactional
	public void persist( IProcessStatus entity ) {
		em.persist( entity );
	}
}
