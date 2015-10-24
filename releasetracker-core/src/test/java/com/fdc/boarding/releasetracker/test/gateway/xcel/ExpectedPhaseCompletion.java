package com.fdc.boarding.releasetracker.test.gateway.xcel;

import junit.framework.Assert;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;

public class ExpectedPhaseCompletion<E extends AbstractExpected> extends AbstractExpected{
	
	private IPhaseCompletion			pc;
	private E							parent;

	public ExpectedPhaseCompletion(IPhaseCompletion pc, IEntityReaderSvc reader, E parent ) {
		super();
		this.pc 	= pc;
		this.reader = reader;
		this.parent	= parent;
	}

	public ExpectedPhaseCompletion<E> daysInPhase( Integer value ){
		Assert.assertEquals( value , pc.getDaysInPhase() );
		return this;
	}

	public ExpectedPhaseCompletion<E> daysFromExpectedCompletion( Integer value ){
		Assert.assertEquals( value , pc.getDaysFromExpectedCompletion() );
		return this;
	}

	public ExpectedPhaseCompletion<E> entryDate( String value ){
		assertDate( value, pc.getEntryDate() );
		return this;
	}

	public ExpectedPhaseCompletion<E> completionDate( String value ){
		assertDate( value, pc.getCompletionDate() );
		return this;
	}

	public ExpectedPhaseCompletion<E> expectedCompletionDate( String value ){
		assertDate( value, pc.getExpectedCompletionDate() );
		return this;
	}

	public ExpectedPhaseCompletion<E> phase( String value ){
		Assert.assertEquals( value , pc.getPhase().getName() );
		return this;
	}

	public ExpectedStatusCompletionIterator<ExpectedPhaseCompletion<E>> withStatusCompletions(){
		return new ExpectedStatusCompletionIterator<ExpectedPhaseCompletion<E>>( reader, pc.getStatusCompletions(), this );
	}

	public ExpectedStatusCompletion<ExpectedPhaseCompletion<E>> withCurrentStatusCompletion(){
		return new ExpectedStatusCompletion<ExpectedPhaseCompletion<E>>( pc.getCurrentStatusCompletion(), reader, this );
	}

	public E parent(){
		return parent;
	}

	
}
