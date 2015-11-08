package com.fdc.boarding.releasetracker.workflow;

import com.fdc.boarding.releasetracker.usecase.LikeRequest;
import com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest;
import com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeListResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.PhaseProgressionResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest;
import com.fdc.boarding.releasetracker.usecase.workflow.StatusListResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.StatusProgressionResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsRequest;
import com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsResponse;
import com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="workflow" )
@FDiNetService( version = 1 )
public interface Workflow {
	
    @FDiNetServiceAPI_RR( responseType = ApprovalResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/approvalRequested")
    @Description( "" )
	public void approvalRequested( ApprovalRequest request, TypedResponseHandler<ApprovalResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = Boolean.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/approvalsComplete")
    @Description( "" )
	public void approvalsComplete( WorkflowRequest request, TypedResponseHandler<Boolean> handler );
	
    @FDiNetServiceAPI_RR( responseType = ApprovalResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/approve")
    @Description( "" )
	public void approve( ApprovalRequest request, TypedResponseHandler<ApprovalResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = ProgressionCheckResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/canProgressToPhase")
    @Description( "" )
	public void canProgressToPhase( ProgressionRequest request, TypedResponseHandler<ProgressionCheckResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = ProgressionCheckResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/canProgressToStatus")
    @Description( "" )
	public void canProgressToStatus( ProgressionRequest request, TypedResponseHandler<ProgressionCheckResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = ApprovalResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/determineLastApprovalSent")
    @Description( "" )
	public void determineLastApprovalSent( WorkflowRequest request, TypedResponseHandler<ApprovalResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = PhaseApprovalTypeResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/determineNextApprovalType")
    @Description( "" )
	public void determineNextApprovalType( WorkflowRequest request, TypedResponseHandler<PhaseApprovalTypeResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = PhaseApprovalTypeListResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findAllPhaseApprovalTypes")
    @Description( "" )
	public void findAllPhaseApprovalTypes( Void request, TypedResponseHandler<PhaseApprovalTypeListResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = PhaseListResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findAllPhases")
    @Description( "" )
	public void findAllPhases( Void request, TypedResponseHandler<PhaseListResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = StatusListResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findAllStatuses")
    @Description( "" )
	public void findAllStatuses( Void request, TypedResponseHandler<StatusListResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = PhaseListResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findPhases")
    @Description( "" )
	public void findPhases( LikeRequest request, TypedResponseHandler<PhaseListResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = PhaseProgressionResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/progressToPhase")
    @Description( "" )
	public void progressToPhase( ProgressionRequest request, TypedResponseHandler<PhaseProgressionResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = StatusProgressionResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/progressToStatus")
    @Description( "" )
	public void progressToStatus( ProgressionRequest request, TypedResponseHandler<StatusProgressionResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = WorkflowCommentsResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/readComments")
    @Description( "" )
	public void readComments( WorkflowCommentsRequest request, TypedResponseHandler<WorkflowCommentsResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = ApprovalResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/reject")
    @Description( "" )
	public void reject( ApprovalRequest request, TypedResponseHandler<ApprovalResponse> handler );

}
