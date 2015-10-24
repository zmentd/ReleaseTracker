package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.security.IUser;

public interface IPhaseApproval extends IAuditable, IEntity<Long> {
	public abstract void addComment(IComment comment);
	public abstract LocalDate getApprovalComplete();
	public abstract Integer getApprovalCompletionDays();
	public abstract LocalDate getApprovalStart();
	public abstract IUser getApprovedBy();
	public abstract List<IComment> getComments();
	public abstract Long getId();
	public abstract IPhaseApprovalType getPhaseApprovalType();
	public abstract PhaseType getPhaseType();
	public abstract LocalDate getRejectComplete();
	public abstract void removeComment(IComment comment);
	public abstract void setApprovalComplete(LocalDate approvalComplete);
	public abstract void setApprovalCompletionDays(Integer approvalCompletionDays);
	public abstract void setApprovalStart(LocalDate approvalStart);
	public abstract void setApprovedBy(IUser approvedBy);
	public abstract void setComments(List<IComment> comments);
	public abstract void setId(Long id);
	public abstract void setPhaseApprovalType(IPhaseApprovalType approvalType);
	public abstract void setPhaseType(PhaseType phaseType);
	public abstract void setRejectComplete(LocalDate rejectComplete);
	public abstract void setPhaseCompletion(IPhaseCompletion phaseCompletion);
	public abstract IPhaseCompletion getPhaseCompletion();
}