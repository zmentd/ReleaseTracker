package com.fdc.boarding.releasetracker.gateway.workflow;

import java.util.List;

import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;

public interface IPhaseApprovalTypePersistenceGateway {

	public List<IPhaseApprovalType> findNonApprovedTypes(IWorkflow workflow);

	public abstract IPhaseApproval findLastApprovalSent(IWorkflow workflow);

}