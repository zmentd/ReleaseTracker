package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.List;

public interface IPhaseApprovalTypePersistenceGateway {

	public List<IPhaseApprovalType> findNonApprovedTypes(IWorkflow workflow);

	public abstract IPhaseApproval findLastApprovalSent(IWorkflow workflow);

}