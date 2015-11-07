package com.fdc.boarding.releasetracker.usecase.workflow.dto;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;

public class StatusCompletionDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static StatusCompletionDto from( IStatusCompletion entity ){
		StatusCompletionDto				dto		= null;
		
		if( entity != null ){
			dto	= new StatusCompletionDto();
			dto.setId( entity.getId() );
			dto.setDaysInStatus( entity.getDaysInStatus() );
			dto.setEntryDate( entity.getEntryDate() );
			dto.setCompletionDate( entity.getCompletionDate() );
			dto.setStatus( StatusDto.from( entity.getStatus() ) );
			dto.setLastModifiedBy( entity.getLastModifiedBy() );
			dto.setLastModifiedDate( entity.getLastModifiedDate() );
		}
		
		return dto;
	}
	
	private Long						id;
	private Integer						daysInStatus;
	private LocalDate					entryDate;
	private LocalDate					completionDate;
	private StatusDto					status;

	public StatusCompletionDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public Integer getDaysInStatus() {
		return daysInStatus;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public StatusDto getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDaysInStatus(Integer daysInStatus) {
		this.daysInStatus = daysInStatus;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public void setStatus(StatusDto status) {
		this.status = status;
	}

}
