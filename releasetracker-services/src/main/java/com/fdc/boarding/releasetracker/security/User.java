package com.fdc.boarding.releasetracker.security;

import com.fdc.boarding.releasetracker.domain.security.LocateUserRequest;
import com.fdc.boarding.releasetracker.domain.security.LocateUserResponse;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="user" )
@FDiNetService( version = 1 )
public interface User {
	
    @FDiNetServiceAPI_RR( responseType = LocateUserResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/locateUsers")
    @Description( "locate users by prefix" )
	public void locateUsers( LocateUserRequest request, TypedResponseHandler<LocateUserResponse> handler );

}
