package com.fdc.boarding.releasetracker.security;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class RegistrarProxy extends FDiNetServiceProxyBase implements RegistrarDef {

	public RegistrarProxy() throws FDiNetException {
		this("registrar", EncodingType.JSON, 1);
	}

	public RegistrarProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public RegistrarProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void register(com.fdc.boarding.releasetracker.domain.security.RegistrationRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.security.RegistrationResponse> handler) {
		invokeRR("register", request, 10000, handler, com.fdc.boarding.releasetracker.domain.security.RegistrationResponse.class, false);
	}
	
}
