package com.fdc.boarding.releasetracker.idea;

import fdinet.core.*;
import fdinet.core.annotations.*;
/*
* GENERATED CODE, DO NOT TOUCH !
*/
@FDiNetService(version=1, configType = java.lang.String.class)
@FDiNetWebInfo(path="idea", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
@Description("")
public interface IdeaDef {

    @FDiNetWebInfo(path="/searchIdeas", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("search ideas by key word")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse.class)
	void searchIdeas(com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse> handler);

    @FDiNetWebInfo(path="/findAssignedTo", maxContentSize=200000, methods={WebDef.WebMethod.POST,})
    @Description("locate ideas by assigned to")
	@FDiNetServiceAPI_RR(timeoutMillis=10000, instanceOnly=true, responseType=com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse.class)
	void findAssignedTo(com.fdc.boarding.releasetracker.usecase.idea.AssignedToRequest request, TypedResponseHandler<com.fdc.boarding.releasetracker.usecase.idea.IdeaListResponse> handler);

}
