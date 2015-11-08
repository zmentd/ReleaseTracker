package com.fdc.boarding.releasetracker.idea;

import java.util.*;
import java.io.IOException;

import fdinet.core.*;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public abstract class IdeaService extends FDiNetServiceBase implements IdeaDef {

	private Map<String, ServiceApiHandler<?>> handlers = new HashMap<>();
		
	public IdeaService(String serviceName) {
		this(serviceName, Processor.NULL_PROCESSOR);
	}

	public IdeaService(String serviceName, Processor processor) {
		super(serviceName, processor);
		handlers.put("searchIdeas",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest>("searchIdeas", com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				searchIdeas((com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse>)r);
			}
		});

		handlers.put("findAssignedTo",new ServiceApiHandler<com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest>("findAssignedTo", com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest.class) {
			@SuppressWarnings("unchecked")
			@Override
			protected void processRequest(Object request, TypedResponseHandler<?> r) {
				findAssignedTo((com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest) request, (TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse>)r);
			}
		});

		setProcessors(handlers);
	}

	protected final java.lang.String getServiceConfiguration() throws Result.ResultException, IOException {
		return getServiceConfiguration(java.lang.String.class);
	}
}
