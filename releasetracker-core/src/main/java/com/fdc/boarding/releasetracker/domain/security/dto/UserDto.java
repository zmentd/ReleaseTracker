package com.fdc.boarding.releasetracker.domain.security.dto;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.domain.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.domain.security.IUser;


public class UserDto extends AbstractAuditedDto implements Serializable {
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
