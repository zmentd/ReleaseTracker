package com.fdc.boarding.releasetracker.security;

public class Credentials {
	private String						userId;
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
