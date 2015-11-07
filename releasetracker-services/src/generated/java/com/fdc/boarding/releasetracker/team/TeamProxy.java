package com.fdc.boarding.releasetracker.team;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class TeamProxy extends FDiNetServiceProxyBase implements TeamDef {

	public TeamProxy() throws FDiNetException {
		this("team", EncodingType.JSON, 1);
	}

	public TeamProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public TeamProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void removeTeam(com.fdc.boarding.releasetracker.usecase.team.TeamRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.TeamResponse> handler) {
		invokeRR("removeTeam", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.team.TeamResponse.class, false);
	}
	
	@Override
	public final void updateTeam(com.fdc.boarding.releasetracker.usecase.team.TeamRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.TeamResponse> handler) {
		invokeRR("updateTeam", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.team.TeamResponse.class, false);
	}
	
	@Override
	public final void addTeam(com.fdc.boarding.releasetracker.usecase.team.TeamRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.TeamResponse> handler) {
		invokeRR("addTeam", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.team.TeamResponse.class, false);
	}
	
	@Override
	public final void getAllTeams(com.fdc.boarding.releasetracker.usecase.team.TeamsRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.ListTeamResponse> handler) {
		invokeRR("getAllTeams", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.team.ListTeamResponse.class, false);
	}
	
}
