package com.fdc.boarding.releasetracker.workflow;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="workflow", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface WorkflowDef {

    @FDiNetWebInfo(path="/findAllStatuses", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.StatusListResponse.class)
	void findAllStatuses(java.lang.Void request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.StatusListResponse> handler);

    @FDiNetWebInfo(path="/approve", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class)
	void approve(com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler);

    @FDiNetWebInfo(path="/findPhases", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse.class)
	void findPhases(com.fdc.boarding.releasetracker.usecase.LikeRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse> handler);

    @FDiNetWebInfo(path="/findAllPhases", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse.class)
	void findAllPhases(java.lang.Void request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse> handler);

    @FDiNetWebInfo(path="/progressToPhase", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.PhaseProgressionResponse.class)
	void progressToPhase(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseProgressionResponse> handler);

    @FDiNetWebInfo(path="/readComments", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsResponse.class)
	void readComments(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsResponse> handler);

    @FDiNetWebInfo(path="/reject", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class)
	void reject(com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler);

    @FDiNetWebInfo(path="/progressToStatus", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.StatusProgressionResponse.class)
	void progressToStatus(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.StatusProgressionResponse> handler);

    @FDiNetWebInfo(path="/determineNextApprovalType", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeResponse.class)
	void determineNextApprovalType(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeResponse> handler);

    @FDiNetWebInfo(path="/canProgressToPhase", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse.class)
	void canProgressToPhase(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse> handler);

    @FDiNetWebInfo(path="/approvalRequested", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class)
	void approvalRequested(com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler);

    @FDiNetWebInfo(path="/approvalsComplete", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=java.lang.Boolean.class)
	void approvalsComplete(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest request, TypedResponseHandler<java.lang.Boolean> handler);

    @FDiNetWebInfo(path="/determineLastApprovalSent", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class)
	void determineLastApprovalSent(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler);

    @FDiNetWebInfo(path="/canProgressToStatus", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse.class)
	void canProgressToStatus(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse> handler);

    @FDiNetWebInfo(path="/findAllPhaseApprovalTypes", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeListResponse.class)
	void findAllPhaseApprovalTypes(java.lang.Void request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeListResponse> handler);

}
