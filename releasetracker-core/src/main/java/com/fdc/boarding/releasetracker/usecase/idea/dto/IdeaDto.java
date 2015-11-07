package com.fdc.boarding.releasetracker.usecase.idea.dto;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;
import com.fdc.boarding.releasetracker.usecase.team.dto.TeamImpactDto;
import com.fdc.boarding.releasetracker.usecase.workflow.dto.WorkflowDto;

public class IdeaDto extends AbstractAuditedDto {
	private static final long 			serialVersionUID = 1L;

	public static IdeaDto from( IIdea entity ){
		IdeaDto							dto		= null;
		List<TeamImpactDto>				list;
		
		if( entity != null ){
			dto	= new IdeaDto();
			locals( dto, entity );
			dto.setAssignedTo( UserDto.from( entity.getAssignedTo() ) );
			dto.setSolutionArchitect( UserDto.from( entity.getSolutionArchitect() ) );
			dto.setDemandOwner( UserDto.from( entity.getDemandOwner() ) );
			dto.setOriginalRequestor( UserDto.from( entity.getOriginalRequestor() ) );
			dto.setWorkflow( WorkflowDto.partial( entity.getWorkflow() ) );
			list = new ArrayList<>();
			for( ITeamImpact t : entity.getTeamImpacts() ){
				list.add( TeamImpactDto.partial( t ) );
			}
			dto.setTeamImpacts( list );
		}
		
		return dto;
	}

	protected static void locals( IdeaDto dto, IIdea entity ){
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		dto.setIdeaNumber( entity.getIdeaNumber() );
		dto.setPriority( entity.getPriority() );
		dto.setDescription( entity.getDescription() );
		dto.setJiraId( entity.getJiraId() );
		dto.setProjectNumber( entity.getProjectNumber() );
		dto.setAmendmentNumber( entity.getAmendmentNumber() );
		dto.setIsUmbrellaIdea( entity.getIsUmbrellaIdea() );
		dto.setCreateDate( entity.getCreateDate() );
		dto.setWikiUrl( entity.getWikiUrl() );
		dto.setClarityRom( entity.getClarityRom() );
		dto.setOverallRom( entity.getOverallRom() );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
	}

	public static IdeaDto partial( IIdea entity ){
		IdeaDto					dto		= null;
		
		if( entity != null ){
			dto	= new IdeaDto();
			locals( dto, entity );
		}
		
		return dto;
	}
	
	private Long						id;
	private String						name;
	protected String 					ideaNumber;
	private Integer						priority;
	private String						description;
	private String						jiraId;
	private String						projectNumber;
	private String						amendmentNumber;
	private Boolean						isUmbrellaIdea	= Boolean.FALSE;
	private LocalDate					createDate;
	private String						wikiUrl;
	private Rom							clarityRom;
	private Rom							overallRom;
	private UserDto						assignedTo;
	private UserDto						solutionArchitect;
	private UserDto						demandOwner;
	protected UserDto 					originalRequestor;
	private WorkflowDto					workflow;
	private List<TeamImpactDto>			teamImpacts;

	public IdeaDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIdeaNumber() {
		return ideaNumber;
	}

	public Integer getPriority() {
		return priority;
	}

	public String getDescription() {
		return description;
	}

	public String getJiraId() {
		return jiraId;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public String getAmendmentNumber() {
		return amendmentNumber;
	}

	public Boolean getIsUmbrellaIdea() {
		return isUmbrellaIdea;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public String getWikiUrl() {
		return wikiUrl;
	}

	public Rom getClarityRom() {
		return clarityRom;
	}

	public Rom getOverallRom() {
		return overallRom;
	}

	public UserDto getAssignedTo() {
		return assignedTo;
	}

	public UserDto getSolutionArchitect() {
		return solutionArchitect;
	}

	public UserDto getDemandOwner() {
		return demandOwner;
	}

	public UserDto getOriginalRequestor() {
		return originalRequestor;
	}

	public WorkflowDto getWorkflow() {
		return workflow;
	}

	public List<TeamImpactDto> getTeamImpacts() {
		return teamImpacts;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdeaNumber(String ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public void setAmendmentNumber(String amendmentNumber) {
		this.amendmentNumber = amendmentNumber;
	}

	public void setIsUmbrellaIdea(Boolean isUmbrellaIdea) {
		this.isUmbrellaIdea = isUmbrellaIdea;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}

	public void setClarityRom(Rom clarityRom) {
		this.clarityRom = clarityRom;
	}

	public void setOverallRom(Rom overallRom) {
		this.overallRom = overallRom;
	}

	public void setAssignedTo(UserDto assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setSolutionArchitect(UserDto solutionArchitect) {
		this.solutionArchitect = solutionArchitect;
	}

	public void setDemandOwner(UserDto demandOwner) {
		this.demandOwner = demandOwner;
	}

	public void setOriginalRequestor(UserDto originalRequestor) {
		this.originalRequestor = originalRequestor;
	}

	public void setWorkflow(WorkflowDto workflow) {
		this.workflow = workflow;
	}

	public void setTeamImpacts(List<TeamImpactDto> teamImpacts) {
		this.teamImpacts = teamImpacts;
	}

}
