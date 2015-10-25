package com.fdc.boarding.releasetracker.domain.team;

import com.fdc.boarding.releasetracker.domain.AbstractResponse;

public class TeamResponse extends AbstractResponse{
	private static final long 			serialVersionUID	= 1L;

	private ITeam						team;

	public TeamResponse(){
		super();
	}

	public TeamResponse(ITeam team) {
		super();
		this.team = team;
	}

	public ITeam getTeam() {
		return team;
	}
	
	public void setTeam(ITeam team) {
		this.team = team;
	}
	
}
