package com.fdc.boarding.releasetracker.security;

import javax.validation.constraints.NotNull;

public class Credentials {
	@NotNull
	private String						userId;
	@NotNull
	private String						password;
	
	public Credentials(){
		super();
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
