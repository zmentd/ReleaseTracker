package com.fdc.boarding.releasetracker.security;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class UserService extends FDiNetServiceBase implements UserDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public UserService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public UserService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("locateUsers",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.security.LocateUserRequest>("locateUsers", com.fdc.boarding.releasetracker.usecase.security.LocateUserRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				locateUsers((com.fdc.boarding.releasetracker.usecase.security.LocateUserRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.security.LocateUserResponse>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
