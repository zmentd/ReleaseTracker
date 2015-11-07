package com.fdc.boarding.releasetracker.security;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.annotation.Register;
import com.fdc.boarding.releasetracker.usecase.security.LocateUserRequest;
import com.fdc.boarding.releasetracker.usecase.security.LocateUserResponse;
import com.fdc.boarding.releasetracker.usecase.security.UserUC;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;

@Register
public class UserServiceImpl extends UserService {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public UserServiceImpl(
	) 
	{
		super( "User", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void locateUsers(LocateUserRequest request, TypedResponseHandler<LocateUserResponse> handler) {
		UserUC							usecase;
		LocateUserResponse				response;
		
		logger.debug( "Entering locateUsers." );
		usecase		= CDIContext.getInstance().getBean( UserUC.class );
		response	= usecase.locateUsers( request );
		handler.handleResponse( response );
		logger.debug( "Exiting locateUsers." );
	}

}
