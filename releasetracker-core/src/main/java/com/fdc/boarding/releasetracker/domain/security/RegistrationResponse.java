package com.fdc.boarding.releasetracker.domain.security;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.domain.AbstractResponse;

public class RegistrationResponse extends AbstractResponse implements Serializable {
	private static final long 			serialVersionUID 		= 1L;

	private IUser						user;
	
	public RegistrationResponse() {
		super();
	}
	
	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}
}
