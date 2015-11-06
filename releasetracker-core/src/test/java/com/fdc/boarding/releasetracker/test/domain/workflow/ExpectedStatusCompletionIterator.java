package com.fdc.boarding.releasetracker.test.domain.workflow;

import java.util.Collection;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpected;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpectedMap;

public class ExpectedStatusCompletionIterator<P extends AbstractExpected> extends AbstractExpectedMap<P, IStatusCompletion, ExpectedStatusCompletion<ExpectedStatusCompletionIterator<P>>> {

	public ExpectedStatusCompletionIterator(IEntityReaderSvc reader, Collection<IStatusCompletion> collection, P parent) {
		super(reader, collection, parent);
	}

	@Override
	protected ExpectedStatusCompletion<ExpectedStatusCompletionIterator<P>> createNext(IStatusCompletion ent) {
		ExpectedStatusCompletion<ExpectedStatusCompletionIterator<P>>			epc;
		
		epc	= new ExpectedStatusCompletion<ExpectedStatusCompletionIterator<P>>( ent, reader, this );
		
		return epc;
	}

	public P parent(){
		return ( P )parent;
	}

	@Override
	protected String mapKey( IStatusCompletion ent ) {
		return ent.getPhaseCompletion().getPhase().getName();
	}
}
