package com.fdc.boarding.workflow.report;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="processreport", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface ProcessReportDef {

    @FDiNetWebInfo(path="/getReport", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Report the status of a process.")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.workflow.usecase.report.ProcessReportModel.class)
	void getReport(fdinet.core.VoidHolder request, TypedResponseHandler<com.fdc.boarding.workflow.usecase.report.ProcessReportModel> handler);

}
