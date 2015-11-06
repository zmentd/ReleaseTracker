package com.fdc.boarding.releasetracker.persistence.workflow;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.StatusType;

@Entity
@Cacheable( true )
@Table( name = "RT_WORKFLOW_STATUS",
		indexes = {
		@Index( columnList="NAME", unique = true )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "NAME" )
		}
)
public class StatusEntity extends AbstractAuditedEntity<Long> implements Serializable, IStatus{
	private static final long 			serialVersionUID = 1L;

	@Id
	@TableGenerator( name="status_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="STATUS_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="status_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@NotNull
	@Column( name = "NAME" )
	private String						name;
	
	@Column( name = "DSC" )
	private String						description;
	
	@NotNull
	@Column( name = "TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.workflow.StatusType" )
	}
	)
	private StatusType					type;

	public StatusEntity() {
		super();
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
	public String getDescription() {
		return description;
	}

	@Override
	public StatusType getType() {
		return type;
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
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setType(StatusType type) {
		this.type = type;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusEntity other = (StatusEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
