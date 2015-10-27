package com.fdc.boarding.releasetracker.domain.security;

import java.io.Serializable;

import org.joda.time.DateTime;


public class UserDto implements Serializable {
	private static final long 	serialVersionUID = 1L;

	public static UserDto from( IUser user ){
		UserDto							dto	= null;
		
		if( user != null ){
			dto	= new UserDto();
			dto.setEmail( user.getEmail() );
			dto.setFirstName( user.getFirstName() );
			dto.setId( user.getId() );
			dto.setLastName( user.getLastName() );
			dto.setLastModifiedBy( user.getLastModifiedBy() );
			dto.setLastModifiedDate( user.getLastModifiedDate() );
		}
		
		return dto;
	}

	private Long						id;
	private String						firstName;
	private String						lastName;
	private String						email;
	private String						lastModifiedBy;
	private DateTime					lastModifiedDate;

	public UserDto(){
		super();
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public Long getId() {
		return id;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
