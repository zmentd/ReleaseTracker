package com.fdc.boarding.releasetracker.usecase.idea;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.idea.dto.IdeaDto;

public class IdeaResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private IdeaDto						idea;
	
	public IdeaResponse(){
		super();
	}

	public IdeaDto getIdea() {
		return idea;
	}

	public void setIdea(IdeaDto idea) {
		this.idea = idea;
	}
}
