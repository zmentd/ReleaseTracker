package com.fdc.boarding.releasetracker.persistence.workflow;

import java.util.List;

import javax.persistence.Query;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.common.IComment;

public class WorkflowPersistenceGateway extends GenericDao<AbstractWorkflowEntity, Long> implements IWorkflowPersistenceGateway {
	private static final long 			serialVersionUID = 1L;

	public WorkflowPersistenceGateway() {
		super( AbstractWorkflowEntity.class );
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.workflow.IWorkflowPersistenceGateway#readComments(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<IComment> readComments( Long workflowId ){
		Query 							query;
		String							jql;
		List<IComment>					list;

		jql		= "select entity.comments from AbstractWorkflowEntity entity ";
		jql		= jql + "where entity.id = " + workflowId;
		query	= entityManager.createQuery( jql );
		list 	= query.getResultList();

		return list;
	}

}
