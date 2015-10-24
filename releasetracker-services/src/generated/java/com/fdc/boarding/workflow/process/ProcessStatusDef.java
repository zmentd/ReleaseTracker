package com.fdc.boarding.workflow.process;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="processstatus", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface ProcessStatusDef {

    @FDiNetWebInfo(path="/processBegin", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Report the beginning of a process.")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=fdinet.core.StringHolder.class)
	void processBegin(com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest request, TypedResponseHandler<fdinet.core.StringHolder> handler);

    @FDiNetWebInfo(path="/processComplete", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Report the completion of a process.")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=fdinet.core.StringHolder.class)
	void processComplete(com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest request, TypedResponseHandler<fdinet.core.StringHolder> handler);

    @FDiNetWebInfo(path="/processStatus", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Report the status of a process.")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=fdinet.core.StringHolder.class)
	void processStatus(com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest request, TypedResponseHandler<fdinet.core.StringHolder> handler);

}
