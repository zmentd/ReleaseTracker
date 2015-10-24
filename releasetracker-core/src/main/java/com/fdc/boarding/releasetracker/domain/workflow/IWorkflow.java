package com.fdc.boarding.releasetracker.domain.workflow;

import java.util.List;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;

public interface IWorkflow extends IAuditable, IEntity<Long> {
	public abstract void addComment(IComment comment);
	public abstract void addPhaseCompletion(IPhaseCompletion phaseCompletion);
	public abstract LocalDate getCacnelledDate();
	public abstract List<IComment> getComments();
	public abstract IPhaseCompletion getCurrentPhaseCompletion();
	public abstract Long getId();
	public abstract IIdea getIdea();
	public abstract List<IPhaseCompletion> getPhaseCompletions();
	public abstract LocalDate getPhaseTargetDate();
	public abstract IReleaseEntry getRelease();
	public abstract Rom getRom();
	public abstract LocalDate getTargetImplementationDate();
	public abstract void removeComment(IComment comment);
	public abstract void removePhaseCompletion(IPhaseCompletion phaseCompletion);
	public abstract void setCacnelledDate(LocalDate cacnelledDate);
	public abstract void setComments(List<IComment> comments);
	public abstract void setCurrentPhaseCompletion(IPhaseCompletion currentPhaseCompletion);
	public abstract void setId(Long id);
	public abstract void setPhaseCompletions(List<IPhaseCompletion> phaseCompletions);
	public abstract void setPhaseTargetDate(LocalDate phaseTargetDate);
	public abstract void setRelease(IReleaseEntry release);
	public abstract void setRom(Rom rom);
	public abstract void setTargetImplementationDate(LocalDate targetImplementationDate);
}