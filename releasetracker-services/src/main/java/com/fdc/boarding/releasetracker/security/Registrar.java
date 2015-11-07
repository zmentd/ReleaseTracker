package com.fdc.boarding.releasetracker.security;

import com.fdc.boarding.releasetracker.usecase.security.RegistrationRequest;
import com.fdc.boarding.releasetracker.usecase.security.RegistrationResponse;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="registrar" )
@FDiNetService( version = 1 )
public interface Registrar {
	
    @FDiNetServiceAPI_RR( responseType = RegistrationResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/register")
    @Description( "Register a user." )
	public void register( RegistrationRequest request, TypedResponseHandler<RegistrationResponse> handler );

}
