package com.fdc.boarding.releasetracker.test.domain.workflow;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.fdc.boarding.core.query.Conjunction;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.common.dto.CommentDto;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.ApprovalRequest;
import com.fdc.boarding.releasetracker.domain.workflow.ApprovalResponse;
import com.fdc.boarding.releasetracker.domain.workflow.ApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseApprovalTypeResponse;
import com.fdc.boarding.releasetracker.domain.workflow.WorkflowRequest;
import com.fdc.boarding.releasetracker.domain.workflow.WorkflowUC;
import com.fdc.boarding.releasetracker.gateway.workflow.IPhaseApprovalTypePersistenceGateway;
import com.fdc.boarding.releasetracker.persistence.idea.IdeaEntity;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseApprovalTypeEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.StatusEntity;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;

public class TestWorkflowUC extends AbstractPersistenceTest{
    private WorkflowUC								usecase;
	private IEntityReaderSvc						reader;
	private EntityPersistenceService				persistenceService;
	private IPhaseApprovalTypePersistenceGateway	typeGateway;

	@Override
	public void injectServices() {
		usecase 			= CDIContext.getInstance().getBean( WorkflowUC.class );
		reader 				= CDIContext.getInstance().getBean( IEntityReaderSvc.class );
		persistenceService	= CDIContext.getInstance().getBean( EntityPersistenceService.class );
		typeGateway 		= CDIContext.getInstance().getBean( IPhaseApprovalTypePersistenceGateway.class );
	}

