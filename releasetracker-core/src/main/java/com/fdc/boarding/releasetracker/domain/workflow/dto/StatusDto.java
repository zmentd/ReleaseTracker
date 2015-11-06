package com.fdc.boarding.releasetracker.domain.workflow.dto;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.domain.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.StatusType;

public class StatusDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static StatusDto from( IStatus entity ){
		StatusDto				dto		= null;
		
		if( entity != null ){
			dto	= new StatusDto();
			dto.setDescription( entity.getDescription() );
			dto.setId( entity.getId() );
			dto.setName( entity.getName() );
			dto.setType( entity.getType() );
			dto.setLastModifiedBy( entity.getLastModifiedBy() );
			dto.setLastModifiedDate( entity.getLastModifiedDate() );
		}
		
		return dto;
	}
	
	private Long						id;
	private String						name;
	private String						description;
	private StatusType					type;

	public StatusDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public StatusType getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(StatusType type) {
		this.type = type;
	}

}
