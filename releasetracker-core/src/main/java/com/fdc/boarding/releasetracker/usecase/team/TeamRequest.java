package com.fdc.boarding.releasetracker.usecase.team;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.releasetracker.usecase.AbstractRequest;
import com.fdc.boarding.releasetracker.usecase.team.dto.TeamDto;


public class TeamRequest extends AbstractRequest {
	
	@NotNull
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
