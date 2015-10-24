package com.fdc.boarding.releasetracker.domain.team;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.fdc.boarding.core.util.AssertUtils;

public class TeamsUC implements Serializable {
	private static final long serialVersionUID = 1L;

	private TeamsRequest				request;
	
	@Inject 
	private ITeamPersistenceGateway	gateway;
	
	private ListTeamResponse buildResponseWithAllTeams(TeamsRequest request) {

		List<ITeam> fullListOfWidgets;
		
		if( request.getOrderBy() != null ){
			fullListOfWidgets = gateway.findAllTeams( request.getOrderBy(), request.isAsc());
		}
		else{
			fullListOfWidgets = gateway.findAllTeams();
		}

		ListTeamResponse widgetsResponse = new ListTeamResponse();
		widgetsResponse.setList(fullListOfWidgets);

		Long size = Long.valueOf(fullListOfWidgets.size());
		widgetsResponse.setCount(size);
		return widgetsResponse;
	}

	private ListTeamResponse buildResponseWithPageOfTeams(){
		List<ITeam>	page = gateway.findAllTeams();
		Long 			count = gateway.getCountOfTeams();
		ListTeamResponse	response = new ListTeamResponse();
		response.setList(page);
		response.setCount(count);
		
		return response; 
	}

	
	private boolean paginationInputsAreValid() {

		boolean pageCountIsValid = AssertUtils.isPositiveInteger(request.getCountPerPage());
		boolean pageNumberIsValid = AssertUtils.isPositiveInteger(request.getPage());

		boolean valid = pageCountIsValid && pageNumberIsValid;

		return valid;

	}

	public ListTeamResponse retrieveTeams( TeamsRequest request ){
		this.request	= request;
		
		boolean retrieveFullList = !paginationInputsAreValid();
		
		if( retrieveFullList ){
			ListTeamResponse response = buildResponseWithAllTeams(request);
			
			return response;
		}
		
		ListTeamResponse response = buildResponseWithPageOfTeams();
		
		return response;
	}


}
