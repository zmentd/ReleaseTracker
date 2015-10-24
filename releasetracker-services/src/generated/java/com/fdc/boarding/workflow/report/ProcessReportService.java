package com.fdc.boarding.workflow.report;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class ProcessReportService extends FDiNetServiceBase implements ProcessReportDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public ProcessReportService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public ProcessReportService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("getReport",new ServiceApiHandler<fdinet.core.VoidHolder>("getReport", fdinet.core.VoidHolder.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				getReport((fdinet.core.VoidHolder) request, (TypedResponseHandler<com.fdc.boarding.workflow.usecase.report.ProcessReportModel>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
