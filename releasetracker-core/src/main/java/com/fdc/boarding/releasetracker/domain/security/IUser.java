package com.fdc.boarding.releasetracker.domain.security;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IUser extends IAuditable, IEntity<Long> {

	public String getEmail();
	public String getFirstName();
	public String getFullName();
	public Long getId();
	public byte[] getImageBytes();
	public String getJiraUserName();
	public String getLastName();
	public IUserPreferences getPreferences();
	public void setEmail(String email);
	public void setFirstName(String firstName);
	public void setId(Long id);
	public void setImageBytes(byte[] image);
	public void setJiraUserName(String jiraUserName);
	public void setLastName(String lastName);
	public void setPreferences(IUserPreferences preferences);

//	public IAuthenticatedUser getAuthUser();
//	public void setAuthUser(IAuthenticatedUser authUser);
//	public String getFullUniqueName();
}