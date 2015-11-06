package com.fdc.boarding.releasetracker.test.domain.common;

import junit.framework.Assert;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpected;

public class ExpectedComment<E extends AbstractExpected> extends AbstractExpected{
	
	private IComment					pc;
	private E							parent;

	public ExpectedComment(IComment pc, IEntityReaderSvc reader, E parent ) {
		super();
		this.pc 	= pc;
		this.reader = reader;
		this.parent	= parent;
	}

	public ExpectedComment<E> commentDate( String value ){
		assertDate( value, pc.getCommentDate().toLocalDate() );
		return this;
	}

	public ExpectedComment<E> startsWith( String value ){
		Assert.assertNotNull( pc.getComment() );
		Assert.assertTrue( pc.getComment().startsWith( value ) );
		return this;
	}

	public E parent(){
		return parent;
	}

	
}
