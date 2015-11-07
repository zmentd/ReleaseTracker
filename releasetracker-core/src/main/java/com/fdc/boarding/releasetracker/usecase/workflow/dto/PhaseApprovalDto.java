package com.fdc.boarding.releasetracker.usecase.workflow.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.release.MilestoneType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.usecase.common.dto.CommentDto;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class PhaseApprovalDto extends AbstractAuditedDto implements Serializable {
	private static final long 	serialVersionUID = 1L;

	public static PhaseApprovalDto from( IPhaseApproval entity ){
		PhaseApprovalDto							dto		= null;
		List<CommentDto>							list;
		
		if( entity != null ){
			dto	= new PhaseApprovalDto();
			dto.setPhaseCompletion( PhaseCompletionDto.from( entity.getPhaseCompletion() ) );
			dto.setPhaseApprovalType( PhaseApprovalTypeDto.from( entity.getPhaseApprovalType() ) );
			dto.setApprovedBy( UserDto.from( entity.getApprovedBy() ) );
			list	= new ArrayList<>();
			for( IComment c : entity.getComments() ){
				list.add( CommentDto.from( c ) );
			}
			dto.setComments( list );
		}
		
		return dto;
	}

	protected static void locals( PhaseApprovalDto dto, IPhaseApproval entity ){
		dto.setId( entity.getId() );
		dto.setApprovalCompletionDays( entity.getApprovalCompletionDays() );
		dto.setMilestoneType( entity.getMilestoneType() );
		dto.setApprovalComplete( entity.getApprovalComplete() );
		dto.setRejectComplete( entity.getRejectComplete() );
		dto.setApprovalStart( entity.getApprovalStart() );
		dto.setLastModifiedBy( entity.getLastModifiedBy() );
		dto.setLastModifiedDate( entity.getLastModifiedDate() );
	}

	public static PhaseApprovalDto partial( IPhaseApproval entity ){
		PhaseApprovalDto				dto		= null;
		
		if( entity != null ){
			dto	= new PhaseApprovalDto();
			locals( dto, entity );
		}
		
		return dto;
	}
	
	private Long						id;
	private Integer						approvalCompletionDays;
	private MilestoneType				milestoneType;
	private LocalDate					approvalComplete;
	private LocalDate					rejectComplete;
	private LocalDate					approvalStart;
	private PhaseCompletionDto			phaseCompletion;
	private PhaseApprovalTypeDto		phaseApprovalType;
	private UserDto						approvedBy;
	private List<CommentDto>			comments;

	public PhaseApprovalDto(){
		super();
	}

	public Long getId() {
		return id;
	}

	public Integer getApprovalCompletionDays() {
		return approvalCompletionDays;
	}

	public MilestoneType getMilestoneType() {
		return milestoneType;
	}

	public LocalDate getApprovalComplete() {
		return approvalComplete;
	}

	public LocalDate getRejectComplete() {
		return rejectComplete;
	}

	public LocalDate getApprovalStart() {
		return approvalStart;
	}

	public PhaseCompletionDto getPhaseCompletion() {
		return phaseCompletion;
	}

	public PhaseApprovalTypeDto getPhaseApprovalType() {
		return phaseApprovalType;
	}

	public UserDto getApprovedBy() {
		return approvedBy;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setApprovalCompletionDays(Integer approvalCompletionDays) {
		this.approvalCompletionDays = approvalCompletionDays;
	}

	public void setMilestoneType(MilestoneType milestoneType) {
		this.milestoneType = milestoneType;
	}

	public void setApprovalComplete(LocalDate approvalComplete) {
		this.approvalComplete = approvalComplete;
	}

	public void setRejectComplete(LocalDate rejectComplete) {
		this.rejectComplete = rejectComplete;
	}

	public void setApprovalStart(LocalDate approvalStart) {
		this.approvalStart = approvalStart;
	}

	public void setPhaseCompletion(PhaseCompletionDto phaseCompletion) {
		this.phaseCompletion = phaseCompletion;
	}

	public void setPhaseApprovalType(PhaseApprovalTypeDto phaseApprovalType) {
		this.phaseApprovalType = phaseApprovalType;
	}

	public void setApprovedBy(UserDto approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

}
