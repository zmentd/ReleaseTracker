package com.fdc.boarding.releasetracker.gateway.security;

import java.io.Serializable;

import javax.inject.Inject;

import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.releasetracker.domain.security.IUserPersistenceGateway;
import com.fdc.boarding.releasetracker.domain.security.Identity;
import com.fdc.boarding.releasetracker.gateway.security.exception.AuthenticationException;
import com.fdc.boarding.releasetracker.persistence.security.AuthenticatedUserEntity;

public class AuthenticationHandler implements Serializable{
	private static final long 			serialVersionUID 	= 1L;
	
	@Inject
	private IUserPersistenceGateway		gateway;
	
	public AuthenticationHandler(){
		super();
	}
	
	@Transactional
	public void authenticate( Identity identity ){
		AuthenticatedUserEntity			user	= null;

		if( identity.getUserId() != null && identity.getPassword() != null ){
			user	= ( AuthenticatedUserEntity )gateway.findByUserId( identity.getUserId() );
			if( user != null ){
				if( user.getPassword().equals( identity.getPassword() ) ){
					identity.setLoggedIn( true );
					identity.setFirstName( user.getUser().getFirstName() );
					identity.setLastName( user.getUser().getLastName() );
					identity.setAuthenticatedUserId( user.getId() );
					identity.setUserpk( user.getUser().getId() );
					identity.setPreferences( user.getUser().getPreferences() );
				}
				else{
					throw new AuthenticationException( "Loging Failed.");
				}
			}
			else{
				throw new AuthenticationException( "Loging Failed.");
			}
		}
		
	}
}
