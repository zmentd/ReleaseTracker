package com.fdc.boarding.releasetracker.security;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.security.RegistrationRequest;
import com.fdc.boarding.releasetracker.domain.security.RegistrationResponse;
import com.fdc.boarding.releasetracker.domain.security.UserUC;
import com.fdc.boarding.releasetracker.registry.annotation.Register;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;

@Register
public class RegistrarServiceImpl extends RegistrarService {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public RegistrarServiceImpl(
	) 
	{
		super( "Registrar", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void register( RegistrationRequest request, TypedResponseHandler<RegistrationResponse> handler ) {
		UserUC						usecase;
		RegistrationResponse		response;
		
		logger.debug( "Entering register." );
		usecase		= CDIContext.getInstance().getBean( UserUC.class );
		response	= usecase.registerUser( request );
		handler.handleResponse( response );
		logger.debug( "Exiting register." );
	}
}
