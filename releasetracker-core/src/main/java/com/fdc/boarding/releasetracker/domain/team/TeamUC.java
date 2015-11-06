package com.fdc.boarding.releasetracker.domain.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fdc.boarding.core.query.EntityQuery;
import com.fdc.boarding.core.query.EntityQueryAp;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.EntityReaderSvc;
import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.AbstractUseCase;
import com.fdc.boarding.releasetracker.domain.team.dto.TeamDto;

public class TeamUC extends AbstractUseCase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityPersistenceService	service;
	
	@Inject
	private EntityReaderSvc				reader;

	@Inject 
	private EntityQuery					query;

	@Transactional
	public TeamResponse addTeam( TeamRequest request )
	{
		TeamResponse					response;
		ITeam							team;
		
		response	= new TeamResponse();
		try {
			team		= CDIContext.getInstance().getBean( ITeam.class );
			team.setName( request.getTeam().getName() );
			team.setObs( request.getTeam().getObs() );
			if( validate( team, response ) ){
				service.insert( team );
				response.setTeam( TeamDto.from( team ) );
				response.setSuccess( true );
				response.setMessage( "Team added." );
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to add team." );
		}
		
		return response;
	}

	@Transactional
	public TeamResponse removeTeam( TeamRequest request )
	{
		TeamResponse					response;
		ITeam							team;
		
		response	= new TeamResponse();
		try {
			if( request.getTeam() == null || request.getTeam().getId() == null ){
				response.setSuccess( false );
				response.setMessage( "No team given to delete." );
			}
			else{
				team		= reader.findByNaturalKey( ITeam.class, "id", request.getTeam().getId() );
				if( !team.getLastModifiedDate().equals( request.getTeam().getLastModifiedDate() ) ){
					response.setSuccess( false );
					response.setMessage( "Team updated by another user." );
				}
				else{
					service.delete( team );
					response.setTeam( TeamDto.from( team ) );
					response.setSuccess( true );
					response.setMessage( "Team deleted." );
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to update team." );
		}

		return response;
	}

	@Transactional
	public ListTeamResponse retrieveTeams( TeamsRequest request ){
		EntityQueryAp					ap;
		ListTeamResponse 				response;
		List<ITeam>						results;
		List<TeamResponse> 				list;
		int 							count;
		int 							first	= 0;
		
		ap			= new EntityQueryAp();
		ap.setEntityClass( ITeam.class );
		ap.setSort( request.getOrderBy().toLowerCase() );
		ap.setDescending( !request.isAsc() );
		response 	= new ListTeamResponse();
		
		try {
			count	= query.getCount( ap );
			list	= new ArrayList<>();
			if( count > 0 ){
				if( count > request.getCountPerPage() ){
					first	= ( ( request.getPage() * request.getCountPerPage() ) - request.getCountPerPage() );
				}
				results	= query.find( ap, first, request.getCountPerPage() );
				for( ITeam team : results ){
					list.add( new TeamResponse( TeamDto.from( team ) ) );	
				}
			}
			response.setList( list );
			response.setCount( Long.valueOf( count ) );
			response.setSuccess( true );
			response.setMessage( "Search successful." );
		} catch (QueryException e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Search failed." );
		}
		
		return response;
	}
	
	@Transactional
	public TeamResponse updateTeam( TeamRequest request )
	{
		TeamResponse					response;
		ITeam							team;
		
		response	= new TeamResponse();
		try {
			if( request.getTeam() == null || request.getTeam().getId() == null ){
				response.setSuccess( false );
				response.setMessage( "No team given to update." );
			}
			else{
				team		= reader.findByNaturalKey( ITeam.class, "id", request.getTeam().getId() );
				if( !team.getLastModifiedDate().equals( request.getTeam().getLastModifiedDate() ) ){
					response.setSuccess( false );
					response.setMessage( "Team updated by another user." );
				}
				else{
					team.setName( request.getTeam().getName() );
					team.setObs( request.getTeam().getObs() );
					if( validate( team, response ) ){
						service.update( team );
						response.setTeam( TeamDto.from( team ) );
						response.setSuccess( true );
						response.setMessage( "Team updated." );
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Unable to update team." );
		}
		
		return response;
	}

}
