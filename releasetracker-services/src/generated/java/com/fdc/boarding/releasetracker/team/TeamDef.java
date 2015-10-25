package com.fdc.boarding.releasetracker.team;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="team", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface TeamDef {

    @FDiNetWebInfo(path="/removeTeam", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Remove a Team")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.domain.team.TeamResponse.class)
	void removeTeam(com.fdc.boarding.releasetracker.domain.team.TeamRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.team.TeamResponse> handler);

    @FDiNetWebInfo(path="/updateTeam", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Update a Team")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.domain.team.TeamResponse.class)
	void updateTeam(com.fdc.boarding.releasetracker.domain.team.TeamRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.team.TeamResponse> handler);

    @FDiNetWebInfo(path="/addTeam", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Add a new Team")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.domain.team.TeamResponse.class)
	void addTeam(com.fdc.boarding.releasetracker.domain.team.TeamRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.team.TeamResponse> handler);

    @FDiNetWebInfo(path="/getAllTeams", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("Retrieve all teams")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.domain.team.ListTeamResponse.class)
	void getAllTeams(com.fdc.boarding.releasetracker.domain.team.TeamsRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.domain.team.ListTeamResponse> handler);

}
