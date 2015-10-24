package com.fdc.boarding.releasetracker.persistence.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.domain.team.ITeamPersistenceGateway;

public class TeamPersistenceGatewayImpl extends GenericDao<TeamEntity, Long> implements ITeamPersistenceGateway	 {
	private static final long 			serialVersionUID = 1L;

	public TeamPersistenceGatewayImpl() {
		super(TeamEntity.class);
	}

	@Override
	public void addTeam(ITeam team) {
		persist( ( TeamEntity )team );
	}

	public List<ITeam> findTeams( int page, int countPerPage, OrderBy orderBy, boolean isAsc ){
		List<ITeam>	list;			
		String orderByPrefix = determineOrderByColumnName(orderBy);
		String orderBySuffix = isAsc ? "ASC" : "DESC";

		String jql = String.format("SELECT w FROM TeamEntity w ORDER BY w.%s %s", orderByPrefix, orderBySuffix);

		TypedQuery<TeamEntity> query = entityManager.createQuery(jql, TeamEntity.class);

		int firstResult = (page - 1) * countPerPage;
		query.setFirstResult(firstResult);
		query.setMaxResults(countPerPage);
		list	= new ArrayList<>();
		List<TeamEntity> results = query.getResultList();
		
		for( TeamEntity entity : results )
		{
			list.add( entity );
		}
		
		return list;
	}

	private String determineOrderByColumnName(OrderBy orderBy) {

		switch (orderBy) {
		case Name:
			return "name";
		case Id:
			return "id";
		case Obs:
			return "obs";
		default:
			throw new RuntimeException("This shouldn't be possible");
		}
	}

	@Override
	public Long getCountOfTeams() {

		Query query = entityManager.createQuery("SELECT COUNT(w) FROM TeamEntity w");

		Long singleResult = (Long) query.getSingleResult();

		return singleResult;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ITeam> findAllTeams() {
		List<? extends ITeam>		list;
		
		list	= findAll();
		
		return ( List<ITeam> )list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ITeam> findAllTeams( OrderBy orderBy, boolean isAsc ) {
		List<? extends ITeam>		list;
		
		list	= findAll( determineOrderByColumnName(orderBy), isAsc);
		
		return ( List<ITeam> )list;
	}

	@Override
	public void modifyTeam(ITeam team) {
		update( ( TeamEntity )team );
	}

	@Override
	public void removeTeam(ITeam team) {
		delete( ( TeamEntity )team );
	}
}
