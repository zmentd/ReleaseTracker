package com.fdc.boarding.releasetracker.domain.idea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class IdeaSearchResponse {
	
	private SortedMap<String, IdeaSearchGroup>	groupedIdeas;
	private List<IdeaStatusResponse>			ideas;
	private Map<Long, IdeaStatusResponse>		ideasById;

	public IdeaSearchResponse() {
		super();
		ideas			= new ArrayList<>();
		groupedIdeas	= new TreeMap<>();
		ideasById		= new HashMap<>();
	}

	public void addIdeaStatus( IdeaStatusResponse response ){
		ideas.add( response );
	}

	public void addIdeaStatus( String group, IdeaStatusResponse response ){
		String						name;
		IdeaSearchGroup			searchGroup;
		
		name	= group;
		if( name == null || name.isEmpty() ){
			name	= "Unassigned";
		}
			
		if( !groupedIdeas.containsKey( name ) )
		{
			searchGroup	= new IdeaSearchGroup();
			groupedIdeas.put( name, searchGroup );
		}
		groupedIdeas.get( name ).addStatusResponse( response );
		ideasById.put( response.getWorkflow().getId(), response );
	}

	public IdeaStatusResponse getById( Long id ){
		return ideasById.get( id );
	}
	
	public List<IdeaStatusResponse> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<IdeaStatusResponse> ideas) {
		this.ideas = ideas;
	}

	public SortedMap<String, IdeaSearchGroup> getGroupedIdeas() {
		return groupedIdeas;
	}
}
