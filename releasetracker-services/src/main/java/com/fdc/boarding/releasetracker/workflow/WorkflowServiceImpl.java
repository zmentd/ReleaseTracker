package com.fdc.boarding.releasetracker.workflow;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.annotation.Register;
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
import com.fdc.boarding.releasetracker.usecase.workflow.WorkflowUC;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;

@Register
public class WorkflowServiceImpl extends WorkflowService {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public WorkflowServiceImpl(
	) 
	{
		super( "Workflow", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void approvalRequested(ApprovalRequest request, TypedResponseHandler<ApprovalResponse> handler) {
		WorkflowUC						usecase;
		ApprovalResponse				response;
		
		logger.debug( "Entering approvalRequested." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.approvalRequested( request );
		handler.handleResponse( response );
		logger.debug( "Exiting approvalRequested." );
	}

	@Override
	public void approvalsComplete(WorkflowRequest request, TypedResponseHandler<Boolean> handler) {
		WorkflowUC						usecase;
		Boolean							response;
		
		logger.debug( "Entering approvalsComplete." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.approvalsComplete( request );
		handler.handleResponse( response );
		logger.debug( "Exiting approvalsComplete." );
	}

	@Override
	public void approve(ApprovalRequest request, TypedResponseHandler<ApprovalResponse> handler) {
		WorkflowUC					usecase;
		ApprovalResponse			response;
		
		logger.debug( "Entering approve." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.approve( request );
		handler.handleResponse( response );
		logger.debug( "Exiting approve." );
	}

	@Override
	public void canProgressToPhase(ProgressionRequest request, TypedResponseHandler<ProgressionCheckResponse> handler) {
		WorkflowUC						usecase;
		ProgressionCheckResponse		response;
		
		logger.debug( "Entering canProgressToPhase." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.canProgressToPhase( request );
		handler.handleResponse( response );
		logger.debug( "Exiting canProgressToPhase." );
	}

	@Override
	public void canProgressToStatus(ProgressionRequest request, TypedResponseHandler<ProgressionCheckResponse> handler) {
		WorkflowUC						usecase;
		ProgressionCheckResponse		response;
		
		logger.debug( "Entering canProgressToStatus." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.canProgressToStatus( request );
		handler.handleResponse( response );
		logger.debug( "Exiting canProgressToStatus." );
	}

	@Override
	public void determineLastApprovalSent(WorkflowRequest request, TypedResponseHandler<ApprovalResponse> handler) {
		WorkflowUC						usecase;
		ApprovalResponse				response;
		
		logger.debug( "Entering determineLastApprovalSent." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.determineLastApprovalSent( request );
		handler.handleResponse( response );
		logger.debug( "Exiting determineLastApprovalSent." );
	}

	@Override
	public void determineNextApprovalType(WorkflowRequest request, TypedResponseHandler<PhaseApprovalTypeResponse> handler) {
		WorkflowUC						usecase;
		PhaseApprovalTypeResponse		response;
		
		logger.debug( "Entering determineNextApprovalType." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.determineNextApprovalType( request );
		handler.handleResponse( response );
		logger.debug( "Exiting determineNextApprovalType." );
	}

	@Override
	public void findAllPhaseApprovalTypes(Void request, TypedResponseHandler<PhaseApprovalTypeListResponse> handler) {
		WorkflowUC						usecase;
		PhaseApprovalTypeListResponse	response;
		
		logger.debug( "Entering findAllPhaseApprovalTypes." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.findAllPhaseApprovalTypes();
		handler.handleResponse( response );
		logger.debug( "Exiting findAllPhaseApprovalTypes." );
	}

	@Override
	public void findAllPhases(Void request, TypedResponseHandler<PhaseListResponse> handler) {
		WorkflowUC						usecase;
		PhaseListResponse				response;
		
		logger.debug( "Entering findAllPhases." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.findAllPhases();
		handler.handleResponse( response );
		logger.debug( "Exiting findAllPhases." );
	}

	@Override
	public void findAllStatuses(Void request, TypedResponseHandler<StatusListResponse> handler) {
		WorkflowUC						usecase;
		StatusListResponse				response;
		
		logger.debug( "Entering findAllStatuses." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.findAllStatuses();
		handler.handleResponse( response );
		logger.debug( "Exiting findAllStatuses." );
	}

	@Override
	public void findPhases(LikeRequest request, TypedResponseHandler<PhaseListResponse> handler) {
		WorkflowUC						usecase;
		PhaseListResponse				response;
		
		logger.debug( "Entering findPhases." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.findPhases( request );
		handler.handleResponse( response );
		logger.debug( "Exiting findPhases." );
	}

	@Override
	public void progressToPhase(ProgressionRequest request, TypedResponseHandler<PhaseProgressionResponse> handler) {
		WorkflowUC						usecase;
		PhaseProgressionResponse		response;
		
		logger.debug( "Entering progressToPhase." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.progressToPhase( request );
		handler.handleResponse( response );
		logger.debug( "Exiting progressToPhase." );
	}

	@Override
	public void progressToStatus(ProgressionRequest request, TypedResponseHandler<StatusProgressionResponse> handler) {
		WorkflowUC						usecase;
		StatusProgressionResponse		response;
		
		logger.debug( "Entering progressToStatus." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.progressToStatus( request );
		handler.handleResponse( response );
		logger.debug( "Exiting progressToStatus." );
	}

	@Override
	public void readComments(WorkflowCommentsRequest request, TypedResponseHandler<WorkflowCommentsResponse> handler) {
		WorkflowUC						usecase;
		WorkflowCommentsResponse		response;
		
		logger.debug( "Entering readComments." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.readComments( request );
		handler.handleResponse( response );
		logger.debug( "Exiting readComments." );
	}

	@Override
	public void reject(ApprovalRequest request, TypedResponseHandler<ApprovalResponse> handler) {
		WorkflowUC						usecase;
		ApprovalResponse				response;
		
		logger.debug( "Entering reject." );
		usecase		= CDIContext.getInstance().getBean( WorkflowUC.class );
		response	= usecase.reject( request );
		handler.handleResponse( response );
		logger.debug( "Exiting reject." );
	}

}
