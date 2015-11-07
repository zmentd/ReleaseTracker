package com.fdc.boarding.releasetracker.security;

import com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="authenticator" )
@FDiNetService( version = 1 )
public interface Authenticator {
    @FDiNetServiceAPI_RR( responseType = AuthenticationResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/authenticate")
    @Description( "Authenticate the user." )
	public void authenticate( Credentials credentials, TypedResponseHandler<AuthenticationResponse> handler );

}
