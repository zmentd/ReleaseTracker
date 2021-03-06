package com.fdc.boarding.releasetracker.usecase.security;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class LocateUserResponse extends AbstractResponse {
	private static final long 			serialVersionUID 	= 1L;

	private List<UserDto>				users;

	public LocateUserResponse(){
		super();
	}

	public List<UserDto> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
}
