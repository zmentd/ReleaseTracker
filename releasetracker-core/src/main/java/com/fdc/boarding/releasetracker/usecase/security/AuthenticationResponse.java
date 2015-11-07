package com.fdc.boarding.releasetracker.usecase.security;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class AuthenticationResponse extends AbstractResponse implements Serializable {
	private static final long 			serialVersionUID 		= 1L;
	
	private UserDto						user;
	
	public AuthenticationResponse() {
		super();
	}
	
	public AuthenticationResponse(boolean success, String message) {
		super(success, message);
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser( UserDto user) {
		this.user = user;
	}
}
