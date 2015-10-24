package com.fdc.boarding.releasetracker.domain.workflow;

import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;

public interface ITeamImpactWorkflow extends IWorkflow{
	public void setTeamImpact(ITeamImpact teamImpact);
	public ITeamImpact getTeamImpact();
}