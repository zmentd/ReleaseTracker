package com.fdc.boarding.releasetracker.persistence.idea;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.IIdeaWorkflow;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;
import com.fdc.boarding.releasetracker.persistence.team.TeamImpactEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.AbstractWorkflowEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.IdeaWorkflowEntity;

@Entity
@Audited
@Indexed
@Table( name = "RT_IDEA", 
	    indexes = {
		@Index( columnList="NAME", unique = false ),
		@Index( columnList="IDEA_NUMBER", unique = true ),
		@Index( columnList="ASGN_TO_ID", unique = false ),
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "IDEA_NUMBER" )
		}
)
public class IdeaEntity extends AbstractAuditedEntity<Long> implements Serializable, IIdea{
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="idea_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="IDEA_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="idea_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Column( name = "NAME" )
	private String						name;

	@Column(name = "IDEA_NUMBER" )
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.NO, store=Store.YES)
	protected String 					ideaNumber;
	
	@Column( name = "PRIORITY" )
	private Integer						priority;
	
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Lob
	@Column( name = "DSC" )
	private String						description;
	
	@Column( name = "JIRA_ID" )
	private String						jiraId;
	
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column( name = "PRJ_NUMBER" )
	private String						projectNumber;
	
	@Field( index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Column( name = "AMND_NUMBER" )
	private String						amendmentNumber;
	
	@Column( name = "IS_UMBR" )
	private Boolean						isUmbrellaIdea	= Boolean.FALSE;

	@Column( name = "CREATE_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					createDate;
	
	@URL
	@Column( name = "WIKI_URL" )
	private String						wikiUrl;
	
	@Column( name = "CLARITY_ROM" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.common.Rom" )
	}
	)
	private Rom							clarityRom;
	
	@Column( name = "OVERALL_ROM" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.common.Rom" )
	}
	)
	private Rom							overallRom;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "ASGN_TO_ID" )	
	private IUser						assignedTo;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "SA_ID" )	
	private IUser						solutionArchitect;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "DMND_OWN_ID" )	
	private IUser						demandOwner;

	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne(targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn(name = "ORGI_RQST_USER_ID")
	protected IUser 					originalRequestor;
	
	@IndexedEmbedded( includePaths  = { "comments.comment" }, targetElement = AbstractWorkflowEntity.class )
	@OneToOne( mappedBy = "idea", targetEntity = IdeaWorkflowEntity.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL} )
	private IIdeaWorkflow				workflow;
	
	@IndexedEmbedded( includePaths  = { "workflow.comments.comment", "team.name" }, targetElement = TeamImpactEntity.class )
	@OneToMany( mappedBy = "idea", targetEntity = TeamImpactEntity.class, cascade={CascadeType.ALL} )
	private Set<ITeamImpact>			teamImpacts	= new HashSet<>();
	
	public IdeaEntity() {
		super();
	}

	@Override
	public void addTeamImpact( ITeamImpact teamImpact ){
		teamImpacts.add(teamImpact);
	}

	@Override
	public String getAmendmentNumber() {
		return amendmentNumber;
	}

	@Override
	public IUser getAssignedTo() {
		return assignedTo;
	}
	
	@Override
	public Rom getClarityRom() {
		return clarityRom;
	}

	@Override
	public LocalDate getCreateDate() {
		return createDate;
	}

	@Override
	public IUser getDemandOwner() {
		return demandOwner;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getIdeaNumber() {
		return ideaNumber;
	}

	@Override
	public Boolean getIsUmbrellaIdea() {
		return isUmbrellaIdea;
	}

	@Override
	public String getJiraId() {
		return jiraId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IUser getOriginalRequestor() {
		return originalRequestor;
	}

	@Override
	public Rom getOverallRom() {
		return overallRom;
	}

	@Override
	public Integer getPriority() {
		return priority;
	}

	@Override
	public String getProjectNumber() {
		return projectNumber;
	}

	@Override
	public IUser getSolutionArchitect() {
		return solutionArchitect;
	}

	@Override
	public Set<ITeamImpact> getTeamImpacts() {
		return teamImpacts;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public String getWikiUrl() {
		return wikiUrl;
	}

	@Override
	public IIdeaWorkflow getWorkflow() {
		return workflow;
	}

	@Override
	public void removeTeamImpact( ITeamImpact teamImpact ){
		teamImpacts.remove(teamImpact);
	}

	@Override
	public void setAmendmentNumber(String amendmentNumber) {
		this.amendmentNumber = amendmentNumber;
	}

	@Override
	public void setAssignedTo(IUser assignedTo) {
		this.assignedTo = assignedTo;
	}

	@Override
	public void setClarityRom(Rom clarityRom) {
		this.clarityRom = clarityRom;
	}

	@Override
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	@Override
	public void setDemandOwner(IUser demandOwner) {
		this.demandOwner = demandOwner;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setIdeaNumber(String ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	@Override
	public void setIsUmbrellaIdea(Boolean isUmbrellaIdea) {
		this.isUmbrellaIdea = isUmbrellaIdea;
	}

	@Override
	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setOriginalRequestor(IUser originalRequestor) {
		this.originalRequestor = originalRequestor;
	}

	@Override
	public void setOverallRom(Rom overallRom) {
		this.overallRom = overallRom;
	}

	@Override
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	@Override
	public void setSolutionArchitect(IUser solutionArchitect) {
		this.solutionArchitect = solutionArchitect;
	}

	@Override
	public void setTeamImpacts(Set<ITeamImpact> teamImpacts) {
		this.teamImpacts = teamImpacts;
	}

	@Override
	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}

	@Override
	public void setWorkflow(IIdeaWorkflow workflow) {
		this.workflow = workflow;
		this.workflow.setIdea( this );
	}

	@Override
	public String toString() {
		return "IdeaEntity [id=" + id + ", name=" + name
				+ ", ideaNumber=" + ideaNumber + ", description="
				+ description + ", jiraId=" + jiraId + ", isProject="
				+ ", isUmbrellaIdea=" + isUmbrellaIdea
				+ ", assignedTo=" + assignedTo + ", overallRom=" + overallRom + ", solutionArchitect="
				+ solutionArchitect + ", developmentManager="
				+ ", demandOwner=" + demandOwner + ", teamImpacts=" + teamImpacts + "]";
	}
}
