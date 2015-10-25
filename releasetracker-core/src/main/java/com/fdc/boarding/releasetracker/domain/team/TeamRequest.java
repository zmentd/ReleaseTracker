package com.fdc.boarding.releasetracker.domain.team;

import com.fdc.boarding.releasetracker.persistence.team.TeamEntity;

public class TeamRequest {
	private TeamEntity					team;
	

	public TeamRequest(){
		super();
	}

	public TeamEntity getTeam() {
		return team;
	}
	
	public void setTeam(TeamEntity team) {
		this.team = team;
	}
}
