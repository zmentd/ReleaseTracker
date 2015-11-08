package com.fdc.boarding.releasetracker.usecase.team.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class TeamDto extends AbstractAuditedDto implements Serializable {
	private static final long 	serialVersionUID = 1L;

	public static TeamDto from( ITeam team ){
		TeamDto							dto		= null;
		
		if( team != null ){
			dto	= new TeamDto();
			dto.setId( team.getId() );
			dto.setName( team.getName() );
			dto.setObs( team.getObs() );
			dto.setBusinessOwner( UserDto.from( team.getBusinessOwner() ) );
			dto.setDevelopmentManager( UserDto.from( team.getDevelopmentManager() ) );
			dto.setPortfolioManager( UserDto.from( team.getPortfolioManager() ) );
			dto.setSolutionArchitect( UserDto.from( team.getSolutionArchitect() ) );
			dto.setLastModifiedBy( team.getLastModifiedBy() );
			dto.setLastModifiedDate( team.getLastModifiedDate() );
		}
		
		return dto;
	}

	private Long						id;
	
	@NotNull
	private String						name;
	
	@NotNull
	private String						obs;
	private UserDto						solutionArchitect;
	private UserDto						developmentManager;
	private UserDto						businessOwner;
	private UserDto						portfolioManager;

	public TeamDto(){
		super();
	}

	public UserDto getBusinessOwner() {
		return businessOwner;
	}

	public UserDto getDevelopmentManager() {
		return developmentManager;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getObs() {
		return obs;
	}

	public UserDto getPortfolioManager() {
		return portfolioManager;
	}

	public UserDto getSolutionArchitect() {
		return solutionArchitect;
	}

	public void setBusinessOwner(UserDto businessOwner) {
		this.businessOwner = businessOwner;
	}

	public void setDevelopmentManager(UserDto developmentManager) {
		this.developmentManager = developmentManager;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public void setPortfolioManager(UserDto portfolioManager) {
		this.portfolioManager = portfolioManager;
	}

	public void setSolutionArchitect(UserDto solutionArchitect) {
		this.solutionArchitect = solutionArchitect;
	}
}
