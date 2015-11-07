package com.fdc.boarding.releasetracker.usecase.team;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.team.dto.TeamDto;

public class TeamResponse extends AbstractResponse{
	private static final long 			serialVersionUID	= 1L;

	private TeamDto						team;

	public TeamResponse(){
		super();
	}

	public TeamResponse(TeamDto team) {
		super();
		this.team = team;
	}

	public TeamDto getTeam() {
		return team;
	}
	
	public void setTeam(TeamDto team) {
		this.team = team;
	}
	
}
