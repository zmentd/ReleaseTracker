package com.fdc.boarding.releasetracker.team;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.team.ListTeamResponse;
import com.fdc.boarding.releasetracker.domain.team.TeamRequest;
import com.fdc.boarding.releasetracker.domain.team.TeamResponse;
import com.fdc.boarding.releasetracker.domain.team.TeamUC;
import com.fdc.boarding.releasetracker.domain.team.TeamsRequest;
import com.fdc.boarding.releasetracker.registry.annotation.Register;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;

@Register
public class TeamServiceImpl extends TeamService {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public TeamServiceImpl(
	) 
	{
		super( "Team", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void addTeam(TeamRequest request, TypedResponseHandler<TeamResponse> handler) {
		TeamUC						usecase;
		TeamResponse				response;
		
		logger.debug( "Entering addTeam." );
		usecase		= CDIContext.getInstance().getBean( TeamUC.class );
		response	= usecase.addTeam( request );
		handler.handleResponse( response );
		logger.debug( "Exiting addTeam." );
	}

	@Override
	public void getAllTeams(TeamsRequest request, TypedResponseHandler<ListTeamResponse> handler) {
		TeamUC						usecase;
		ListTeamResponse			response;
		
		logger.debug( "Entering getAllTeams." );
		usecase		= CDIContext.getInstance().getBean( TeamUC.class );
		response	= usecase.retrieveTeams( request );
		handler.handleResponse( response );
		logger.debug( "Exiting getAllTeams." );
	}

	@Override
	public void removeTeam(TeamRequest request, TypedResponseHandler<TeamResponse> handler) {
		TeamUC						usecase;
		TeamResponse				response;
		
		logger.debug( "Entering removeTeam." );
		usecase		= CDIContext.getInstance().getBean( TeamUC.class );
		response	= usecase.removeTeam( request );
		handler.handleResponse( response );
		logger.debug( "Exiting removeTeam." );
	}

	@Override
	public void updateTeam(TeamRequest request, TypedResponseHandler<TeamResponse> handler) {
		TeamUC						usecase;
		TeamResponse				response;
		
		logger.debug( "Entering updateTeam." );
		usecase		= CDIContext.getInstance().getBean( TeamUC.class );
		response	= usecase.updateTeam( request );
		handler.handleResponse( response );
		logger.debug( "Exiting updateTeam." );
	}
}
