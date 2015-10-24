package com.fdc.boarding.workflow.process;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.annotation.Register;
import com.fdc.boarding.workflow.usecase.process.IProcessUC;
import com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest;

import fdinet.core.Processor;
import fdinet.core.StringHolder;
import fdinet.core.TypedResponseHandler;

@Register
public class ProcessStatusServiceImpl extends ProcessStatusService{
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public ProcessStatusServiceImpl(
	) 
	{
		super( "ProcessStatus", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void processBegin(ProcessStatusRequest request, TypedResponseHandler<StringHolder> handler) {
		IProcessUC						usecase;
		
		logger.debug( "Entering processBegin." );
		usecase			= CDIContext.getInstance().getBean( IProcessUC.class );
		usecase.processBegin( request );
		
		handler.handleResponse( new StringHolder( "Process entered at " + new DateTime().toString( "HH:mm:ss MM/dd/yyyy" ) ) );
		logger.debug( "Exiting processBegin." );
	}

	@Override
	public void processStatus(ProcessStatusRequest request, TypedResponseHandler<StringHolder> handler) {
		IProcessUC						usecase;
		
		logger.debug( "Entering processStatus." );
		usecase			= CDIContext.getInstance().getBean( IProcessUC.class );
		usecase.processStatus( request );
		
		handler.handleResponse( new StringHolder( "Status updated at " + new DateTime().toString( "HH:mm:ss MM/dd/yyyy" )  ) );
		logger.debug( "Exiting processStatus." );
	}

	@Override
	public void processComplete(ProcessStatusRequest request, TypedResponseHandler<StringHolder> handler) {
		IProcessUC						usecase;
		
		logger.debug( "Entering processComplete." );
		usecase			= CDIContext.getInstance().getBean( IProcessUC.class );
		usecase.processComplete( request );
		
		handler.handleResponse( new StringHolder( "Process completed at " + new DateTime().toString( "HH:mm:ss MM/dd/yyyy" ) ) );
		logger.debug( "Exiting processComplete." );
	}
}
