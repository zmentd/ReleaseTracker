package com.fdc.boarding.releasetracker.test.gateway.xcel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.service.IEntityReaderSvc;

public abstract class AbstractExpectedMap<P extends AbstractExpected, T extends IEntity<?>, E extends AbstractExpected> extends AbstractExpected{

	protected Collection<T> 			collection;
	protected P							parent;
	protected Map<String, T>			byName;
	
	public AbstractExpectedMap( IEntityReaderSvc reader, Collection<T> collection, P parent ){
		this.reader		= reader;
		this.collection	= collection;
		this.parent		= parent;
		byName			= new HashMap<>();
		for( T item : collection ){
			byName.put( mapKey( item ), item );
		}
	}
	
	public AbstractExpectedMap<P, T, E> size( int expected ){
		Assert.assertEquals( expected, collection.size() );
		return this;
	}
	
	public E with( String key ){
		return createNext( byName.get( key ) );
	}
	
	protected abstract E createNext( T ent );
	protected abstract String mapKey( T ent );
	
	public P parent(){
		return parent;
	}
}
