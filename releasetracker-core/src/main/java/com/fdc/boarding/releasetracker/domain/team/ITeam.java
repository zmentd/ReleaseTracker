package com.fdc.boarding.releasetracker.domain.team;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.security.IUser;

public interface ITeam extends IAuditable, IEntity<Long> {

	public abstract IUser getBusinessOwner();

	public abstract IUser getDevelopmentManager();

	public Long getId();

	public String getName();

	public String getObs();

	public abstract IUser getPortfolioManager();

	public abstract IUser getSolutionArchitect();

	public abstract void setBusinessOwner(IUser businessOwner);

	public abstract void setDevelopmentManager(IUser developmentManager);

	public void setId(Long id);

	public void setName(String name);

	public void setObs(String obs);

	public abstract void setPortfolioManager(IUser portfolioManager);

	public abstract void setSolutionArchitect(IUser solutionArchitect);

}