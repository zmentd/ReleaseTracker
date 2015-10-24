package com.fdc.boarding.releasetracker.domain.team;

import java.io.Serializable;
import java.util.List;


public interface ITeamPersistenceGateway extends Serializable {
	enum OrderBy{
		Id,
		Name,
		Obs
	}
	public void addTeam( ITeam team );
	public List<ITeam> findAllTeams();
	public List<ITeam> findAllTeams( OrderBy orderBy, boolean isAsc );
	public void modifyTeam( ITeam team );
	public void removeTeam( ITeam team );
	public List<ITeam> findTeams( int page, int countPerPage, OrderBy orderBy, boolean isAsc );
	public Long getCountOfTeams();
}
