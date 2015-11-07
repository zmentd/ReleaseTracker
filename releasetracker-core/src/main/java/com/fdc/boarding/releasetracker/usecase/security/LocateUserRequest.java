package com.fdc.boarding.releasetracker.usecase.security;

import com.fdc.boarding.core.query.AbstractAp;

public class LocateUserRequest extends AbstractAp {
	private static final long 			serialVersionUID	= 1L;

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
