package com.fdc.boarding.releasetracker.usecase.idea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fdc.boarding.core.intercept.annotation.ServiceCall;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.gateway.idea.IIdeaPersistenceGateway;
import com.fdc.boarding.releasetracker.usecase.AbstractUseCase;
import com.fdc.boarding.releasetracker.usecase.idea.dto.IdeaDto;

public class IdeaUC extends AbstractUseCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private IEntityReaderSvc				reader;

    @Inject
    private IIdeaPersistenceGateway			gateway;

    @ServiceCall
    public IdeaSearchResponse findIdeas( IdeaAp ap ){
    	IdeaSearchResponse			response;
   
    	response	= gateway.findIdeas( ap );
		response.setSuccess( true );
		response.setMessage( "Operation successful." );

    	return response;
    }
    
    @ServiceCall
    public IdeaListResponse findAssignedTo( AssignedToRequest request ){
		List<IIdea>					ideas;
		List<IdeaDto>				dtos;
		IdeaListResponse			response;
		
		response	= new IdeaListResponse();
    	try{
			ideas	= reader.find( IIdea.class
									 , "priority"
									 , false
									 , Restriction.eq( "assignedTo.id", request.getAssignedToId() )
			);
			dtos	= new ArrayList<>();
			for( IIdea idea : ideas ){
				dtos.add( IdeaDto.from( idea ) );
			}
			response.setIdeas( dtos );
			response.setSuccess( true );
			response.setMessage( "Operation successful." );
		} 
    	catch( Exception e ){
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Search failed." );
		}
		
		return response;
    }
    
    @ServiceCall
    public IdeaListPartialResponse searchIdeas( IdeaSearchRequest request ){
    	IdeaListPartialResponse			response;
		List<IdeaPartialDto>			list;
		
		response	= new IdeaListPartialResponse();
		try{
			list	= gateway.searchIdeas( request.getSearchValue() );
			response.setList( list );
			response.setSuccess( true );
			response.setMessage( "Operation successful." );
		} 
		catch( Exception e ){
			e.printStackTrace();
			response.setSuccess( false );
			response.setMessage( "Search failed." );
		}
		
		return response;
   }
}
