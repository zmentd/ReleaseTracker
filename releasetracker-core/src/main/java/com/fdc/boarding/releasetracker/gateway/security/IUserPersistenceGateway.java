package com.fdc.boarding.releasetracker.gateway.security;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.security.IAuthenticatedUser;
import com.fdc.boarding.releasetracker.domain.security.IUser;

public interface IUserPersistenceGateway extends Serializable {

	public abstract List<IUser> findAllUsers();

	public abstract IAuthenticatedUser findByUserId(String userId);

	public abstract IUser findByEmail(String email);

	public abstract IAuthenticatedUser findAuthenticatedByEmail(String email);

	public abstract void add(IAuthenticatedUser entity);

	public abstract void add(IUser entity);

}