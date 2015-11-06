package com.fdc.boarding.releasetracker.domain.team.dto;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.domain.security.dto.UserDto;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.dto.WorkflowDto;

public class TeamImpactDto extends AbstractAuditedDto implements Serializable {
	private static final long 	serialVersionUID = 1L;

	public static TeamImpactDto from( ITeamImpact entity ){
		TeamImpactDto					dto		= null;
		
		if( entity != null ){
			dto	= new TeamImpactDto();
			locals( dto, entity );
			dto.setTeam( TeamDto.from( entity.getTeam() ) );
			dto.setRomOwner( UserDto.from( entity.getRomOwner() ) );
			dto.setWorkflow( WorkflowDto.partial( entity.getWorkflow() ) );
		}
		
		return dto;
	}

	protected static void locals( TeamImpactDto dto, ITeamImpact entity ){
		dto.setId( entity.getId() );
		dto.setNoImpact( entity.isNoImpact() );
		dto.setSupportOnly( entity.isSupportOnly() );
		dto.setPlannedEffortNotOpen( entity.isPlannedEffortNotOpen() );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
	}

	public static TeamImpactDto partial( ITeamImpact entity ){
		TeamImpactDto					dto		= null;
		
		if( entity != null ){
			dto	= new TeamImpactDto();
			locals( dto, entity );
		}
		
		return dto;
	}
	
	private Long						id;
	private boolean						noImpact;
	private LocalDate					noImpactDate;
	private boolean						supportOnly;
	private boolean						plannedEffortNotOpen;
	protected TeamDto 					team;
	private UserDto						romOwner;
	private WorkflowDto					workflow;

	public TeamImpactDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public boolean isNoImpact() {
		return noImpact;
	}

	public LocalDate getNoImpactDate() {
		return noImpactDate;
	}

	public boolean isSupportOnly() {
		return supportOnly;
	}

	public boolean isPlannedEffortNotOpen() {
		return plannedEffortNotOpen;
	}

	public TeamDto getTeam() {
		return team;
	}

	public UserDto getRomOwner() {
		return romOwner;
	}

	public WorkflowDto getWorkflow() {
		return workflow;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNoImpact(boolean noImpact) {
		this.noImpact = noImpact;
	}

	public void setNoImpactDate(LocalDate noImpactDate) {
		this.noImpactDate = noImpactDate;
	}

	public void setSupportOnly(boolean supportOnly) {
		this.supportOnly = supportOnly;
	}

	public void setPlannedEffortNotOpen(boolean plannedEffortNotOpen) {
		this.plannedEffortNotOpen = plannedEffortNotOpen;
	}

	public void setTeam(TeamDto team) {
		this.team = team;
	}

	public void setRomOwner(UserDto romOwner) {
		this.romOwner = romOwner;
	}

	public void setWorkflow(WorkflowDto workflow) {
		this.workflow = workflow;
	}
}
