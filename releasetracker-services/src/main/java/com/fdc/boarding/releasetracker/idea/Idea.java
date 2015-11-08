package com.fdc.boarding.releasetracker.idea;

import com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="idea" )
@FDiNetService( version = 1 )
public interface Idea {
	
    @FDiNetServiceAPI_RR( responseType = IdeaListResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findAssignedTo")
    @Description( "locate ideas by assigned to" )
	public void findAssignedTo( AssignedToRequest request, TypedResponseHandler<IdeaListResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = IdeaListPartialResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/searchIdeas")
    @Description( "search ideas by key word" )
	public void searchIdeas( IdeaSearchRequest request, TypedResponseHandler<IdeaListPartialResponse> handler );

}
