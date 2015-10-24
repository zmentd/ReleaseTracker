package com.fdc.boarding.releasetracker.test.gateway.xcel;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;

public class ExpectedIdea extends AbstractExpected {

	public static ExpectedIdea			instance	= new ExpectedIdea();

	public static LocalDate toDate( String value ){
		LocalDate						date;
		DateTimeFormatter 				formatter;
		
		formatter	= DateTimeFormat.forPattern( "MM/dd/yyyy" );
		date		= LocalDate.parse( value, formatter );
		
		return date;
	}
	
	private IIdea						idea;
	
	public ExpectedIdea amendmentNumber( String value ){
		Assert.assertEquals( value , idea.getAmendmentNumber() );
		return this;
	}
	
	public ExpectedIdea umbrellaIdea( boolean value ){
		Assert.assertEquals( value , idea.getIsUmbrellaIdea().booleanValue() );
		return this;
	}

	public ExpectedIdea assignedTo( String value ){
		assertSameUser( value, idea.getAssignedTo() );
		return this;
	}

	public ExpectedIdea clarityRom( Rom value ){
		Assert.assertEquals( value , idea.getClarityRom() );
		return this;
	}

	public ExpectedIdea createDate( String value ){
		assertDate( value, idea.getCreateDate() );
		return this;
	}

	public ExpectedIdea demandOwner( String value ){
		assertSameUser( value , idea.getDemandOwner() );
		return this;
	}

	public ExpectedIdea description( String value ){
		Assert.assertEquals( value , idea.getDescription() );
		return this;
	}

	public ExpectedIdea descriptionStartsWith( String value ){
		Assert.assertNotNull( idea.getDescription() );
		Assert.assertTrue( idea.getDescription().startsWith( value ) );
		return this;
	}

	public ExpectedIdea expectFrom( IIdea idea ){
		this.idea	= idea;
		return this;
	}

	public ExpectedIdea expectFrom( String value ){
		idea	= reader.findByNaturalKey( IIdea.class, "ideaNumber", value, "assignedTo"
																		   , "demandOwner"
																		   , "solutionArchitect" 
																		   , "originalRequestor"
																		   , "workflow.currentPhaseCompletion.currentStatusCompletion"
																		   , "workflow.currentPhaseCompletion.statusCompletions"
																		   , "workflow.phaseCompletions.statusCompletions"
																		   , "workflow.release"
																		   , "workflow.comments"
																		   , "teamImpacts"
																		   , "teamImpacts.team"
																		   , "teamImpacts.workflow.release"
																		   , "teamImpacts.workflow.phaseCompletions.statusCompletions"
				);
		Assert.assertNotNull( idea );
		return this;
	}

	public ExpectedIdea name( String value ){
		Assert.assertEquals( value , idea.getName() );
		return this;
	}

	public ExpectedIdea number( String value ){
		Assert.assertEquals( value , idea.getIdeaNumber() );
		return this;
	}

	public ExpectedIdea originalRequestor( String value ){
		assertSameUser( value , idea.getOriginalRequestor() );
		return this;
	}

	public ExpectedIdea overallRom( Rom value ){
		Assert.assertEquals( value , idea.getOverallRom() );
		return this;
	}

	public ExpectedIdea priority( Integer value ){
		Assert.assertEquals( value , idea.getPriority() );
		return this;
	}

	public ExpectedIdea projectNumber( String value ){
		Assert.assertEquals( value , idea.getProjectNumber() );
		return this;
	}
	
	public ExpectedIdea rom( Rom value ){
		Assert.assertEquals( value , idea.getWorkflow().getRom() );
		return this;
	}
	public ExpectedIdea solutionArchitect( String value ){
		assertSameUser( value , idea.getSolutionArchitect() );
		return this;
	}

	public ExpectedIdea targetImplementationDate( String value ){
		assertDate( value, idea.getWorkflow().getTargetImplementationDate() );
		return this;
	}

	public ExpectedIdea phaseTargetDate( String value ){
		assertDate( value, idea.getWorkflow().getPhaseTargetDate() );
		return this;
	}

	public ExpectedIdea currentPhaseEntryDate( String value ){
		assertDate( value, idea.getWorkflow().getCurrentPhaseCompletion().getEntryDate() );
		return this;
	}

	public ExpectedIdea currentStatusEntryDate( String value ){
		assertDate( value, idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getEntryDate() );
		return this;
	}

	public ExpectedIdea currentStatus( String value ){
		Assert.assertEquals( value , idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getStatus().getName() );
		return this;
	}

	public ExpectedIdea currentPhase( String value ){
		Assert.assertEquals( value , idea.getWorkflow().getCurrentPhaseCompletion().getPhase().getName() );
		return this;
	}

	public ExpectedIdea release( String value ){
		if( value != null ){
			Assert.assertNotNull( idea.getWorkflow().getRelease() );
			Assert.assertNotNull( idea.getWorkflow().getRelease().getReleaseDate() );
			Assert.assertEquals( value , idea.getWorkflow().getRelease().getReleaseDate().toString( "YY.MM" ) );
		}
		else{
			Assert.assertNull( idea.getWorkflow().getRelease() );
		}
		return this;
	}

	public ExpectedPhaseCompletion<ExpectedIdea> withCurrentPhaseCompletion(){
		return new ExpectedPhaseCompletion<ExpectedIdea>( idea.getWorkflow().getCurrentPhaseCompletion(), reader, this );
	}

	public ExpectedStatusCompletion<ExpectedIdea> withCurrentStatusCompletion(){
		return new ExpectedStatusCompletion<ExpectedIdea>( idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion(), reader, this );
	}

	public ExpectedPhaseCompletionIterator<ExpectedIdea> withPhaseCompletions(){
		return new ExpectedPhaseCompletionIterator<ExpectedIdea>( reader, idea.getWorkflow().getPhaseCompletions(), this );
	}

	public ExpectedCommentIterator<ExpectedIdea> withComments(){
		return new ExpectedCommentIterator<ExpectedIdea>( reader, idea.getWorkflow().getComments(), this );
	}

	public ExpectedTeamImpactIterator<ExpectedIdea> withTeamImpacts(){
		return new ExpectedTeamImpactIterator<ExpectedIdea>( reader, idea.getTeamImpacts(), this );
	}
	
}
