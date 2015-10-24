package com.fdc.boarding.releasetracker.domain.team;

import java.io.Serializable;

import javax.inject.Inject;

import com.fdc.boarding.releasetracker.persistence.team.TeamEntity;

public class TeamUC implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private ITeamPersistenceGateway	gateway;

	public void createTeam( TeamRequest request )
	{
		ITeam						team;
		
		team	= new TeamEntity( request.getName(), request.getObs());
		gateway.addTeam(team);
	}

	public void addTeam( ITeam team )
	{
		gateway.addTeam(team);
	}

	public void updateTeam( ITeam team )
	{
		gateway.modifyTeam(team);
	}

	public void removeTeam( ITeam team )
	{
		gateway.removeTeam(team);
	}
}
