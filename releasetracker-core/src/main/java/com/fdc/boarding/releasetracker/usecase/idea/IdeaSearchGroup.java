package com.fdc.boarding.releasetracker.usecase.idea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdeaSearchGroup {
	private int									count;
	private Map<DaysToTargetStatus, Integer>	statusCount;
	private List<IdeaStatusResponse>			statusResponses;
	
	public IdeaSearchGroup(){
		super();
		statusCount		= new HashMap<>();
		statusResponses	= new ArrayList<>();
	}

	public void addStatusResponse( IdeaStatusResponse response ){
		int								sc;
		DaysToTargetStatus				dts;
		
		count++;
		dts	= response.getStatus();
		if( dts != null ){
			if( !statusCount.containsKey( dts ) ){
				statusCount.put( dts, 0 );
			}
			sc	= statusCount.get( dts ) + 1;
			statusCount.put( dts, sc );
		}
		statusResponses.add( response );
	}
	
	public int getCount() {
		return count;
	}
	
	public int getStatusCount( String status ) {
		int								sc	= 0;
		DaysToTargetStatus				dts;
		
		dts	= DaysToTargetStatus.locate( status );
		if( dts != null && statusCount.containsKey( dts ) ){
			sc	= statusCount.get( dts );
		}
		
		return sc;
	}
	
	public int getStatusCount( DaysToTargetStatus status ) {
		return count;
	}

	public List<IdeaStatusResponse> getStatusResponses() {
		return statusResponses;
	}
}
