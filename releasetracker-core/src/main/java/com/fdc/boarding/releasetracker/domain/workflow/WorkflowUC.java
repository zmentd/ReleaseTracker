package com.fdc.boarding.releasetracker.domain.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.query.Conjunction;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.release.IReleasePersistenceGateway;
import com.fdc.boarding.releasetracker.domain.release.MilestoneByRom;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseApprovalEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseCompletionEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.StatusCompletionEntity;

public class WorkflowUC implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private IReleasePersistenceGateway	releaseGateway;

	@Inject
	private EntityPersistenceService	persistenceService;

	@Inject
	private IEntityReaderSvc			reader;
    
	@Inject
	private IPhaseApprovalTypePersistenceGateway	typeGateway;

    @Transactional
	public boolean approvalsComplete( IWorkflow workflow ){
		boolean									complete = false;
		Map<ApprovalType, IPhaseApprovalType>	required;
		IPhaseCompletion						current;
		IPhase									currentPhase;
		
		current			= workflow.getCurrentPhaseCompletion();
		currentPhase	= current.getPhase();
		required		= new HashMap<>();
		if( current == null || currentPhase.getRequiredApprovalTypes() == null || currentPhase.getRequiredApprovalTypes().isEmpty() ){
			complete	= true;
		}
		else{
			if( current.getPhaseApprovals() == null || current.getPhaseApprovals().isEmpty() ){
				complete	= false;	
			}
			else{
				for( IPhaseApprovalType pat : current.getPhase().getRequiredApprovalTypes() ){
					required.put( pat.getType(), pat );
				}
				for( IPhaseApproval pa : current.getPhaseApprovals() ){
					if( pa.getPhaseApprovalType() != null && 
					    required.containsKey( pa.getPhaseApprovalType().getType() ) &&
					    ( pa.getApprovalComplete() != null || pa.getRejectComplete() != null )
					){
						required.remove( pa.getPhaseApprovalType().getType() );
					}
				}
				complete	= required.keySet().isEmpty();	
				
			}
		}
		return complete;
	}
	
    @Transactional
	public IPhaseApprovalType determineNextApprovalType( IWorkflow workflow ){
    	List<IPhaseApprovalType>		types		= null;
		IPhaseApprovalType				type		= null;

		if( !approvalsComplete( workflow ) ){
			types	= typeGateway.findNonApprovedTypes( workflow );
			if( !types.isEmpty() ){
				type	= types.get( 0 );
			}
		}
		
		return type;
	}

    @Transactional
	public IPhaseApproval determineLastApprovalSent( IWorkflow workflow ){
		IPhaseApproval				last		= null;

		if( !approvalsComplete( workflow ) ){
			last	= typeGateway.findLastApprovalSent( workflow );
		}
		
		return last;
	}
	
    @Transactional
	public void approvalRequested( IWorkflow workflow, IPhaseApprovalType approvalType, IComment comment ){
		IPhaseApproval					approval;
		
		approval	= new PhaseApprovalEntity();
		approval.setApprovalStart( new LocalDate() );
		approval.setPhaseCompletion( workflow.getCurrentPhaseCompletion() );
		approval.setPhaseApprovalType( approvalType );
		approval.setPhaseType( workflow.getCurrentPhaseCompletion().getPhase().getType() );
		if( comment != null ){
			approval.addComment( comment );
		}
		persistenceService.insert( approval );
		
	}

    @Transactional
	public void approve( IWorkflow workflow, IPhaseApprovalType approvalType, IUser approver, IComment comment ){
		IPhaseApproval					approval	= null;
    	List<PhaseApprovalEntity>		results;
    	
		try {
			results		= reader.find( PhaseApprovalEntity.class
									 , "approvalStart"
									 , true
									 , Restriction.eq( "phaseCompletion", workflow.getCurrentPhaseCompletion() )
			);
			for( IPhaseApproval ap : results ){
				if( ap.getApprovalComplete() == null ){
					approval	= ap;
					break;
				}
			}
			if( approval != null ){
				approval.setApprovalComplete( new LocalDate() );
				approval.setApprovalCompletionDays( Days.daysBetween( approval.getApprovalStart(), approval.getApprovalComplete() ).getDays() );
				approval.setApprovedBy( approver );
				persistenceService.addChild( approval, approver, "approvedBy");
				if( comment != null ){
					approval.addComment( comment );
					persistenceService.addChild( approval, comment, "comments");
				}
				persistenceService.update( approval );
				if( approvalsComplete( workflow ) ){
					for( IPhase p : workflow.getCurrentPhaseCompletion().getPhase().getNextPhases() ){
						if( p.getIndex() == workflow.getCurrentPhaseCompletion().getPhase().getIndex() + 1 ){
							progressTo( workflow, p );
							break;
						}
					}
				}
			}
			else{
				//
				// Throw an error!
				//
			}
		} catch( QueryException e ) {
			e.printStackTrace();
		}

	}

    @Transactional
	public void reject( IWorkflow workflow, IPhaseApprovalType approvalType, IUser approver, IComment comment ){
		IPhaseApproval					approval	= null;
    	List<PhaseApprovalEntity>		results;
		
		try {
			results		= reader.find( PhaseApprovalEntity.class
									 , "approvalStart"
									 , true
									 , Conjunction.and( Restriction.eq( "phase", workflow.getCurrentPhaseCompletion().getPhase() )
											 		  , Restriction.eq( "phaseCompletion", workflow.getCurrentPhaseCompletion() )
							 		  ) 
			);
			for( IPhaseApproval ap : results ){
				if( ap.getApprovalComplete() == null ){
					approval	= ap;
					break;
				}
			}
			if( approval != null ){
				approval.setRejectComplete( new LocalDate() );
				approval.setApprovalCompletionDays( Days.daysBetween( approval.getApprovalStart(), approval.getRejectComplete() ).getDays() );
				approval.setApprovedBy( approver );
				persistenceService.addChild( approval, approver, "approvedBy");
				if( comment != null ){
					approval.addComment( comment );
					persistenceService.addChild( approval, comment, "comments");
				}
				persistenceService.update( approval );
			}
			else{
				//
				// Throw an error!
				//
			}
		} catch( QueryException e ) {
			e.printStackTrace();
		}

	}
	
    @Transactional
	public boolean canProgressTo( IWorkflow workflow, IPhase phase ){
		boolean							proceed;
		
		proceed = approvalsComplete( workflow );
		proceed = proceed && workflow.getCurrentPhaseCompletion().getPhase().getNextPhases().contains( phase );

		return proceed;
	}

    @Transactional
	public boolean canProgressTo( IWorkflow workflow, IStatus status ){
		boolean							proceed;
		
		proceed = workflow.getCurrentPhaseCompletion().getPhase().getAvailableStatuses().contains( status );

		return proceed;
	}
	
    @Transactional
	public IPhaseCompletion progressTo( IWorkflow workflow, IPhase phase ){
		IPhaseCompletion				cpc			= null;
		IPhaseCompletion				pc			= null;
    	MilestoneByRom					milestone	= null;
    	Rom								rom;
    	LocalDate						date;
		
		if( canProgressTo( workflow, phase ) ){
			date		= workflow.getTargetImplementationDate();
			rom			= workflow.getRom();
			if( date != null && rom != null ){
				milestone	= releaseGateway.findMilestoneByTargetDate( date, rom, phase.getType() );
			}
			//
			// End the current phase
			//
			cpc	= workflow.getCurrentPhaseCompletion();
			cpc.setCompletionDate( new LocalDate() );
			cpc.setDaysInPhase( Days.daysBetween( cpc.getEntryDate(), cpc.getCompletionDate() ).getDays() );
			cpc.setDaysFromExpectedCompletion( Days.daysBetween( cpc.getEntryDate(), cpc.getExpectedCompletionDate() ).getDays() );
		
			//
			// Start the new phase
			//
			pc			= new PhaseCompletionEntity();
			pc.setEntryDate( cpc.getEntryDate() );
			pc.setCompletionDate( new LocalDate() );
			pc.setDaysInPhase( Days.daysBetween( pc.getEntryDate(), pc.getCompletionDate() ).getDays() );
			pc.setWorkflow( workflow );
			pc.setPhase( phase );
			if( milestone != null ){
				pc.setExpectedCompletionDate( milestone.getDueDate() );
				pc.setDaysFromExpectedCompletion( Days.daysBetween( pc.getEntryDate(), pc.getExpectedCompletionDate() ).getDays() );
			}

			workflow.addPhaseCompletion( pc );
			workflow.setCurrentPhaseCompletion( pc );

			progress( cpc, pc, pc.getPhase().getAvailableStatuses().get( 0 ) );
			persistenceService.update( workflow );
		}
		
		return pc;
	}
	
    @Transactional
	public IStatusCompletion progressTo( IWorkflow workflow, IStatus status ){
		IStatusCompletion				sc			= null;
		
		if( canProgressTo( workflow, status ) ){
			sc	= progress( workflow.getCurrentPhaseCompletion(), workflow.getCurrentPhaseCompletion(), status );
			persistenceService.update( workflow );
		}
		
		return sc;
	}
	
    @Transactional
	private IStatusCompletion progress( IPhaseCompletion cpc, IPhaseCompletion newPc, IStatus status ){
		IStatusCompletion				csc			= null;
		IStatusCompletion				sc			= null;
		
		//
		// End the current status
		//
		csc		= cpc.getCurrentStatusCompletion();
		csc.setCompletionDate( new LocalDate() );
		csc.setDaysInStatus( Days.daysBetween( csc.getEntryDate(), csc.getCompletionDate() ).getDays() );

		//
		// Start the new status
		//
		sc			= new StatusCompletionEntity();
		sc.setEntryDate( new LocalDate() );
		sc.setPhaseCompletion( newPc );
		sc.setStatus( status );
		newPc.setCurrentStatusCompletion( sc );
		newPc.addStatusCompletion( sc );

		return sc;
	}
}
