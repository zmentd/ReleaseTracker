package com.fdc.boarding.releasetracker.domain.security;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IAuthenticatedUser extends IAuditable, IEntity<Long> {

	public Long getId();

	public String getPassword();

	public IUser getUser();

	public String getUserId();

	public void setId(Long id);

	public void setPassword(String password);

	public void setUser(IUser user);

	public void setUserId(String userId);

}