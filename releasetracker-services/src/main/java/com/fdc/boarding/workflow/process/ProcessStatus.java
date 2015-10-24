package com.fdc.boarding.workflow.process;

import com.fdc.boarding.workflow.usecase.process.ProcessStatusRequest;

import fdinet.core.StringHolder;
import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="processstatus" )
@FDiNetService( version = 1 )
public interface ProcessStatus {
    @FDiNetServiceAPI_RR( responseType = StringHolder.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/processBegin")
    @Description( "Report the beginning of a process." )
	public void processBegin( ProcessStatusRequest request, TypedResponseHandler<StringHolder> handler );
	
    @FDiNetServiceAPI_RR( responseType = StringHolder.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/processStatus")
    @Description( "Report the status of a process." )
	public void processStatus( ProcessStatusRequest request, TypedResponseHandler<StringHolder> handler );
	
    @FDiNetServiceAPI_RR( responseType = StringHolder.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/processComplete")
    @Description( "Report the completion of a process." )
	public void processComplete( ProcessStatusRequest request, TypedResponseHandler<StringHolder> handler );
}
