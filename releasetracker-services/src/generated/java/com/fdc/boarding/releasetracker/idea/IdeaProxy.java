package com.fdc.boarding.releasetracker.idea;

import fdinet.core.*;
import fdinet.core.TypeSerializer.EncodingType;

/*
* GENERATED CODE, DO NOT TOUCH !
*/
public final class IdeaProxy extends FDiNetServiceProxyBase implements IdeaDef {

	public IdeaProxy() throws FDiNetException {
		this("idea", EncodingType.JSON, 1);
	}

	public IdeaProxy(String serviceName) throws FDiNetException {
		this(serviceName, EncodingType.JSON, 1);
	}

	public IdeaProxy(String serviceName, EncodingType enc, int version) throws FDiNetException {
		super(serviceName, enc, version);
	}

	@Override
	public final void searchIdeas(com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse> handler) {
		invokeRR("searchIdeas", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse.class, false);
	}
	
	@Override
	public final void findAssignedTo(com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse> handler) {
		invokeRR("findAssignedTo", request, 10000, handler, com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse.class, false);
	}
	
}
