package com.fdc.boarding.releasetracker.domain.idea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fdc.boarding.core.util.StringUtil;
import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;

public class IdeaAp implements Serializable {
	private static final long 			serialVersionUID = 1L;
	
	private ITeam					team;
	private IPhase						phase;
	private Long						assignedTo;
	private List<IStatus>				excludeStatuses;
	private IdeaGroupBy				groupBy;
	private DaysToTargetStatus			milestoneStatus;
	private DaysToTargetStatus			releaseStatus;
	
	public IdeaAp(){
		super();
		excludeStatuses	= new ArrayList<>();
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public List<IStatus> getExcludeStatuses() {
		return excludeStatuses;
	}

	public String getExcludeStatusesIds() {
		List<Long>						ids;
		
		ids	= new ArrayList<Long>();
		for( IStatus status : excludeStatuses ){
			ids.add( status.getId() );
		}
			
		return StringUtil.joinAll( ",", false, ids );
	}

	public IdeaGroupBy getGroupBy() {
		return groupBy;
	}

	public DaysToTargetStatus getMilestoneStatus() {
		return milestoneStatus;
	}

	public IPhase getPhase() {
		return phase;
	}

	public ITeam getTeam() {
		return team;
	}

	public DaysToTargetStatus getReleaseStatus() {
		return releaseStatus;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setExcludeStatuses(List<IStatus> excludeStatuses) {
		this.excludeStatuses = excludeStatuses;
	}

	public void setGroupBy(IdeaGroupBy groupBy) {
		this.groupBy = groupBy;
	}

	public void setMilestoneStatus(DaysToTargetStatus milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
	}

	public void setPhase(IPhase phase) {
		this.phase = phase;
	}

	public void setTeam(ITeam team) {
		this.team = team;
	}

	public void setReleaseStatus(DaysToTargetStatus releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
}
