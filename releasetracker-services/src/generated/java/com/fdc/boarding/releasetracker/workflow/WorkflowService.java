package com.fdc.boarding.releasetracker.workflow;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class WorkflowService extends FDiNetServiceBase implements WorkflowDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public WorkflowService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public WorkflowService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("findAllStatuses",new ServiceApiHandler<java.lang.Void>("findAllStatuses", java.lang.Void.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				findAllStatuses((java.lang.Void) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.StatusListResponse>)r);
			}
		});

		handlers.put("approve",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest>("approve", com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				approve((com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse>)r);
			}
		});

		handlers.put("findPhases",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.LikeRequest>("findPhases", com.fdc.boarding.releasetracker.usecase.LikeRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				findPhases((com.fdc.boarding.releasetracker.usecase.LikeRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse>)r);
			}
		});

		handlers.put("findAllPhases",new ServiceApiHandler<java.lang.Void>("findAllPhases", java.lang.Void.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				findAllPhases((java.lang.Void) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseListResponse>)r);
			}
		});

		handlers.put("progressToPhase",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest>("progressToPhase", com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				progressToPhase((com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseProgressionResponse>)r);
			}
		});

		handlers.put("readComments",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsRequest>("readComments", com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				readComments((com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowCommentsResponse>)r);
			}
		});

		handlers.put("reject",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest>("reject", com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				reject((com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse>)r);
			}
		});

		handlers.put("progressToStatus",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest>("progressToStatus", com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				progressToStatus((com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.StatusProgressionResponse>)r);
			}
		});

		handlers.put("determineNextApprovalType",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest>("determineNextApprovalType", com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				determineNextApprovalType((com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeResponse>)r);
			}
		});

		handlers.put("canProgressToPhase",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest>("canProgressToPhase", com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				canProgressToPhase((com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse>)r);
			}
		});

		handlers.put("approvalRequested",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest>("approvalRequested", com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				approvalRequested((com.fdc.boarding.releasetracker.usecase.workflow.ApprovalRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse>)r);
			}
		});

		handlers.put("approvalsComplete",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest>("approvalsComplete", com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				approvalsComplete((com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest) request, (TypedResponseHandler<java.lang.Boolean>)r);
			}
		});

		handlers.put("determineLastApprovalSent",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest>("determineLastApprovalSent", com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				determineLastApprovalSent((com.fdc.boarding.releasetracker.usecase.workflow.WorkflowRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ApprovalResponse>)r);
			}
		});

		handlers.put("canProgressToStatus",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest>("canProgressToStatus", com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				canProgressToStatus((com.fdc.boarding.releasetracker.usecase.workflow.ProgressionRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.ProgressionCheckResponse>)r);
			}
		});

		handlers.put("findAllPhaseApprovalTypes",new ServiceApiHandler<java.lang.Void>("findAllPhaseApprovalTypes", java.lang.Void.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				findAllPhaseApprovalTypes((java.lang.Void) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.workflow.PhaseApprovalTypeListResponse>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
