package com.fdc.boarding.releasetracker.domain.team;

import com.fdc.boarding.releasetracker.domain.AbstractRequest;
import com.fdc.boarding.releasetracker.domain.team.dto.TeamDto;


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
