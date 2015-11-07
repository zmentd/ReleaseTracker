package com.fdc.boarding.releasetracker.security;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.annotation.Register;
import com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse;
import com.fdc.boarding.releasetracker.usecase.security.UserUC;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;

@Register
public class AuthenticatorServiceImpl extends AuthenticatorService{
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public AuthenticatorServiceImpl(
	) 
	{
		super( "Authenticator", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void authenticate( Credentials request, TypedResponseHandler<AuthenticationResponse> handler) {
		UserUC							usecase;
		AuthenticationResponse			response;
		
		logger.debug( "Entering authenticate." );
		usecase		= CDIContext.getInstance().getBean( UserUC.class );
		response	= usecase.authenticateUser( request );
		handler.handleResponse( response );
		logger.debug( "Exiting authenticate." );
	}

}
