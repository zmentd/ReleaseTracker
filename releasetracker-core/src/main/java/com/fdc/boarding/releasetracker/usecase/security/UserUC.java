package com.fdc.boarding.releasetracker.usecase.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fdc.boarding.core.intercept.annotation.ServiceCall;
import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.core.query.Conjunction;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.EntityReaderSvc;
import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.security.IAuthenticatedUser;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.gateway.security.IUserPersistenceGateway;
import com.fdc.boarding.releasetracker.persistence.security.AuthenticatedUserEntity;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;
import com.fdc.boarding.releasetracker.security.Credentials;
import com.fdc.boarding.releasetracker.usecase.ValidationFailure;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class UserUC {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();

	@Inject
	private EntityPersistenceService	service;

	@Inject
	private IUserPersistenceGateway		gateway;
	
	@Inject
	private EntityReaderSvc				reader;
	
	@ServiceCall
	@Transactional
	public AuthenticationResponse authenticateUser( Credentials credentials ){
		boolean							authenticated	= true;
		Identity						identity;
		EntityReaderSvc					reader;
		AuthenticationResponse			response;
		IUser							user;
		
		logger.debug( "Entering authenticate." );
		try {
			identity	= CDIContext.getInstance().getBean( Identity.class );
			reader		= CDIContext.getInstance().getBean( EntityReaderSvc.class );
			identity.setUserId( credentials.getUserId() );
			identity.setPassword( credentials.getPassword() );
			identity.login();
			authenticated	= identity.isLoggedIn();
			if( authenticated ){
				response	= new AuthenticationResponse( true, "User authenticated" );
				user		= reader.findByKey( UserEntity.class, identity.getUserpk(), "preferences" );
				response.setUser( UserDto.from( user ) );
			}
			else{
				response	= new AuthenticationResponse( false, "Authentication failed" );
			}
		} catch (Exception e) {
			e.printStackTrace();
			response		= new AuthenticationResponse( false, "Authentication failed" );
		}
		logger.debug( "Exiting authenticate." );
		
		return response;
	}
	
	@ServiceCall
	public LocateUserResponse locateUsers( LocateUserRequest request ){
		List<IUser> 					results	= null;
		List<UserDto> 					list	= null;
		LocateUserResponse				response;

		response	= new LocateUserResponse();
		try{
			results	= reader.find( IUser.class, "firstName", true, Conjunction.or( Restriction.ilike( "firstName", request.getNamePrefix() ) 
																				 , Restriction.ilike( "lastName", request.getNamePrefix() )
																				 )
			);
			list	= new ArrayList<>();
			for( IUser user : results ){
				list.add( UserDto.from( user ) );
			}
			response.setUsers( list );
			response.setMessage( "User registered." );
			response.setSuccess( true );
		} 
		catch( QueryException e ) {
			e.printStackTrace();
			response.setMessage( "Unable to locate users." );
			response.setSuccess( false );
		}

		return response;
	}
	
	protected void registerUser( IAuthenticatedUser auth, IUser user ){
		service.addChild( auth, user, "user" );
	}
	
	@ServiceCall
	@Transactional
	public RegistrationResponse registerUser( RegistrationRequest request ){
		IUser											user	= null;
		IAuthenticatedUser								auth;
		RegistrationResponse							response;
		ValidationFailure								vf;
		
		response	= new RegistrationResponse();
		if( !request.getConfirmPassword().equals( request.getPassword() ) ){
			vf	= new ValidationFailure();	
			vf.setMessage( "The password does not match the confirm password." );
			vf.setPath( "password" );
			vf.setValidator( "password.confirmed" );
			response.addValidationFailure( vf );
			response.setMessage( "Validation failed." );
			response.setSuccess( false );
		}
		else{
			auth	= gateway.findByUserId( request.getUsername() );
			if( auth == null ){
				user	= gateway.findByEmail( request.getEmail() );
				if( user == null ){
					user	= new UserEntity();
				}
	
				user.setEmail( request.getEmail() );
				user.setFirstName( request.getFirstName() );
				user.setLastName( request.getLastName() );

				auth	= new AuthenticatedUserEntity();
				auth.setPassword( request.getPassword() );
				auth.setUserId( request.getUsername() );
				
				if( service.addChild( auth, user, "user" ) ){
					response.setUser( UserDto.from( user ) );
					response.setMessage( "User registered." );
					response.setSuccess( true );
				}
				else{
					response.setMessage( "User registration failed." );
					response.setSuccess( false );
				}
			}
			else{
				response.setMessage( "Username unavailable." );
				response.setSuccess( false );
			}
		}
		
		return response;
	}
}
