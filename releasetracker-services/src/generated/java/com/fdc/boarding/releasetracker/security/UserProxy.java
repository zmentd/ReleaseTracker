package com.fdc.boarding.releasetracker.security;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class UserProxy extends FDiNetServiceProxyBase implements UserDef {

	public UserProxy() throws FDiNetException {
		this("user", EncodingType.JSON, 1);
	}

	public UserProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public UserProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void locateUsers(com.fdc.boarding.releasetracker.domain.security.LocateUserRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.security.LocateUserResponse> handler) {
		invokeRR("locateUsers", request, 10000, handler, com.fdc.boarding.releasetracker.domain.security.LocateUserResponse.class, false);
	}
	
}
