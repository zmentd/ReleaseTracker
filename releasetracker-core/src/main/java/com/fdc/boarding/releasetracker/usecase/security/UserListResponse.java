package com.fdc.boarding.releasetracker.usecase.security;

import java.util.List;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class UserListResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private List<UserDto>				list;
	
	public UserListResponse(){
		super();
	}

	public List<UserDto> getList() {
		return list;
	}

	public void setList(List<UserDto> list) {
		this.list = list;
	}
}
