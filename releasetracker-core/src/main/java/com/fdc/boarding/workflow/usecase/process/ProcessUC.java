package com.fdc.boarding.workflow.usecase.process;

import javax.inject.Inject;

import org.joda.time.Duration;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.workflow.domain.process.IProcessStatus;
import com.fdc.boarding.workflow.gateway.process.IProcessStatusPersistenceGateway;


public class ProcessUC implements IProcessUC {
	
	@Inject
	private IProcessStatusPersistenceGateway	gateway;
	
	@Override
	public void processBegin( ProcessStatusRequest request ) {
		IProcessStatus					entity;
		
		entity	= CDIContext.getInstance().getBean( IProcessStatus.class );
		entity.setCorrelationId( request.getCorrelationId() );
		entity.setProcessEntryDateTime( request.getUtcDateTimeAsDateTime() );
		entity.setProcess( request.getProcess() );
		entity.setStatus( request.getStatus() );
		gateway.add( entity );
	}

	@Override
	public void processStatus( ProcessStatusRequest request ) {
		IProcessStatus					last;
		IProcessStatus					entity;

		last	= gateway.find( request.getCorrelationId(), request.getProcess() );
		entity	= CDIContext.getInstance().getBean( IProcessStatus.class );
		if( last != null ){
			entity.setProcessEntryDateTime( last.getProcessEntryDateTime() );
		}
		entity.setCorrelationId( request.getCorrelationId() );
		entity.setProcess( request.getProcess() );
		entity.setStatus( request.getStatus() );
		gateway.add( entity );
	}

	@Override
	public void processComplete( ProcessStatusRequest request ) {
		IProcessStatus					last;
		IProcessStatus					entity;
		Duration						duration;
		
		last	= gateway.find( request.getCorrelationId(), request.getProcess() );
		entity	= CDIContext.getInstance().getBean( IProcessStatus.class );
		if( last != null ){
			duration = new Duration( last.getProcessEntryDateTime(), request.getUtcDateTimeAsDateTime() );
			entity.setProcessEntryDateTime( last.getProcessEntryDateTime() );
			entity.setProcessDuration( duration.getMillis() );
		}
		entity.setProcessCompleteDateTime( request.getUtcDateTimeAsDateTime() );
		entity.setCorrelationId( request.getCorrelationId() );
		entity.setProcess( request.getProcess() );
		entity.setStatus( request.getStatus() );
		gateway.add( entity );
	}
}
