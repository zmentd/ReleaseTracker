package com.fdc.boarding.releasetracker.usecase.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.intercept.annotation.ServiceCall;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.ApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.gateway.release.IReleasePersistenceGateway;
import com.fdc.boarding.releasetracker.gateway.workflow.IPhaseApprovalTypePersistenceGateway;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseApprovalEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseCompletionEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.StatusCompletionEntity;
import com.fdc.boarding.releasetracker.usecase.AbstractUseCase;
import com.fdc.boarding.releasetracker.usecase.release.MilestoneByRom;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseApprovalDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseApprovalTypeDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.PhaseCompletionDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.StatusCompletionDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.WorkflowDto;

public class WorkflowUC extends AbstractUseCase implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private IReleasePersistenceGateway	releaseGateway;

	@Inject
	private EntityPersistenceService	persistenceService;

	@Inject
	private IEntityReaderSvc			reader;
    
	@Inject
	private IPhaseApprovalTypePersistenceGateway	typeGateway;
	
	@ServiceCall
	@Transactional
	public ApprovalResponse approvalRequested( ApprovalRequest request ){
		IWorkflow 						workflow;
		IPhaseApprovalType 				approvalType;
		IPhaseApproval					approval;
		IComment						comment;
		IUser							user;
		ApprovalResponse				response;
		
		response		= new ApprovalResponse();
		try {
			workflow		= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			approvalType	= reader.findByKeyWithAssertion( IPhaseApprovalType.class, request.getPhaseApprovalTypeId() );
			approval		= CDIContext.getInstance().getBean( IPhaseApproval.class );
			approval.setApprovalStart( new LocalDate() );
			approval.setPhaseCompletion( workflow.getCurrentPhaseCompletion() );
			approval.setPhaseApprovalType( approvalType );
			approval.setMilestoneType( workflow.getCurrentPhaseCompletion().getPhase().getType() );
			if( request.getComment() != null ){
				comment		= CDIContext.getInstance().getBean( IComment.class );
				comment.setComment( request.getComment().getComment() );
				comment.setCommentDate( request.getComment().getCommentDate() );
				comment.setCommentType( request.getComment().getCommentType() );
				comment.setJiraSourced( false );
				user		= reader.findByKey( IUser.class, request.getComment().getUser().getId() );
				comment.setUser( user );
				approval.addComment( comment );
			}
			if( validate( approval, response ) ){
				persistenceService.insert( approval );
				response.setPhaseApproval( PhaseApprovalDto.from( approval ) );
				response.setWorkflow( WorkflowDto.from( workflow ) );
				response.setSuccess( true );
				response.setMessage( "Approval requested." );
			}
		} catch( Exception e ){
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to send approval request." );
		}
		
		return response;
	}	

	protected boolean approvalsComplete( IWorkflow workflow ){
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
	
	@ServiceCall
	@Transactional
	public boolean approvalsComplete( WorkflowRequest request ){
    	IWorkflow 								workflow;
		
		workflow	= reader.findByKey( IWorkflow.class, request.getWorkflowId() );
		return approvalsComplete( workflow );
	}
	
	@ServiceCall( validationGroups = {Default.class, ApprovalValidation.class})
	@Transactional
	public ApprovalResponse approve( ApprovalRequest request ){
		IWorkflow 						workflow;
		IPhaseApprovalType 				approvalType;
		IComment						comment;
		IUser							approver;
		ApprovalResponse				response;

		
		IPhaseApproval					approval	= null;
    	List<PhaseApprovalEntity>		results;
    	
		response		= new ApprovalResponse();
		try {
			//
			// Find the relevant objects
			//
			workflow		= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			approvalType	= reader.findByKeyWithAssertion( IPhaseApprovalType.class, request.getPhaseApprovalTypeId() );
			approver		= reader.findByKeyWithAssertion( IUser.class, request.getApproverId() );
			results			= reader.find( PhaseApprovalEntity.class
									 	 , "approvalStart"
									 	 , true
									 	 , Restriction.eq( "phaseCompletion", workflow.getCurrentPhaseCompletion() )
			);
			//
			// Find the next non-complete approval for the type given.
			//
			for( IPhaseApproval pa : results ){
				if( pa.getApprovalComplete() == null && pa.getPhaseApprovalType().equals( approvalType ) ){
					approval	= pa;
					break;
				}
			}
			if( approval != null ){
				//
				// Ensure the given approver can actually approve. (Not Supported Yet)
				//
				
				//
				// Complete the approval
				//
				approval.setApprovalComplete( new LocalDate() );
				approval.setApprovalCompletionDays( Days.daysBetween( approval.getApprovalStart(), approval.getApprovalComplete() ).getDays() );
				approval.setApprovedBy( approver );
				persistenceService.addChild( approval, approver, "approvedBy");

				//
				// Add the comment.
				//
				if( request.getComment() != null ){
					comment		= CDIContext.getInstance().getBean( IComment.class );
					comment.setComment( request.getComment().getComment() );
					comment.setCommentDate( request.getComment().getCommentDate() );
					comment.setCommentType( request.getComment().getCommentType() );
					comment.setJiraSourced( false );
					comment.setUser( approver );
					approval.addComment( comment );
					persistenceService.addChild( approval, comment, "comments");
				}
				persistenceService.update( approval );

				//
				// If the approvals are now complete, progress to the next phase.
				//
				if( approvalsComplete( workflow ) ){
					for( IPhase p : workflow.getCurrentPhaseCompletion().getPhase().getNextPhases() ){
						if( p.getIndex() == workflow.getCurrentPhaseCompletion().getPhase().getIndex() + 1 ){
							progressTo( workflow, p );
							break;
						}
					}
				}
				response.setPhaseApproval( PhaseApprovalDto.from( approval ) );
				response.setWorkflow( WorkflowDto.from( workflow ) );
				response.setSuccess( true );
				response.setMessage( "Workflow approved." );
			}
			else{
				//
				// Previous approval not found.
				//
				response.setSuccess( false );
				response.setMessage( "Unable to approve workflow, approval type not found." );
			}
		} catch( Exception e ) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to approve workflow, an unexpected exception occured." );
		}

		return response;
	}

	protected boolean canProgressTo( IWorkflow workflow, IPhase phase ){
		boolean							proceed;
		
		proceed = approvalsComplete( workflow );
		proceed = proceed && workflow.getCurrentPhaseCompletion().getPhase().getNextPhases().contains( phase );

		return proceed;
	}

	protected boolean canProgressTo( IWorkflow workflow, IStatus status ){
		boolean							proceed;
		
		proceed = workflow.getCurrentPhaseCompletion().getPhase().getAvailableStatuses().contains( status );

		return proceed;
	}

	@ServiceCall( validationGroups = {Default.class, PhaseProgressionValidation.class})
    @Transactional
	public ProgressionCheckResponse canProgressToPhase( ProgressionRequest request ){
		IWorkflow 						workflow;
		IPhase							phase;
		boolean							proceed;
		ProgressionCheckResponse		response;
		
		
		response	= new ProgressionCheckResponse();
		try {
			workflow	= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			phase		= reader.findByKeyWithAssertion( IPhase.class, request.getPhaseId() );
			proceed 	= canProgressTo( workflow, phase );
			response.setCanProgress( proceed );
			response.setSuccess( true );
			response.setMessage( "Progression Check completed." );
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to check progression, an unexpected exception occured." );
		}

		return response;
	}
	
    @ServiceCall( validationGroups = {Default.class, StatusProgressionValidation.class})
    @Transactional
	public ProgressionCheckResponse canProgressToStatus( ProgressionRequest request ){
		IWorkflow 						workflow;
		IStatus							status;
		boolean							proceed;
		ProgressionCheckResponse		response;
		
		response	= new ProgressionCheckResponse();
		try{
			workflow	= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			status		= reader.findByKeyWithAssertion( IStatus.class, request.getStatusId() );
			proceed 	= canProgressTo( workflow, status );
			response.setCanProgress( proceed );
			response.setSuccess( true );
			response.setMessage( "Progression Check completed." );
		} 
		catch( Exception e ){
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to check progression, an unexpected exception occured." );
		}
		
		return response;
	}

    protected IPhaseApproval determineLastApprovalSent( IWorkflow workflow ){
		IPhaseApproval				last		= null;

		if( !approvalsComplete( workflow ) ){
			last	= typeGateway.findLastApprovalSent( workflow );
		}
		
		return last;
	}

	@ServiceCall
    @Transactional
	public ApprovalResponse determineLastApprovalSent( WorkflowRequest request ){
    	IWorkflow 						workflow;
    	ApprovalResponse				response;
		IPhaseApproval					last		= null;

		response	= new ApprovalResponse();
		try {
			workflow	= reader.findByKey( IWorkflow.class, request.getWorkflowId() );
			last		= determineLastApprovalSent( workflow );
			response.setMessage( "Operation successful." );
			response.setSuccess( true );
			response.setPhaseApproval( PhaseApprovalDto.from( last ) );
			response.setWorkflow( WorkflowDto.from( workflow ) );
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to determine last approval sent, an unexpected exception occured." );
		}
		
		return response;
	}

    protected IPhaseApprovalType determineNextApprovalType( IWorkflow workflow ){
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
	
    @ServiceCall
    @Transactional
	public PhaseApprovalTypeResponse determineNextApprovalType( WorkflowRequest request ){
    	IWorkflow 								workflow;
    	IPhaseApprovalType						type;
    	PhaseApprovalTypeResponse				response;
    	
    	response	= new PhaseApprovalTypeResponse();
		try {
			workflow	= reader.findByKey( IWorkflow.class, request.getWorkflowId() );
			type		= determineNextApprovalType( workflow );
			response.setMessage( "Operation successful." );
			response.setSuccess( true );
			response.setPhaseApprovalType( PhaseApprovalTypeDto.from( type ) );
		} 
		catch( Exception e ) {
			e.printStackTrace();
			response.setMessage( "Unable to determine next approval type:" + e.getMessage() );
			response.setSuccess( false );
		}
		
		return response;
	}

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
	
	protected IPhaseCompletion progressTo( IWorkflow workflow, IPhase phase ){
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
	
	protected IStatusCompletion progressTo( IWorkflow workflow, IStatus status ){
		IStatusCompletion				sc			= null;
		
		if( canProgressTo( workflow, status ) ){
			sc	= progress( workflow.getCurrentPhaseCompletion(), workflow.getCurrentPhaseCompletion(), status );
			persistenceService.update( workflow );
		}
		
		return sc;
	}
   
	@ServiceCall( validationGroups = {Default.class, PhaseProgressionValidation.class})
    @Transactional
	public PhaseProgressionResponse progressToPhase( ProgressionRequest request ){
		IWorkflow 						workflow;
		IPhase							phase;
		IPhaseCompletion				pc			= null;
		PhaseProgressionResponse		response;
		
		//
		// TODO: If this is an IdeaWorkflow, progress all TeamImpactWorkflows
		//       If this is a TeamImpactWorkflow, check to see if it is the last and if so progress the IdeaWorkflow as well
		//
		response	= new PhaseProgressionResponse();
		try {
			workflow	= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			phase		= reader.findByKeyWithAssertion( IPhase.class, request.getPhaseId() );
			pc 			= progressTo( workflow, phase );
			response.setCompletion( PhaseCompletionDto.from( pc ) );
			response.setWorkflow( WorkflowDto.from( workflow ) );
			response.setSuccess( true );
			response.setMessage( "Progress completed." );
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to progress workflow, an unexpected exception occured." );
		}

    	return response;
    }
	
    @ServiceCall( validationGroups = {Default.class, StatusProgressionValidation.class})
    @Transactional
	public StatusProgressionResponse progressToStatus( ProgressionRequest request ){
		IWorkflow 						workflow;
		IStatus							status;
		IStatusCompletion				sc			= null;
		StatusProgressionResponse		response;
		
		response	= new StatusProgressionResponse();
		try {
			workflow	= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			status		= reader.findByKeyWithAssertion( IStatus.class, request.getStatusId() );
			sc 			= progressTo( workflow, status );
			response.setStatusCompletion( StatusCompletionDto.from( sc ) );
			response.setPhaseCompletion( PhaseCompletionDto.from( sc.getPhaseCompletion() ) );
			response.setWorkflow( WorkflowDto.from( workflow ) );
			response.setSuccess( true );
			response.setMessage( "Progress completed." );
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to progress workflow, an unexpected exception occured." );
		}

    	return response;
    }
	
	@ServiceCall( validationGroups = {Default.class, ApprovalValidation.class})
	@Transactional
	public ApprovalResponse reject( ApprovalRequest request ){
		IWorkflow 						workflow;
		IPhaseApprovalType 				approvalType;
		IComment						comment;
		IUser							approver;
		ApprovalResponse				response;

		
		IPhaseApproval					approval	= null;
    	List<PhaseApprovalEntity>		results;
    	
		response		= new ApprovalResponse();
		try {
			//
			// Find the relevant objects
			//
			workflow		= reader.findByKeyWithAssertion( IWorkflow.class, request.getWorkflowId() );
			approvalType	= reader.findByKeyWithAssertion( IPhaseApprovalType.class, request.getPhaseApprovalTypeId() );
			approver		= reader.findByKeyWithAssertion( IUser.class, request.getApproverId() );
			results			= reader.find( PhaseApprovalEntity.class
									 	 , "approvalStart"
									 	 , true
									 	 , Restriction.eq( "phaseCompletion", workflow.getCurrentPhaseCompletion() )
			);
			//
			// Find the next non-complete approval for the type given.
			//
			for( IPhaseApproval pa : results ){
				if( pa.getApprovalComplete() == null && pa.getPhaseApprovalType().equals( approvalType ) ){
					approval	= pa;
					break;
				}
			}
			if( approval != null ){
				//
				// Ensure the given approver can actually approve. (Not Supported Yet)
				//
				
				//
				// Complete the approval
				//
				approval.setRejectComplete( new LocalDate() );
				approval.setApprovalCompletionDays( Days.daysBetween( approval.getApprovalStart(), approval.getApprovalComplete() ).getDays() );
				approval.setApprovedBy( approver );
				persistenceService.addChild( approval, approver, "approvedBy");

				//
				// Add the comment.
				//
				if( request.getComment() != null ){
					comment		= CDIContext.getInstance().getBean( IComment.class );
					comment.setComment( request.getComment().getComment() );
					comment.setCommentDate( request.getComment().getCommentDate() );
					comment.setCommentType( request.getComment().getCommentType() );
					comment.setJiraSourced( false );
					comment.setUser( approver );
					approval.addComment( comment );
					persistenceService.addChild( approval, comment, "comments");
				}
				persistenceService.update( approval );

				response.setPhaseApproval( PhaseApprovalDto.from( approval ) );
				response.setWorkflow( WorkflowDto.from( workflow ) );
				response.setSuccess( true );
				response.setMessage( "Workflow rejected." );
			}
			else{
				//
				// Previous approval not found.
				//
				response.setSuccess( false );
				response.setMessage( "Unable to reject workflow, approval type not found." );
			}
		} catch( Exception e ) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to reject workflow, an unexpected exception occured." );
		}

		return response;
	}
	
	@Transactional
	public void updateWorkflow( IWorkflow workflow ){
		persistenceService.update( workflow );
	}
}
