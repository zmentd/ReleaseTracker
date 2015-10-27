package com.fdc.boarding.releasetracker.domain.team;


public class TeamRequest {
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
