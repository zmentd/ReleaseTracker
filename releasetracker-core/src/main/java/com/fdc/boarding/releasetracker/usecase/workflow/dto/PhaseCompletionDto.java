package com.fdc.boarding.releasetracker.usecase.workflow.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;

public class PhaseCompletionDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static PhaseCompletionDto from( IPhaseCompletion entity ){
		PhaseCompletionDto				dto		= null;
		List<PhaseApprovalDto>			plist;
		List<StatusCompletionDto>		slist;
		
		if( entity != null ){
			dto	= new PhaseCompletionDto();
			locals( dto, entity );
			dto.setCurrentStatusCompletion( StatusCompletionDto.from( entity.getCurrentStatusCompletion() ) );
			plist	= new ArrayList<>();
			for( IPhaseApproval p : entity.getPhaseApprovals() ){
				plist.add( PhaseApprovalDto.partial( p ) );
			}
			dto.setPhaseApprovals( plist );
			slist	= new ArrayList<>();
			for( IStatusCompletion s : entity.getStatusCompletions() ){
				slist.add( StatusCompletionDto.from( s ) );
			}
			dto.setStatusCompletions( slist );
		}
		
		return dto;
	}

	protected static void locals( PhaseCompletionDto dto, IPhaseCompletion entity ){
		dto.setId( entity.getId() );
		dto.setDaysInPhase( entity.getDaysInPhase() );
		dto.setPhaseIndex( entity.getPhaseIndex() );
		dto.setDaysFromExpectedCompletion( entity.getDaysFromExpectedCompletion() );
		dto.setEntryDate( entity.getEntryDate() );
		dto.setCompletionDate( entity.getCompletionDate() );
		dto.setExpectedCompletionDate( entity.getExpectedCompletionDate() );
		dto.setPhase( PhaseDto.from( entity.getPhase() ) );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
	}

	public static PhaseCompletionDto partial( IPhaseCompletion entity ){
		PhaseCompletionDto				dto		= null;
		
		if( entity != null ){
			dto	= new PhaseCompletionDto();
			locals( dto, entity );
		}
		
		return dto;
	}
	
	private Long						id;
	private int							phaseIndex;
	private Integer						daysInPhase;
	private Integer						daysFromExpectedCompletion;
	private LocalDate					entryDate;
	private LocalDate					completionDate;
	private LocalDate					expectedCompletionDate;
	private PhaseDto					phase;
	private StatusCompletionDto			currentStatusCompletion;
	private List<PhaseApprovalDto>		phaseApprovals;
	private List<StatusCompletionDto>	statusCompletions;

	public PhaseCompletionDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public int getPhaseIndex() {
		return phaseIndex;
	}

	public Integer getDaysInPhase() {
		return daysInPhase;
	}

	public Integer getDaysFromExpectedCompletion() {
		return daysFromExpectedCompletion;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public LocalDate getExpectedCompletionDate() {
		return expectedCompletionDate;
	}

	public PhaseDto getPhase() {
		return phase;
	}

	public StatusCompletionDto getCurrentStatusCompletion() {
		return currentStatusCompletion;
	}

	public List<PhaseApprovalDto> getPhaseApprovals() {
		return phaseApprovals;
	}

	public List<StatusCompletionDto> getStatusCompletions() {
		return statusCompletions;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhaseIndex(int phaseIndex) {
		this.phaseIndex = phaseIndex;
	}

	public void setDaysInPhase(Integer daysInPhase) {
		this.daysInPhase = daysInPhase;
	}

	public void setDaysFromExpectedCompletion(Integer daysFromExpectedCompletion) {
		this.daysFromExpectedCompletion = daysFromExpectedCompletion;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public void setExpectedCompletionDate(LocalDate expectedCompletionDate) {
		this.expectedCompletionDate = expectedCompletionDate;
	}

	public void setPhase(PhaseDto phase) {
		this.phase = phase;
	}

	public void setCurrentStatusCompletion(
			StatusCompletionDto currentStatusCompletion) {
		this.currentStatusCompletion = currentStatusCompletion;
	}

	public void setPhaseApprovals(List<PhaseApprovalDto> phaseApprovals) {
		this.phaseApprovals = phaseApprovals;
	}

	public void setStatusCompletions(List<StatusCompletionDto> statusCompletions) {
		this.statusCompletions = statusCompletions;
	}

}
