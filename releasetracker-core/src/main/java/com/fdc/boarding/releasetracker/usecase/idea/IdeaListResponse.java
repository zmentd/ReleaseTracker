package com.fdc.boarding.releasetracker.usecase.idea;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.idea.dto.IdeaDto;

public class IdeaListResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;
	
	private List<IdeaDto>				ideas;
	
	public IdeaListResponse(){
		super();
	}

	public List<IdeaDto> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<IdeaDto> ideas) {
		this.ideas = ideas;
	}
}
