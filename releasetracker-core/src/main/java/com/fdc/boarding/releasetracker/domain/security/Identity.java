package com.fdc.boarding.releasetracker.domain.security;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.fdc.boarding.releasetracker.gateway.security.AuthenticationHandler;

@Named( "identity" )
public class Identity implements Serializable {
	private static final long 			serialVersionUID = 1L;
	
	private String						userId;
	private String						password;
	private String						firstName;
	private String						lastName;
	private Long						authenticatedUserId;
	private Long						userpk;
	private boolean						loggedIn				= false;
	private IUserPreferences			preferences;		
	
	@Inject
	private AuthenticationHandler		handler;

	
	public Identity(){
		super();
	}

	public void login(){
		handler.authenticate( this );
		password	= null;
	}

	public String logout(){
		loggedIn 			= false;
		userId				= null;
		userpk				= null;
		password			= null;
		firstName			= null;
		lastName			= null;
		authenticatedUserId	= null;
		preferences			= null;
		
		return "home";
	}

	public Long getAuthenticatedUserId() {
		return authenticatedUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserId() {
		return userId;
	}

	public Long getUserpk() {
		return userpk;
	}

	public IUserPreferences getPreferences() {
		return preferences;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setAuthenticatedUserId(Long authenticatedUserId) {
		this.authenticatedUserId = authenticatedUserId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserpk(Long userpk) {
		this.userpk = userpk;
	}

	public void setPreferences(IUserPreferences preferences) {
		this.preferences = preferences;
	}
}
