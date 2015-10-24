package com.fdc.boarding.releasetracker.domain.idea;

import java.util.Set;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.IIdeaWorkflow;

public interface IIdea extends IAuditable, IEntity<Long> {
	public abstract void addTeamImpact(ITeamImpact teamImpact);
	public abstract String getAmendmentNumber();
	public abstract IUser getAssignedTo();
	public abstract Rom getClarityRom();
	public abstract LocalDate getCreateDate();
	public abstract IUser getDemandOwner();
	public abstract String getDescription();
	public abstract Long getId();
	public abstract String getIdeaNumber();
	public abstract Boolean getIsUmbrellaIdea();
	public abstract String getJiraId();
	public abstract String getName();
	public abstract IUser getOriginalRequestor();
	public abstract Rom getOverallRom();
	public abstract Integer getPriority();
	public abstract String getProjectNumber();
	public abstract IUser getSolutionArchitect();
	public abstract Set<ITeamImpact> getTeamImpacts();
	public abstract String getWikiUrl();
	public abstract IIdeaWorkflow getWorkflow();
	public abstract void removeTeamImpact(ITeamImpact teamImpact);
	public abstract void setAmendmentNumber(String amendmentNumber);
	public abstract void setAssignedTo(IUser assignedTo);
	public abstract void setClarityRom(Rom clarityRom);
	public abstract void setCreateDate(LocalDate createDate);
	public abstract void setDemandOwner(IUser demandOwner);
	public abstract void setDescription(String description);
	public abstract void setId(Long id);
	public abstract void setIdeaNumber(String ideaNumber);
	public abstract void setIsUmbrellaIdea(Boolean isUmbrellaIdea);
	public abstract void setJiraId(String jiraId);
	public abstract void setName(String name);
	public abstract void setOriginalRequestor(IUser originalRequestor);
	public abstract void setOverallRom(Rom overallRom);
	public abstract void setPriority(Integer priority);
	public abstract void setProjectNumber(String projectNumber);
	public abstract void setSolutionArchitect(IUser solutionArchitect);
	public abstract void setTeamImpacts( Set<ITeamImpact> teamImpacts);
	public abstract void setWikiUrl(String wikiUrl);
	public abstract void setWorkflow(IIdeaWorkflow workflow);
}