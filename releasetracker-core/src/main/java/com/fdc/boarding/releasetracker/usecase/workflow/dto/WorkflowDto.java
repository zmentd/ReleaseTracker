package com.fdc.boarding.releasetracker.usecase.workflow.dto;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.workflow.IIdeaWorkflow;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.usecase.release.dto.ReleaseEntryDto;

public class WorkflowDto extends AbstractAuditedDto {
	public static enum WorkflowType
	{
		Idea,
		TeamImpact
	}

	private static final long 			serialVersionUID = 1L;;
	
	public static WorkflowDto from( IWorkflow entity ){
		WorkflowDto						dto		= null;
		List<PhaseCompletionDto>		plist;
		
		if( entity != null ){
			dto	= new WorkflowDto();
			locals( dto, entity );
			plist = new ArrayList<>();
			for( IPhaseCompletion p : entity.getPhaseCompletions() ){
				plist.add( PhaseCompletionDto.from( p ) );
			}
			dto.setPhaseCompletions( plist );
			if( entity instanceof IIdeaWorkflow ){
				dto.setType( WorkflowType.Idea );
			}
			else{
				dto.setType( WorkflowType.TeamImpact );
			}
		}
		
		return dto;
	}

	protected static void locals( WorkflowDto dto, IWorkflow entity ){
		dto.setId( entity.getId() );
		dto.setTargetImplementationDate( entity.getTargetImplementationDate() );
		dto.setPhaseTargetDate( entity.getPhaseTargetDate() );
		dto.setCacnelledDate( entity.getCacnelledDate() );
		dto.setRom( entity.getRom() );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
		dto.setCurrentPhaseCompletion( PhaseCompletionDto.from( entity.getCurrentPhaseCompletion() ) );
		dto.setRelease( ReleaseEntryDto.partial( entity.getRelease() ) );
	}

	public static WorkflowDto partial( IWorkflow entity ){
		WorkflowDto						dto		= null;
		
		if( entity != null ){
			dto	= new WorkflowDto();
			locals( dto, entity );
		}
		
		return dto;
	}

	private Long						id;
	private WorkflowType				type;
	private LocalDate					targetImplementationDate;
	private LocalDate					phaseTargetDate;
	private LocalDate					cacnelledDate;
	private Rom							rom;
	private PhaseCompletionDto			currentPhaseCompletion;
	private ReleaseEntryDto				release;
	private List<PhaseCompletionDto>	phaseCompletions	= new ArrayList<>();

	public WorkflowDto(){
		super();
	}

	public LocalDate getCacnelledDate() {
		return cacnelledDate;
	}

	public PhaseCompletionDto getCurrentPhaseCompletion() {
		return currentPhaseCompletion;
	}

	public Long getId() {
		return id;
	}

	public List<PhaseCompletionDto> getPhaseCompletions() {
		return phaseCompletions;
	}

	public LocalDate getPhaseTargetDate() {
		return phaseTargetDate;
	}

	public ReleaseEntryDto getRelease() {
		return release;
	}

	public Rom getRom() {
		return rom;
	}

	public LocalDate getTargetImplementationDate() {
		return targetImplementationDate;
	}

	public WorkflowType getType() {
		return type;
	}

	public void setCacnelledDate(LocalDate cacnelledDate) {
		this.cacnelledDate = cacnelledDate;
	}

	public void setCurrentPhaseCompletion(PhaseCompletionDto currentPhaseCompletion) {
		this.currentPhaseCompletion = currentPhaseCompletion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhaseCompletions(List<PhaseCompletionDto> phaseCompletions) {
		this.phaseCompletions = phaseCompletions;
	}

	public void setPhaseTargetDate(LocalDate phaseTargetDate) {
		this.phaseTargetDate = phaseTargetDate;
	}

	public void setRelease(ReleaseEntryDto release) {
		this.release = release;
	}

	public void setRom(Rom rom) {
		this.rom = rom;
	}

	public void setTargetImplementationDate(LocalDate targetImplementationDate) {
		this.targetImplementationDate = targetImplementationDate;
	}

	public void setType(WorkflowType type) {
		this.type = type;
	}
}
