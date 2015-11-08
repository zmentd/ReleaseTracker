package com.fdc.boarding.releasetracker.team;

import com.fdc.boarding.releasetracker.usecase.LikeRequest;
import com.fdc.boarding.releasetracker.usecase.team.ListTeamResponse;
import com.fdc.boarding.releasetracker.usecase.team.TeamRequest;
import com.fdc.boarding.releasetracker.usecase.team.TeamResponse;
import com.fdc.boarding.releasetracker.usecase.team.TeamsRequest;

import fdinet.core.TypedResponseHandler;
import fdinet.core.annotations.Description;
import fdinet.core.annotations.FDiNetService;
import fdinet.core.annotations.FDiNetServiceAPI_RR;
import fdinet.core.annotations.FDiNetWebInfo;

@FDiNetWebInfo( path="team" )
@FDiNetService( version = 1 )
public interface Team {
	
    @FDiNetServiceAPI_RR( responseType = ListTeamResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/getAllTeams")
    @Description( "Retrieve all teams" )
	public void getAllTeams( TeamsRequest request, TypedResponseHandler<ListTeamResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = TeamResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/addTeam")
    @Description( "Add a new Team" )
	public void addTeam( TeamRequest request, TypedResponseHandler<TeamResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = TeamResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/updateTeam")
    @Description( "Update a Team" )
	public void updateTeam( TeamRequest request, TypedResponseHandler<TeamResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = TeamResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/removeTeam")
    @Description( "Remove a Team" )
	public void removeTeam( TeamRequest request, TypedResponseHandler<TeamResponse> handler );
	
    @FDiNetServiceAPI_RR( responseType = ListTeamResponse.class, timeoutMillis=10000 )
    @FDiNetWebInfo(path="/findTeams")
    @Description( "Find teams like the name given" )
	public void findTeams( LikeRequest request, TypedResponseHandler<ListTeamResponse> handler );

}
