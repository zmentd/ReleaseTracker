package com.fdc.boarding.releasetracker.domain.workflow.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

public class PhaseDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static PhaseDto from( IPhase entity ){
		PhaseDto						dto		= null;
		List<StatusDto>					slist;
		List<PhaseDto>					plist;
		List<PhaseApprovalTypeDto>		patList;
		
		if( entity != null ){
			dto	= new PhaseDto();
			locals( dto, entity );
			if( entity.getAvailableStatuses() != null ){
				slist = new ArrayList<>();
				for( IStatus s : entity.getAvailableStatuses() ){
					slist.add( StatusDto.from( s ) );
				}
				dto.setAvailableStatuses( slist );
			}
			if( entity.getNextPhases() != null ){
				plist = new ArrayList<>();
				for( IPhase p : entity.getNextPhases() ){
					plist.add( PhaseDto.from( p ) );
				}
				dto.setNextPhases( plist );
			}
			if( entity.getRequiredApprovalTypes() != null ){
				patList	= new ArrayList<>();
				for( IPhaseApprovalType pat : entity.getRequiredApprovalTypes() ){
					patList.add( PhaseApprovalTypeDto.from( pat ) );
				}
				dto.setRequiredApprovalTypes( patList );
			}
		}
		
		return dto;
	}

	protected static void locals( PhaseDto dto, IPhase entity ){
		dto.setDescription( entity.getDescription() );
		dto.setId( entity.getId() );
		dto.setIndex( entity.getIndex() );
		dto.setName( entity.getName() );
		dto.setType( entity.getType() );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
	}

	public static PhaseDto partial( IPhase entity ){
		PhaseDto						dto		= null;
		
		if( entity != null ){
			dto	= new PhaseDto();
			locals( dto, entity );
		}
		
		return dto;
	}
	
	private Long							id;
	private String							name;
	private String							description;
	private int								index;
	private PhaseType						type;
	private List<StatusDto>					availableStatuses;
	private List<PhaseDto>					nextPhases;
	protected List<PhaseApprovalTypeDto>	requiredApprovalTypes;

	public PhaseDto(){
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

	public int getIndex() {
		return index;
	}

	public PhaseType getType() {
		return type;
	}

	public List<StatusDto> getAvailableStatuses() {
		return availableStatuses;
	}

	public List<PhaseDto> getNextPhases() {
		return nextPhases;
	}

	public List<PhaseApprovalTypeDto> getRequiredApprovalTypes() {
		return requiredApprovalTypes;
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

	public void setIndex(int index) {
		this.index = index;
	}

	public void setType(PhaseType type) {
		this.type = type;
	}

	public void setAvailableStatuses(List<StatusDto> availableStatuses) {
		this.availableStatuses = availableStatuses;
	}

	public void setNextPhases(List<PhaseDto> nextPhases) {
		this.nextPhases = nextPhases;
	}

	public void setRequiredApprovalTypes(
			List<PhaseApprovalTypeDto> requiredApprovalTypes) {
		this.requiredApprovalTypes = requiredApprovalTypes;
	}

}
