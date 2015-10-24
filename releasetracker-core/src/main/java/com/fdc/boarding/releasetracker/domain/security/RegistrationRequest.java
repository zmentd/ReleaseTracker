package com.fdc.boarding.releasetracker.domain.security;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationRequest implements Serializable {
	private static final long 	serialVersionUID 		= 1L;

	@NotNull
	@NotEmpty
	private String				firstName;

	@NotNull
	@NotEmpty
	private String				lastName;
	
	@NotNull
	@Email
	private String				email;
	
	@NotNull
	@NotEmpty
	private String				username;
	
	
	@NotNull
	@Length( min = 6 )
	private String				password;
	
	@NotNull
	private String				confirmPassword;
	
	public RegistrationRequest() {
		super();
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public String getEmail() {
		return email;
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
	
	public String getUsername() {
		return username;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

}
