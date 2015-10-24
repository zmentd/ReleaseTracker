package com.fdc.boarding.releasetracker.persistence.workflow;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

@Entity
@Cacheable( true )
@Table( name = "RT_PHASE",
		indexes = {
		@Index( columnList="NAME", unique = true )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "NAME" )
		}
)
public class PhaseEntity extends AbstractAuditedEntity<Long> implements Serializable, IPhase{
	private static final long 			serialVersionUID = 1L;

	@Id
	@TableGenerator( name="phase_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="PHASE_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="phase_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@NotNull
	@Column( name = "NAME" )
	private String						name;
	
	@Column( name = "DSC" )
	private String						description;

	@Column( name = "SEQ" )
	private int							index;

	@Column( name = "TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.workflow.PhaseType" )
	}
	)
	private PhaseType					type;
	
	@ManyToMany( targetEntity=StatusEntity.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
        name="RT_PHASE_AVAIL_STATUS",
        joinColumns=@JoinColumn(name="PHASE_ID"),
        inverseJoinColumns=@JoinColumn(name="STATUS_ID")
    )
	private List<IStatus>				availableStatuses;
	
	@OrderBy( "index" )
	@ManyToMany( targetEntity=PhaseEntity.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
        name="RT_PHASE_NEXT_PHASE",
        joinColumns=@JoinColumn(name="PHASE_ID"),
        inverseJoinColumns=@JoinColumn(name="NEXT_PHASE_ID")
    )
	private List<IPhase>				nextPhases;

	@OrderBy("sequence")
	@OneToMany(targetEntity = PhaseApprovalTypeEntity.class, mappedBy = "phase", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	protected List<IPhaseApprovalType> requiredApprovalTypes;
	
	public PhaseEntity() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhaseEntity other = (PhaseEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public List<IStatus> getAvailableStatuses() {
		return availableStatuses;
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
	public int getIndex() {
		return index;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<IPhase> getNextPhases() {
		return nextPhases;
	}

	@Override
	public List<IPhaseApprovalType> getRequiredApprovalTypes() {
		return requiredApprovalTypes;
	}

	@Override
	public PhaseType getType() {
		return type;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public void setAvailableStatuses(List<IStatus> availableStatuses) {
		this.availableStatuses = availableStatuses;
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
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setNextPhases(List<IPhase> nextPhases) {
		this.nextPhases = nextPhases;
	}

	@Override
	public void setRequiredApprovalTypes( List<IPhaseApprovalType> requiredApprovalTypes) {
		this.requiredApprovalTypes = requiredApprovalTypes;
	}

	@Override
	public void setType(PhaseType type) {
		this.type = type;
	}
	
}
