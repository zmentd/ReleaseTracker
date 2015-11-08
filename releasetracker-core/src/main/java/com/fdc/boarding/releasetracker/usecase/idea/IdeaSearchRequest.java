package com.fdc.boarding.releasetracker.usecase.idea;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.usecase.AbstractRequest;

public class IdeaSearchRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	private String						searchValue;
	
	public IdeaSearchRequest(){
		super();
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
