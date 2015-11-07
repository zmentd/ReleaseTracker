package com.fdc.boarding.releasetracker.usecase.release.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.release.IMilestone;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;

public class ReleaseEntryDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static ReleaseEntryDto from( IReleaseEntry entity ){
		ReleaseEntryDto				dto		= null;
		List<MilestoneDto>			list;
		
		if( entity != null ){
			dto	= new ReleaseEntryDto();
			locals( dto, entity );
			if( entity.getMilestones() != null ){
				list = new ArrayList<>();
				for( IMilestone m : entity.getMilestones() ){
					list.add( MilestoneDto.from( m ) );
				}
				dto.setMilestones( list );
			}
		}
		
		return dto;
	}

	protected static void locals( ReleaseEntryDto dto, IReleaseEntry entity ){
		dto.setId( entity.getId() );
		dto.setLastCatRevDate( entity.getLastCatRevDate() );
		dto.setLockDownDate( entity.getLockDownDate() );
		dto.setMinor( entity.getMinor() );
		dto.setReleaseDate( entity.getReleaseDate() );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
	}

	public static ReleaseEntryDto partial( IReleaseEntry entity ){
		ReleaseEntryDto					dto		= null;
		
		if( entity != null ){
			dto	= new ReleaseEntryDto();
			locals( dto, entity );
		}
		
		return dto;
	}
	
	private Long						id;
	private Boolean						minor;
	private LocalDate					releaseDate;
	private LocalDate					lockDownDate;
	private LocalDate					lastCatRevDate;
	private List<MilestoneDto>			milestones;

	public ReleaseEntryDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public Boolean getMinor() {
		return minor;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public LocalDate getLockDownDate() {
		return lockDownDate;
	}

	public LocalDate getLastCatRevDate() {
		return lastCatRevDate;
	}

	public List<MilestoneDto> getMilestones() {
		return milestones;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMinor(Boolean minor) {
		this.minor = minor;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setLockDownDate(LocalDate lockDownDate) {
		this.lockDownDate = lockDownDate;
	}

	public void setLastCatRevDate(LocalDate lastCatRevDate) {
		this.lastCatRevDate = lastCatRevDate;
	}

	public void setMilestones(List<MilestoneDto> milestones) {
		this.milestones = milestones;
	}


}
