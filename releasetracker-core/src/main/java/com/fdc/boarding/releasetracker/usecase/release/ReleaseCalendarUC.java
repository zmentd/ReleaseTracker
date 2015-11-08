package com.fdc.boarding.releasetracker.usecase.release;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.gateway.release.IReleasePersistenceGateway;

public class ReleaseCalendarUC implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private IReleasePersistenceGateway	gateway;
	
	public List<Integer> findReleaseCalendarYears(){
		return gateway.determineReleaseYears();
	}
	
	public ReleaseCalendarResponse findReleaseCalendarForYear( ReleaseCalendarRequest request ){
		ReleaseCalendarResponse			response;
		List<IReleaseEntry>				list;

		response 	= new ReleaseCalendarResponse();
		list		= gateway.findReleaseCalendarForYear( request.getYear() );
		response.setReleaseEntries( list );
		response.setYear( request.getYear() );
		
		return response;
	}
	
	public ReleaseCalendarResponse findReleaseCalendarForYear( int year){
		ReleaseCalendarResponse			response;
		List<IReleaseEntry>				list;

		response 	= new ReleaseCalendarResponse();
		list		= gateway.findReleaseCalendarForYear(year);
		response.setReleaseEntries(list);
		response.setYear(year);
		
		return response;
	}
}
