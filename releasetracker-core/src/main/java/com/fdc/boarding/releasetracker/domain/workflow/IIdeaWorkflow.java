package com.fdc.boarding.releasetracker.domain.workflow;

import com.fdc.boarding.releasetracker.domain.idea.IIdea;

public interface IIdeaWorkflow extends IWorkflow {
	public IIdea getIdea();
	public void setIdea(IIdea idea);
}