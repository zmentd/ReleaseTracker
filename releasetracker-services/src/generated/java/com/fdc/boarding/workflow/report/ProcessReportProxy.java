package com.fdc.boarding.workflow.report;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class ProcessReportProxy extends FDiNetServiceProxyBase implements ProcessReportDef {

	public ProcessReportProxy() throws FDiNetException {
		this("processreport", EncodingType.JSON, 1);
	}

	public ProcessReportProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public ProcessReportProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void getReport(fdinet.core.VoidHolder request, TypedResponseHandler<com.fdc.boarding.workflow.usecase.report.ProcessReportModel> handler) {
		invokeRR("getReport", request, 10000, handler, com.fdc.boarding.workflow.usecase.report.ProcessReportModel.class, false);
	}
	
}
