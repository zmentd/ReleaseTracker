package com.fdc.boarding.releasetracker.idea;

import java.util.HashMap;
import java.util.Map;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.registry.annotation.Register;
import com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaUC;

import fdinet.core.Processor;
import fdinet.core.TypedResponseHandler;

@Register
public class IdeaServiceImpl extends IdeaService {
	private final LoggerProxy 			logger		= LoggerProxy.getLogger();
	
	public IdeaServiceImpl(
	) 
	{
		super( "Idea", Processor.NULL_PROCESSOR );
		Map<String, Object> 			defaults;

		defaults = new HashMap<>();
		defaults.put( "sName", getServiceName() );
		defaults.put( "sVer", getServiceVersion() );
	}

	@Override
	public void findAssignedTo(AssignedToRequest request, TypedResponseHandler<IdeaListResponse> handler) {
		IdeaUC						usecase;
		IdeaListResponse			response;
		
		logger.debug( "Entering findAssignedTo." );
		usecase		= CDIContext.getInstance().getBean( IdeaUC.class );
		response	= usecase.findAssignedTo( request );
		handler.handleResponse( response );
		logger.debug( "Exiting findAssignedTo." );
	}

	@Override
	public void searchIdeas(IdeaSearchRequest request, TypedResponseHandler<IdeaListPartialResponse> handler) {
		IdeaUC						usecase;
		IdeaListPartialResponse		response;
		
		logger.debug( "Entering searchIdeas." );
		usecase		= CDIContext.getInstance().getBean( IdeaUC.class );
		response	= usecase.searchIdeas( request );
		handler.handleResponse( response );
		logger.debug( "Exiting searchIdeas." );
	}

}
