package com.fdc.boarding.releasetracker.security;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class RegistrarService extends FDiNetServiceBase implements RegistrarDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public RegistrarService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public RegistrarService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("register",new ServiceApiHandler<com.fdc.boarding.releasetracker.domain.security.RegistrationRequest>("register", com.fdc.boarding.releasetracker.domain.security.RegistrationRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				register((com.fdc.boarding.releasetracker.domain.security.RegistrationRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.domain.security.RegistrationResponse>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
