package com.fdc.boarding.releasetracker.test.domain;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Assert;

import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.service.IEntityReaderSvc;

public abstract class AbstractExpectedIterator<P extends AbstractExpected, T extends IEntity<?>, E extends AbstractExpected> extends AbstractExpected{

	protected Collection<T> 			collection;
	protected P							parent;
	protected Iterator<T>				iter;
	protected Map<String, T>			byName;
	
	public AbstractExpectedIterator( IEntityReaderSvc reader, Collection<T> collection, P parent ){
		this.reader		= reader;
		this.collection	= collection;
		this.parent		= parent;
		this.iter		= this.collection.iterator();
	}
	
	public AbstractExpectedIterator<P, T, E> size( int expected ){
		Assert.assertEquals( expected, collection.size() );
		return this;
	}
	
	public E next(){
		return createNext( iter.next() );
	}
	
	public E next( String key ){
		return createNext( iter.next() );
	}
	
	protected abstract E createNext( T ent );
	
	public P parent(){
		return parent;
	}
}
