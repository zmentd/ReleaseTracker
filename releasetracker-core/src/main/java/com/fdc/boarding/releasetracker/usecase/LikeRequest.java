package com.fdc.boarding.releasetracker.usecase;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class LikeRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	@NotNull
	private String						searchValue;
	
	public LikeRequest(){
		super();
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
