package com.fdc.boarding.releasetracker.team;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class TeamService extends FDiNetServiceBase implements TeamDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public TeamService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public TeamService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("removeTeam",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.team.TeamRequest>("removeTeam", com.fdc.boarding.releasetracker.usecase.team.TeamRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				removeTeam((com.fdc.boarding.releasetracker.usecase.team.TeamRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.TeamResponse>)r);
			}
		});

		handlers.put("updateTeam",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.team.TeamRequest>("updateTeam", com.fdc.boarding.releasetracker.usecase.team.TeamRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				updateTeam((com.fdc.boarding.releasetracker.usecase.team.TeamRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.TeamResponse>)r);
			}
		});

		handlers.put("addTeam",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.team.TeamRequest>("addTeam", com.fdc.boarding.releasetracker.usecase.team.TeamRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				addTeam((com.fdc.boarding.releasetracker.usecase.team.TeamRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.TeamResponse>)r);
			}
		});

		handlers.put("getAllTeams",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.team.TeamsRequest>("getAllTeams", com.fdc.boarding.releasetracker.usecase.team.TeamsRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				getAllTeams((com.fdc.boarding.releasetracker.usecase.team.TeamsRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.team.ListTeamResponse>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
