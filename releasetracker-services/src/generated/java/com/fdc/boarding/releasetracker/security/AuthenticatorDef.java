package com.fdc.boarding.releasetracker.security;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="authenticator", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface AuthenticatorDef {

    @FDiNetWebInfo(path="/authenticate", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Authenticate the user.")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse.class)
	void authenticate(com.fdc.boarding.releasetracker.security.Credentials request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse> handler);

}
