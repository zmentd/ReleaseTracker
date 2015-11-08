package com.fdc.boarding.releasetracker.persistence.workflow;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.common.IComment;

public interface IWorkflowPersistenceGateway extends Serializable{

	public abstract List<IComment> readComments(Long workflowId);

}