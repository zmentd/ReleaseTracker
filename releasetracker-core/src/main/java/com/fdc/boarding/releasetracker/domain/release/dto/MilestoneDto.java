package com.fdc.boarding.releasetracker.domain.release.dto;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.domain.release.IMilestone;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

public class MilestoneDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static MilestoneDto from( IMilestone entity ){
		MilestoneDto				dto		= null;
		
		if( entity != null ){
			dto	= new MilestoneDto();
			dto.setDescription( entity.getDescription() );
			dto.setDueDate( entity.getDueDate() );
			dto.setId( entity.getId() );
			dto.setIndex( entity.getIndex() );
			dto.setLargeDueDate( entity.getLargeDueDate() );
			dto.setMediumDueDate( entity.getMediumDueDate() );
			dto.setName( entity.getName() );
			dto.setPhaseType( entity.getPhaseType() );
			dto.setSmallDueDate( entity.getSmallDueDate() );
			dto.setLastModifiedBy( entity.getLastModifiedBy() );
			dto.setLastModifiedDate( entity.getLastModifiedDate() );
		}
		
		return dto;
	}
	
	private Long						id;
	private String						name;
	private int							index;
	private String						description;
	private PhaseType					phaseType;
	private LocalDate					dueDate;
	private LocalDate					largeDueDate;
	private LocalDate					mediumDueDate;
	private LocalDate					smallDueDate;

	public MilestoneDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public String getDescription() {
		return description;
	}

	public PhaseType getPhaseType() {
		return phaseType;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getLargeDueDate() {
		return largeDueDate;
	}

	public LocalDate getMediumDueDate() {
		return mediumDueDate;
	}

	public LocalDate getSmallDueDate() {
		return smallDueDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhaseType(PhaseType phaseType) {
		this.phaseType = phaseType;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setLargeDueDate(LocalDate largeDueDate) {
		this.largeDueDate = largeDueDate;
	}

	public void setMediumDueDate(LocalDate mediumDueDate) {
		this.mediumDueDate = mediumDueDate;
	}

	public void setSmallDueDate(LocalDate smallDueDate) {
		this.smallDueDate = smallDueDate;
	}

}
