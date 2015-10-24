package com.fdc.boarding.workflow.report;

import com.fdc.boarding.workflow.usecase.report.ProcessReportModel;

import fdinet.core.TypedResponseHandler;
import fdinet.core.VoidHolder;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path = "processreport" )
@FDiNetService( version = 1 )
public interface ProcessReport {
	
    @FDiNetServiceAPI_RR( responseType = ProcessReportModel.class, timeoutMillis=10000 )
    @FDiNetWebInfo( path = "/getReport" )
    @Description( "Report the status of a process." )
	public void getReport( VoidHolder request, TypedResponseHandler<ProcessReportModel> handler );

}