	@Test
	public void testApproveDev(){
		ApprovalRequest 				request;
    	IIdea							idea;
    	IPhase							phase;
    	IPhaseApprovalType				type;
    	List<PhaseApprovalTypeEntity>	results;
    	IComment						comment		= null;
    	IUser							approver;
		ApprovalResponse				response;
  	
    	try {
    		approver	= reader.findByKey( UserEntity.class, 1L );
    		idea		= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test"
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes.name" 
					   , "workflow.currentPhaseCompletion.phaseApprovals" 
					   , "workflow.currentPhaseCompletion.currentStatusCompletion.status" 
   				
    		);
    		phase		= reader.findByNaturalKey( PhaseEntity.class, "name", "HLE", "nextPhases", "availableStatuses", "requiredApprovalTypes" );
    		idea.getWorkflow().getCurrentPhaseCompletion().setPhase( phase );
    		idea.getWorkflow().getCurrentPhaseCompletion().setEntryDate( new LocalDate().withDayOfMonth( 1 ).withMonthOfYear( 9 ) );
    		usecase.updateWorkflow( idea.getWorkflow() );
    		results		= reader.find( PhaseApprovalTypeEntity.class
    								 , "name"
    								 , true
    								 , Conjunction.and( Restriction.eq( "type", ApprovalType.Development )
    										 		  , Restriction.eq( "phase", phase )
    										 		  ) 
    	    );
    		
    		Assert.assertFalse( results.isEmpty() );
    		type		= results.get( 0 );
    		request		= new ApprovalRequest();
    		request.setPhaseApprovalTypeId( type.getId() );
    		request.setWorkflowId( idea.getWorkflow().getId() );
    		request.setComment( CommentDto.from( comment ) );
    		
    		response	= usecase.approvalRequested( request );
			Assert.assertNotNull( response );
			Assert.assertNotNull( response.getPhaseApproval() );
			Assert.assertTrue( response.isSuccess() );
    		
			usecase.approve( idea.getWorkflow(), type, approver, comment );
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	@Test
	public void testApproveDevAgain(){
		ApprovalRequest 				request;
    	IIdea							idea;
    	IPhase							phase;
    	IPhaseApprovalType				type;
    	List<PhaseApprovalTypeEntity>	results;
    	IComment						comment		= null;
    	IUser							approver;
		ApprovalResponse				response;
    	
    	try {
    		request		= new ApprovalRequest();
    		approver	= reader.findByKey( UserEntity.class, 1L );
    		idea		= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes.name" 
					   , "workflow.currentPhaseCompletion.phaseApprovals" 
					   , "workflow.currentPhaseCompletion.currentStatusCompletion.status" 
   				
    		);
    		phase		= reader.findByNaturalKey( PhaseEntity.class, "name", "HLE", "nextPhases", "availableStatuses", "requiredApprovalTypes" );
    		idea.getWorkflow().getCurrentPhaseCompletion().setPhase( phase );
    		idea.getWorkflow().getCurrentPhaseCompletion().setEntryDate( new LocalDate().withDayOfMonth( 1 ).withMonthOfYear( 9 ) );
    		persistenceService.update( idea );
    		results		= reader.find( PhaseApprovalTypeEntity.class
    								 , "name"
    								 , true
    								 , Conjunction.and( Restriction.eq( "type", ApprovalType.Development )
    										 		  , Restriction.eq( "phase", phase )
    										 		  ) 
    	    );
    		
    		Assert.assertFalse( results.isEmpty() );
    		type		= results.get( 0 );
    		request.setPhaseApprovalTypeId( type.getId() );
    		request.setWorkflowId( idea.getWorkflow().getId() );
    		request.setComment( CommentDto.from( comment ) );
    		
    		response	= usecase.approvalRequested( request );
			Assert.assertNotNull( response );
			Assert.assertNotNull( response.getPhaseApproval() );
			Assert.assertTrue( response.isSuccess() );
    		
			response	= usecase.approvalRequested( request );
			Assert.assertNotNull( response );
			Assert.assertNotNull( response.getPhaseApproval() );
			Assert.assertTrue( response.isSuccess() );
    		usecase.approve( idea.getWorkflow(), type, approver, comment );
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	@Test
	public void testDetermineNextApprovalType(){
		WorkflowRequest 				request;
    	PhaseApprovalTypeResponse		response;
    	IIdea							idea;

		try {
	   		idea 	= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test" );
	   		request	= new WorkflowRequest();
	   		request.setWorkflowId( idea.getWorkflow().getId() );
	   		response	= usecase.determineNextApprovalType( request );
			Assert.assertNotNull( response );
			Assert.assertNotNull( response.getPhaseApprovalType() );
			Assert.assertTrue( response.isSuccess() );
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
    
	@Test
	public void testFindApprovalType(){
    	IIdea						idea;
    	List<IPhaseApprovalType>		types		= null;

		try {
			idea	= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes.name" 
					   , "workflow.currentPhaseCompletion.phaseApprovals" 
					   , "workflow.currentPhaseCompletion.currentStatusCompletion.status" 
					
			);
			types	= typeGateway.findNonApprovedTypes( idea.getWorkflow() );
			Assert.assertNotNull( types );
			Assert.assertTrue( !types.isEmpty() );
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
    @Test
    public void testProgressToPhase(){
    	IIdea						idea;
    	IPhase							phase;
    	IPhase							nextPhase;
    	IPhaseCompletion				pc;
    	
    	try {
    		idea		= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes" 
					   , "workflow.currentPhaseCompletion.phaseApprovals" 
					   , "workflow.currentPhaseCompletion.currentStatusCompletion" 
					   , "workflow.currentPhaseCompletion.statusCompletions" 
					   , "workflow.phaseCompletions" 
    		);
    		phase		= reader.findByNaturalKey( PhaseEntity.class, "name", "Slotting", "nextPhases", "availableStatuses", "requiredApprovalTypes" );
    		nextPhase	= reader.findByNaturalKey( PhaseEntity.class, "name", "DDD", "nextPhases", "availableStatuses", "requiredApprovalTypes" );
    		idea.getWorkflow().getCurrentPhaseCompletion().setPhase( phase );
    		idea.getWorkflow().getCurrentPhaseCompletion().setEntryDate( new LocalDate().withDayOfMonth( 1 ).withMonthOfYear( 9 ) );
    		persistenceService.update( idea.getWorkflow().getCurrentPhaseCompletion() );
    		
    		idea		= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test" 
					   , "workflow.currentPhaseCompletion.phase.nextPhases" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes" 
					   , "workflow.currentPhaseCompletion.phaseApprovals" 
					   , "workflow.currentPhaseCompletion.currentStatusCompletion" 
					   , "workflow.currentPhaseCompletion.statusCompletions" 
					   , "workflow.phaseCompletions" 
	 		);
	 		phase		= reader.findByNaturalKey( PhaseEntity.class, "name", "Slotting", "nextPhases", "availableStatuses", "requiredApprovalTypes" );
	 		nextPhase	= reader.findByNaturalKey( PhaseEntity.class, "name", "DDD", "nextPhases", "availableStatuses", "requiredApprovalTypes" );
    		Assert.assertTrue( usecase.canProgressTo( idea.getWorkflow(), nextPhase ) );
    		pc			= usecase.progressTo( idea.getWorkflow(), nextPhase );
    		Assert.assertNotNull( pc );
    		Assert.assertEquals( idea.getWorkflow().getCurrentPhaseCompletion().getPhase().getId(), nextPhase.getId() );
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
    }
    
    @Test
    public void testProgressToStatus(){
    	IIdea						idea;
    	IPhase							phase;
    	IStatus							status;
    	IStatus							nextStatus;
    	IStatusCompletion				sc;
    	
    	try {
    		idea		= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test"
    											 , "workflow.currentPhaseCompletion.currentStatusCompletion" 
    		);
    		phase		= reader.findByNaturalKey( PhaseEntity.class, "name", "HLAD", "nextPhases", "availableStatuses" );
    		status		= reader.findByNaturalKey( StatusEntity.class, "name", "In Queue" );
    		nextStatus	= reader.findByNaturalKey( StatusEntity.class, "name", "In Progress" );
    		idea.getWorkflow().getCurrentPhaseCompletion().setPhase( phase );
    		idea.getWorkflow().getCurrentPhaseCompletion().setEntryDate( new LocalDate().withDayOfMonth( 1 ).withMonthOfYear( 9 ) );
    		idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().setStatus( status );
    		idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().setEntryDate( new LocalDate().withDayOfMonth( 1 ).withMonthOfYear( 9 ) );
    		persistenceService.update( idea );
    		idea		= reader.findByNaturalKey( IdeaEntity.class, "ideaNumber", "CON-042719_test" 
					   , "workflow.currentPhaseCompletion.phase.nextPhases" 
					   , "workflow.currentPhaseCompletion.phase.requiredApprovalTypes" 
					   , "workflow.currentPhaseCompletion.phase.availableStatuses" 
					   , "workflow.currentPhaseCompletion.phaseApprovals" 
					   , "workflow.currentPhaseCompletion.currentStatusCompletion" 
					   , "workflow.currentPhaseCompletion.statusCompletions" 
					   , "workflow.phaseCompletions" 
	 		);
    		Assert.assertTrue( usecase.canProgressTo( idea.getWorkflow(), nextStatus ) );
    		sc			= usecase.progressTo( idea.getWorkflow(), nextStatus );
    		Assert.assertNotNull( sc );
    		Assert.assertEquals( idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getStatus().getId(), nextStatus.getId() );
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
    }

}
