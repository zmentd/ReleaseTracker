package com.fdc.boarding.releasetracker.test.domain.common;

import java.util.Collection;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpected;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpectedIterator;

public class ExpectedCommentIterator<P extends AbstractExpected> extends AbstractExpectedIterator<P, IComment, ExpectedComment<ExpectedCommentIterator<P>>> {

	public ExpectedCommentIterator(IEntityReaderSvc reader, Collection<IComment> collection, P parent) {
		super(reader, collection, parent);
	}

	@Override
	protected ExpectedComment<ExpectedCommentIterator<P>> createNext(IComment ent) {
		ExpectedComment<ExpectedCommentIterator<P>>			epc;
		
		epc	= new ExpectedComment<ExpectedCommentIterator<P>>( ent, reader, this );
		
		return epc;
	}

	public P parent(){
		return ( P )parent;
	}
}
