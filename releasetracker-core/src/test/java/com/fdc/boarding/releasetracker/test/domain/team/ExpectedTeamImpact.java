package com.fdc.boarding.releasetracker.test.domain.team;

import junit.framework.Assert;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpected;
import com.fdc.boarding.releasetracker.test.domain.workflow.ExpectedPhaseCompletion;
import com.fdc.boarding.releasetracker.test.domain.workflow.ExpectedPhaseCompletionIterator;

public class ExpectedTeamImpact<E extends AbstractExpected> extends AbstractExpected{
	
	private ITeamImpact					pc;
	private E							parent;

	public ExpectedTeamImpact(ITeamImpact pc, IEntityReaderSvc reader, E parent ) {
		super();
		this.pc 	= pc;
		this.reader = reader;
		this.parent	= parent;
	}

	public ExpectedTeamImpact<E> noImpact( boolean value ){
		Assert.assertEquals( value , pc.isNoImpact() );
		return this;
	}

	public ExpectedTeamImpact<E> noImpactDate( String value ){
		assertDate( value, pc.getNoImpactDate() );
		return this;
	}

	public ExpectedTeamImpact<E> supportOnly( boolean value ){
		Assert.assertEquals( value , pc.isSupportOnly() );
		return this;
	}

	public ExpectedTeamImpact<E> plannedEffortNotOpen( boolean value ){
		Assert.assertEquals( value , pc.isPlannedEffortNotOpen() );
		return this;
	}

	public ExpectedTeamImpact<E> team( String value ){
		Assert.assertEquals( value , pc.getTeam().getName() );
		return this;
	}

	public ExpectedTeamImpact<E> romOwner( String value ){
		assertSameUser( value, pc.getRomOwner() );
		return this;
	}

	public ExpectedTeamImpact<E> phaseTargetDate( String value ){
		assertDate( value, pc.getWorkflow().getPhaseTargetDate() );
		return this;
	}

	public ExpectedTeamImpact<E> currentPhaseEntryDate( String value ){
		assertDate( value, pc.getWorkflow().getCurrentPhaseCompletion().getEntryDate() );
		return this;
	}

	public ExpectedTeamImpact<E> currentStatusEntryDate( String value ){
		assertDate( value, pc.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getEntryDate() );
		return this;
	}

	public ExpectedTeamImpact<E> currentStatus( String value ){
		Assert.assertEquals( value , pc.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getStatus().getName() );
		return this;
	}

	public ExpectedTeamImpact<E> currentPhase( String value ){
		Assert.assertEquals( value , pc.getWorkflow().getCurrentPhaseCompletion().getPhase().getName() );
		return this;
	}

	public ExpectedTeamImpact<E> release( String value ){
		if( value != null ){
			Assert.assertNotNull( pc.getWorkflow().getRelease() );
			Assert.assertNotNull( pc.getWorkflow().getRelease().getReleaseDate() );
			Assert.assertEquals( value , pc.getWorkflow().getRelease().getReleaseDate().toString( "YY.MM" ) );
		}
		else{
			Assert.assertNull( pc.getWorkflow().getRelease() );
		}
		return this;
	}

	public ExpectedPhaseCompletion<ExpectedTeamImpact<E>> withCurrentPhaseCompletion(){
		Assert.assertNotNull( pc.getWorkflow().getCurrentPhaseCompletion() );
		return new ExpectedPhaseCompletion<ExpectedTeamImpact<E>>( pc.getWorkflow().getCurrentPhaseCompletion(), reader, this );
	}

	public ExpectedPhaseCompletionIterator<ExpectedTeamImpact<E>> withPhaseCompletions(){
		return new ExpectedPhaseCompletionIterator<ExpectedTeamImpact<E>>( reader, pc.getWorkflow().getPhaseCompletions(), this );
	}

	public E parent(){
		return parent;
	}

	
}
