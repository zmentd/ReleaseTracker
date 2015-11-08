package com.fdc.boarding.releasetracker.workflow;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class WorkflowProxy extends FDiNetServiceProxyBase implements WorkflowDef {

	public WorkflowProxy() throws FDiNetException {
		this("workflow", EncodingType.JSON, 1);
	}

	public WorkflowProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public WorkflowProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void findAllStatuses(java.lang.Void request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.StatusListResponse> handler) {
		invokeRR("findAllStatuses", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.StatusListResponse.class, false);
	}
	
	@Override
	public final void approve(com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler) {
		invokeRR("approve", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class, false);
	}
	
	@Override
	public final void findPhases(com.fdc.boarding.releasetracker.usecase.LikeRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse> handler) {
		invokeRR("findPhases", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse.class, false);
	}
	
	@Override
	public final void findAllPhases(java.lang.Void request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse> handler) {
		invokeRR("findAllPhases", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse.class, false);
	}
	
	@Override
	public final void progressToPhase(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseProgressionResponse> handler) {
		invokeRR("progressToPhase", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.PhaseProgressionResponse.class, false);
	}
	
	@Override
	public final void readComments(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsResponse> handler) {
		invokeRR("readComments", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsResponse.class, false);
	}
	
	@Override
	public final void reject(com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler) {
		invokeRR("reject", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class, false);
	}
	
	@Override
	public final void progressToStatus(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.StatusProgressionResponse> handler) {
		invokeRR("progressToStatus", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.StatusProgressionResponse.class, false);
	}
	
	@Override
	public final void determineNextApprovalType(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeResponse> handler) {
		invokeRR("determineNextApprovalType", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeResponse.class, false);
	}
	
	@Override
	public final void canProgressToPhase(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse> handler) {
		invokeRR("canProgressToPhase", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse.class, false);
	}
	
	@Override
	public final void approvalRequested(com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler) {
		invokeRR("approvalRequested", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class, false);
	}
	
	@Override
	public final void approvalsComplete(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest request, TypedResponseHandler<java.lang.Boolean> handler) {
		invokeRR("approvalsComplete", request, 10000, handler, java.lang.Boolean.class, false);
	}
	
	@Override
	public final void determineLastApprovalSent(com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse> handler) {
		invokeRR("determineLastApprovalSent", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse.class, false);
	}
	
	@Override
	public final void canProgressToStatus(com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse> handler) {
		invokeRR("canProgressToStatus", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse.class, false);
	}
	
	@Override
	public final void findAllPhaseApprovalTypes(java.lang.Void request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeListResponse> handler) {
		invokeRR("findAllPhaseApprovalTypes", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeListResponse.class, false);
	}
	
}
