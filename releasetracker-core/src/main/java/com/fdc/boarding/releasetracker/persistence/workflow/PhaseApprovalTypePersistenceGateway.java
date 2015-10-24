package com.fdc.boarding.releasetracker.persistence.workflow;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalTypePersistenceGateway;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;

public class PhaseApprovalTypePersistenceGateway extends GenericDao<PhaseApprovalTypeEntity, Long> implements IPhaseApprovalTypePersistenceGateway {

	public PhaseApprovalTypePersistenceGateway(){
		super(PhaseApprovalTypeEntity.class);
	}

	@Override
	
	@SuppressWarnings("unchecked")
	public List<IPhaseApprovalType> findNonApprovedTypes( IWorkflow workflow ){
		String							jql;
		Query							query;
		List<IPhaseApprovalType>		results;
		
		Hibernate.initialize( workflow.getCurrentPhaseCompletion().getPhase() );
		jql		= "select pat from PhaseApprovalTypeEntity pat ";
		jql		= jql + "where pat.id not in ( "
							+ "select pa.phaseApprovalType.id from PhaseApprovalEntity pa "
							+ "where pa.phaseCompletion.workflow.id = " + workflow.getId() 
							+ " and pa.phaseCompletion.phase.id = " + workflow.getCurrentPhaseCompletion().getPhase().getId() 
							+ " and ( pa.approvalComplete is not null "
							+ " or pa.rejectComplete is not null ) "
							+ ") ";
		jql 	= jql + " and pat.phase.id = " + workflow.getCurrentPhaseCompletion().getPhase().getId() + " ";
		jql 	= jql + "order by pat.sequence asc ";
		
		query 	= entityManager.createQuery( jql );		
		results	= query.getResultList();
	
		return results;
	}


	@Override
	public IPhaseApproval findLastApprovalSent( IWorkflow workflow ){
		String							jql;
		Query							query;
		IPhaseApproval					results		= null;
		
		
		jql		= "select pa from PhaseApprovalEntity pa ";
		jql 	= jql + "where pa.phaseCompletion.workflow.id = " + workflow.getId();
		jql 	= jql + " and pa.phaseCompletion.phase.id = " + workflow.getCurrentPhaseCompletion().getPhase().getId(); 
		jql 	= jql + " and pa.approvalComplete is null ";
		jql 	= jql + " and pa.rejectComplete is null ";
		
		try {
			query 	= entityManager.createQuery( jql );		
			results	= ( IPhaseApproval )query.getSingleResult();
		} catch( NoResultException e ) {
		}
	
		return results;
	}

}
