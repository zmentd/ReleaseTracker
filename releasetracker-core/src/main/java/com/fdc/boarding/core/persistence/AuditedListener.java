package com.fdc.boarding.core.persistence;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditedListener {

//	@Inject 
	private ICurrentUserProvider		provider;
	
	public AuditedListener() {
		super();
	}

	@PreUpdate
	@PrePersist
	@SuppressWarnings( "rawtypes" )
	public void setLastModifiedUser( AbstractAuditedEntity entity ){
		if( provider == null || provider.getCurrentUserId() == null ){
			entity.setLastModifiedBy( "42" );
		}
		else{
			entity.setLastModifiedBy( provider.getCurrentUserId() );
		}
	}
}
