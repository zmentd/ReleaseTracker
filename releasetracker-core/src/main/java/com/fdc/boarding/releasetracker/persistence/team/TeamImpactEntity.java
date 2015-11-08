package com.fdc.boarding.releasetracker.persistence.team;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.ITeamImpactWorkflow;
import com.fdc.boarding.releasetracker.persistence.idea.IdeaEntity;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.AbstractWorkflowEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.TeamImpactWorkflowEntity;

@Entity
@Audited
@Table( name = "RT_TEAM_IMPACT" )
public class TeamImpactEntity extends AbstractAuditedEntity<Long> implements Serializable, ITeamImpact{
	private static final long 			serialVersionUID = 1L;

	@Id
	@TableGenerator( name="team_impact_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="TEAM_IMPACT_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="team_impact_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Column( name = "NO_IMPACT" )
	private boolean						noImpact;

	@Column( name = "NO_IMPACT_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					noImpactDate;

	@Column( name = "SUPPORT_ONLY" )
	private boolean						supportOnly;
	
	@Column( name = "PLANNED_EFFORT_NOT_OPEN" )
	private boolean						plannedEffortNotOpen;

	@IndexedEmbedded( includePaths  = { "name" }, targetElement = TeamEntity.class )
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotNull
	@ManyToOne(targetEntity = TeamEntity.class)
	@JoinColumn(name = "TEAM_ID")
	protected ITeam 					team;
	
	@ContainedIn
	@ManyToOne( targetEntity = IdeaEntity.class )
	@JoinColumn( name = "IDEA_ID" )	
	private IIdea						idea;

	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL} )
	@JoinColumn( name = "ROM_OWNER_ID" )	
	private IUser						romOwner;
	
	@IndexedEmbedded( includePaths  = { "comments.comment" }, targetElement = AbstractWorkflowEntity.class )
	@OneToOne( mappedBy = "teamImpact", targetEntity = TeamImpactWorkflowEntity.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL} )
	private ITeamImpactWorkflow			workflow;

	public TeamImpactEntity() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public IIdea getIdea() {
		return idea;
	}

	@Override
	public LocalDate getNoImpactDate() {
		return noImpactDate;
	}

	@Override
	public IUser getRomOwner() {
		return romOwner;
	}

	@Override
	public ITeam getTeam() {
		return team;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}
	
	@Override
	public ITeamImpactWorkflow getWorkflow() {
		return workflow;
	}

	@Override
	public boolean isNoImpact() {
		return noImpact;
	}

	@Override
	public boolean isPlannedEffortNotOpen() {
		return plannedEffortNotOpen;
	}

	@Override
	public boolean isSupportOnly() {
		return supportOnly;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setIdea(IIdea idea) {
		this.idea = idea;
	}

	@Override
	public void setNoImpact(boolean noImpact) {
		this.noImpact = noImpact;
	}

	@Override
	public void setNoImpactDate(LocalDate noImpactDate) {
		this.noImpactDate = noImpactDate;
	}

	@Override
	public void setPlannedEffortNotOpen(boolean plannedEffortNotOpen) {
		this.plannedEffortNotOpen = plannedEffortNotOpen;
	}

	@Override
	public void setRomOwner(IUser romOwner) {
		this.romOwner = romOwner;
	}

	@Override
	public void setSupportOnly(boolean supportOnly) {
		this.supportOnly = supportOnly;
	}

	@Override
	public void setTeam(ITeam team) {
		this.team = team;
	}

	@Override
	public void setWorkflow(ITeamImpactWorkflow workflow) {
		this.workflow = workflow;
		this.workflow.setTeamImpact( this );
	}
}
