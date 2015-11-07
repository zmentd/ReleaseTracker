package com.fdc.boarding.releasetracker.usecase.workflow.dto;

import java.io.Serializable;

import com.fdc.boarding.releasetracker.domain.workflow.ApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;

public class PhaseApprovalTypeDto extends AbstractAuditedDto implements Serializable {
	private static final long 	serialVersionUID = 1L;

	public static PhaseApprovalTypeDto from( IPhaseApprovalType entity ){
		PhaseApprovalTypeDto							dto		= null;
		
		if( entity != null ){
			dto	= new PhaseApprovalTypeDto();
			dto.setId( entity.getId() );
			dto.setName( entity.getName() );
			dto.setSequence( entity.getSequence() );
			dto.setType( entity.getType() );
			dto.setLastModifiedBy( entity.getLastModifiedBy() );
			dto.setLastModifiedDate( entity.getLastModifiedDate() );
		}
		
		return dto;
	}
	
	private Long						id;
	private String						name;
	private Integer						sequence;
	private ApprovalType				type;

	public PhaseApprovalTypeDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getSequence() {
		return sequence;
	}

	public ApprovalType getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setType(ApprovalType type) {
		this.type = type;
	}
}
