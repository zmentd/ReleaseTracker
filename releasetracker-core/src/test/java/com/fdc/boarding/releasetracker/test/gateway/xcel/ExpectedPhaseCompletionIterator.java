package com.fdc.boarding.releasetracker.test.gateway.xcel;

import java.util.Collection;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;

public class ExpectedPhaseCompletionIterator<P extends AbstractExpected> extends AbstractExpectedIterator<P, IPhaseCompletion, ExpectedPhaseCompletion<ExpectedPhaseCompletionIterator<P>>> {

	public ExpectedPhaseCompletionIterator(IEntityReaderSvc reader, Collection<IPhaseCompletion> collection, P parent) {
		super(reader, collection, parent);
	}

	@Override
	protected ExpectedPhaseCompletion<ExpectedPhaseCompletionIterator<P>> createNext(IPhaseCompletion ent) {
		ExpectedPhaseCompletion<ExpectedPhaseCompletionIterator<P>>			epc;
		
		epc	= new ExpectedPhaseCompletion<ExpectedPhaseCompletionIterator<P>>( ent, reader, this );
		
		return epc;
	}

	public P parent(){
		return ( P )parent;
	}
}
