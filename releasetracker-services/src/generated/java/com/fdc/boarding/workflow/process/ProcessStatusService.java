package com.fdc.boarding.workflow.process;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class ProcessStatusService extends FDiNetServiceBase implements ProcessStatusDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public ProcessStatusService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public ProcessStatusService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("processBegin",new ServiceApiHandler<com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest>("processBegin", com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				processBegin((com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest) request, (TypedResponseHandler<fdinet.core.StringHolder>)r);
			}
		});

		handlers.put("processComplete",new ServiceApiHandler<com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest>("processComplete", com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				processComplete((com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest) request, (TypedResponseHandler<fdinet.core.StringHolder>)r);
			}
		});

		handlers.put("processStatus",new ServiceApiHandler<com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest>("processStatus", com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				processStatus((com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest) request, (TypedResponseHandler<fdinet.core.StringHolder>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
