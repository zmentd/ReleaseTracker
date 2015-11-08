package com.fdc.boarding.releasetracker.usecase.idea;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;

public class IdeaListPartialResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private List<IdeaPartialDto>		list;

	public IdeaListPartialResponse(){
		super();
	}

	public List<IdeaPartialDto> getList() {
		return list;
	}

	public void setList(List<IdeaPartialDto> list) {
		this.list = list;
	}
}
