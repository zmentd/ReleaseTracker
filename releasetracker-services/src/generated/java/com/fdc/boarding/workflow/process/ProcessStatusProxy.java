package com.fdc.boarding.workflow.process;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class ProcessStatusProxy extends FDiNetServiceProxyBase implements ProcessStatusDef {

	public ProcessStatusProxy() throws FDiNetException {
		this("processstatus", EncodingType.JSON, 1);
	}

	public ProcessStatusProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public ProcessStatusProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void processBegin(com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest request, TypedResponseHandler<fdinet.core.StringHolder> handler) {
		invokeRR("processBegin", request, 10000, handler, fdinet.core.StringHolder.class, false);
	}
	
	@Override
	public final void processComplete(com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest request, TypedResponseHandler<fdinet.core.StringHolder> handler) {
		invokeRR("processComplete", request, 10000, handler, fdinet.core.StringHolder.class, false);
	}
	
	@Override
	public final void processStatus(com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest request, TypedResponseHandler<fdinet.core.StringHolder> handler) {
		invokeRR("processStatus", request, 10000, handler, fdinet.core.StringHolder.class, false);
	}
	
}
