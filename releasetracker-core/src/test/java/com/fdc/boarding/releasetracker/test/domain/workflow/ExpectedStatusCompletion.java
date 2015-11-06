package com.fdc.boarding.releasetracker.test.domain.workflow;

import junit.framework.Assert;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpected;

public class ExpectedStatusCompletion<E extends AbstractExpected> extends AbstractExpected{
	
	private IStatusCompletion			pc;
	private E							parent;

	public ExpectedStatusCompletion(IStatusCompletion pc, IEntityReaderSvc reader, E parent ) {
		super();
		this.pc 	= pc;
		this.reader = reader;
		this.parent	= parent;
	}

	public ExpectedStatusCompletion<E> daysInStatus( Integer value ){
		Assert.assertEquals( value , pc.getDaysInStatus() );
		return this;
	}

	public ExpectedStatusCompletion<E> entryDate( String value ){
		assertDate( value, pc.getEntryDate() );
		return this;
	}

	public ExpectedStatusCompletion<E> completionDate( String value ){
		assertDate( value, pc.getCompletionDate() );
		return this;
	}

	public ExpectedStatusCompletion<E> status( String value ){
		Assert.assertEquals( value , pc.getStatus().getName() );
		return this;
	}

	public E parent(){
		return parent;
	}

	
}
