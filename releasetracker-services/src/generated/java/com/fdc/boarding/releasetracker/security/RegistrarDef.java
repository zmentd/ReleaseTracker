package com.fdc.boarding.releasetracker.security;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="registrar", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface RegistrarDef {

    @FDiNetWebInfo(path="/register", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Register a user.")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.domain.security.RegistrationResponse.class)
	void register(com.fdc.boarding.releasetracker.domain.security.RegistrationRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.security.RegistrationResponse> handler);

}
