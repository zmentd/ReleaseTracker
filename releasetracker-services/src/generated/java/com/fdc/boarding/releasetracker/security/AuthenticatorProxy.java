package com.fdc.boarding.releasetracker.security;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class AuthenticatorProxy extends FDiNetServiceProxyBase implements AuthenticatorDef {

	public AuthenticatorProxy() throws FDiNetException {
		this("authenticator", EncodingType.JSON, 1);
	}

	public AuthenticatorProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public AuthenticatorProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void authenticate(com.fdc.boarding.releasetracker.security.Credentials request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse> handler) {
		invokeRR("authenticate", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse.class, false);
	}
	
}
