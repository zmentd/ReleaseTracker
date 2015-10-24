package com.fdc.boarding.workflow.report;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.annotation.Register;
import com.fdc.boarding.workflow.usecase.report.ProcessReportModel;
import com.fdc.boarding.workflow.usecase.report.ProcessReportUC;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;
import fdinet.core.VoidHolder;

@Register
public class ProcessReportServiceImpl extends ProcessReportService {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public ProcessReportServiceImpl(
	) 
	{
		super( "ProcessReport", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void getReport(VoidHolder request, TypedResponseHandler<ProcessReportModel> handler) 
	{
		ProcessReportUC						usecase;
		ProcessReportModel					model;
		
		logger.debug( "Entering getModel." );
		usecase		= CDIContext.getInstance().getBean( ProcessReportUC.class );
		model		= usecase.createReport();
		
		handler.handleResponse( model );
		logger.debug( "Exiting getModel." );
		
	}

}
