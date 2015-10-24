package com.fdc.boarding.releasetracker.domain.idea;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;

public interface IIdeaLink extends IAuditable, IEntity<Long> {
	public abstract IIdea getChildIdea();
	public abstract Long getId();
	public abstract IIdea getParentIdea();
	public abstract IdeaLinkType getType();
	public abstract void setChildIdea(IIdea childIdea);
	public abstract void setId(Long id);
	public abstract void setParentIdea(IIdea parentIdea);
	public abstract void setType(IdeaLinkType type);
}