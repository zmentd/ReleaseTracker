package com.fdc.boarding.releasetracker;

import com.fdc.boarding.releasetracker.usecase.release.ReleaseCalendarRequest;
import com.fdc.boarding.releasetracker.usecase.release.ReleaseCalendarResponse;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="release" )
@FDiNetService( version = 1 )
public interface Release {
	
    @FDiNetServiceAPI_RR( responseType = ReleaseCalendarResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findReleaseCalendarForYear")
    @Description( "locate release calendar for a specified year." )
	public void findAssignedTo( ReleaseCalendarRequest request, TypedResponseHandler<ReleaseCalendarResponse> handler );

}
