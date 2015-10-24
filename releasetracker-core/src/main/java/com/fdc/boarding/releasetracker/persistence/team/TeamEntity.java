package com.fdc.boarding.releasetracker.persistence.team;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;

@Entity
@Cacheable( true )
@Table( name = "RT_TEAM",
		indexes = {
		@Index( columnList="NAME", unique = true )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "NAME" )
		}
)
public class TeamEntity extends AbstractAuditedEntity<Long> implements ITeam {
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="team_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="TEAM_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="team_sequence")
	@Column( name = "ID" )
	private Long	id;

	@NotEmpty
	@NotNull
	@Column( name = "NAME" )
	private String	name;
	
	@NotEmpty
	@NotNull
	@Column( name = "OBS" )
	private String	obs;
	
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "SA_ID" )	
	private IUser						solutionArchitect;
	
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "DEV_MNGR_ID" )	
	private IUser						developmentManager;
	
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "BUS_OWN_ID" )	
	private IUser						businessOwner;
	
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinColumn( name = "PORT_MNGR_ID" )	
	private IUser						portfolioManager;
	
	public TeamEntity() {
		super();
	}

	public TeamEntity(String name, String obs) {
		super();
		this.name = name;
		this.obs = obs;
	}

	@Override
	public IUser getBusinessOwner() {
		return businessOwner;
	}

	@Override
	public IUser getDevelopmentManager() {
		return developmentManager;
	}

	@Override
	public Long getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getObs() {
		return obs;
	}
	
	@Override
	public IUser getPortfolioManager() {
		return portfolioManager;
	}

	@Override
	public IUser getSolutionArchitect() {
		return solutionArchitect;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void setBusinessOwner(IUser businessOwner) {
		this.businessOwner = businessOwner;
	}

	@Override
	public void setDevelopmentManager(IUser developmentManager) {
		this.developmentManager = developmentManager;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public void setPortfolioManager(IUser portfolioManager) {
		this.portfolioManager = portfolioManager;
	}

	@Override
	public void setSolutionArchitect(IUser solutionArchitect) {
		this.solutionArchitect = solutionArchitect;
	}

}
