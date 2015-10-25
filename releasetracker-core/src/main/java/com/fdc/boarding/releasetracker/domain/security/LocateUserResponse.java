package com.fdc.boarding.releasetracker.domain.security;

import java.util.List;

import com.fdc.boarding.releasetracker.domain.AbstractResponse;

public class LocateUserResponse extends AbstractResponse {
	private static final long 			serialVersionUID 	= 1L;

	private List<IUser>					users;

	public LocateUserResponse(){
		super();
	}

	public List<IUser> getUsers() {
		return users;
	}
	
	public void setUsers(List<IUser> users) {
		this.users = users;
	}
}
