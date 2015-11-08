package com.fdc.boarding.releasetracker.usecase.security;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.query.AbstractAp;

public class LocateUserRequest extends AbstractAp {
	private static final long 			serialVersionUID	= 1L;

	@NotNull
	private String						namePrefix;

	public LocateUserRequest(){
		super();
	}

	public String getNamePrefix() {
		return namePrefix;
	}
	
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
}
