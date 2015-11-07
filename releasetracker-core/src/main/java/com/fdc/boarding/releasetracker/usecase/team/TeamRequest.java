package com.fdc.boarding.releasetracker.usecase.team;

import com.fdc.boarding.releasetracker.usecase.AbstractRequest;
import com.fdc.boarding.releasetracker.usecase.team.dto.TeamDto;


public class TeamRequest extends AbstractRequest {
	private TeamDto						team;
	

	public TeamRequest(){
		super();
	}

	public TeamDto getTeam() {
		return team;
	}
	
	public void setTeam( TeamDto team) {
		this.team = team;
	}
}
