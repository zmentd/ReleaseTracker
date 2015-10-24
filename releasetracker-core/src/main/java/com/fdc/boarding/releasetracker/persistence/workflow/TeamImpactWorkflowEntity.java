package com.fdc.boarding.releasetracker.persistence.workflow;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.ITeamImpactWorkflow;
import com.fdc.boarding.releasetracker.persistence.team.TeamImpactEntity;

@Entity
@Audited
@DiscriminatorValue( "TEAM_IMPACT" )
public class TeamImpactWorkflowEntity extends AbstractWorkflowEntity implements ITeamImpactWorkflow {
	private static final long 			serialVersionUID = 1L;

	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@OneToOne( targetEntity = TeamImpactEntity.class )
	private ITeamImpact					teamImpact;
	
	public TeamImpactWorkflowEntity(){
		super();
	}

	@Override
	public ITeamImpact getTeamImpact() {
		return teamImpact;
	}

	@Override
	public void setTeamImpact(ITeamImpact teamImpact) {
		this.teamImpact = teamImpact;
	}

	@Override
	public IIdea getIdea() {
		return teamImpact.getIdea();
	}
}
