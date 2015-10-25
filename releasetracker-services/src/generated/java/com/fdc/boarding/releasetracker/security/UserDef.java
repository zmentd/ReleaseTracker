package com.fdc.boarding.releasetracker.security;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="user", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface UserDef {

    @FDiNetWebInfo(path="/locateUsers", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("locate users by prefix")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.domain.security.LocateUserResponse.class)
	void locateUsers(com.fdc.boarding.releasetracker.domain.security.LocateUserRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.security.LocateUserResponse> handler);

}
