package com.fdc.boarding.releasetracker.security;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class AuthenticatorService extends FDiNetServiceBase implements AuthenticatorDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public AuthenticatorService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public AuthenticatorService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("authenticate",new ServiceApiHandler<com.fdc.boarding.releasetracker.security.Credentials>("authenticate", com.fdc.boarding.releasetracker.security.Credentials.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				authenticate((com.fdc.boarding.releasetracker.security.Credentials) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.domain.security.AuthenticationResponse>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
