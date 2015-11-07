package com.fdc.boarding.releasetracker.usecase.idea;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;

public class IdeaStatusResponse implements Serializable{
	private static final long 			serialVersionUID = 1L;
	
	private IWorkflow					workflow;
	private LocalDate					releaseDate;
	private LocalDate					milestoneDueDate;
	private String						milestoneName;
	private DaysToTargetStatus			phaseStatus;
	private DaysToTargetStatus			milestoneStatus;
	private Long						releaseEntryId;
	private Long						milestoneId;
	private int							daysToMilestone;
	private int							daysToTarget;
	private int							daysToRelease;
	private DaysToTargetStatus			releaseStatus;
	private DaysToTargetStatus			status;
	
	public IdeaStatusResponse(){
		super();
	}

	public IWorkflow getWorkflow() {
		return workflow;
	}

	public int getDaysToMilestone() {
		return daysToMilestone;
	}

	public int getDaysToRelease() {
		return daysToRelease;
	}

	public int getDaysToTarget() {
		return daysToTarget;
	}

	public LocalDate getMilestoneDueDate() {
		return milestoneDueDate;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public DaysToTargetStatus getMilestoneStatus() {
		return milestoneStatus;
	}

	public DaysToTargetStatus getPhaseStatus() {
		return phaseStatus;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public Long getReleaseEntryId() {
		return releaseEntryId;
	}

	public DaysToTargetStatus getReleaseStatus() {
		return releaseStatus;
	}

	public DaysToTargetStatus getStatus() {
		return status;
	}

	public void setWorkflow(IWorkflow idea) {
		this.workflow = idea;
	}

	public void setDaysToMilestone(int daysToMilestone) {
		this.daysToMilestone = daysToMilestone;
	}

	public void setDaysToRelease(int daysToRelease) {
		this.daysToRelease = daysToRelease;
	}

	public void setDaysToTarget(int daysToTarget) {
		this.daysToTarget = daysToTarget;
	}

	public void setMilestoneDueDate(LocalDate milestoneDueDate) {
		this.milestoneDueDate = milestoneDueDate;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public void setMilestoneStatus(DaysToTargetStatus milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
		setStatus( milestoneStatus );
	}

	public void setPhaseStatus(DaysToTargetStatus phaseStatus) {
		this.phaseStatus = phaseStatus;
		setStatus( phaseStatus );
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setReleaseEntryId(Long releaseEntryId) {
		this.releaseEntryId = releaseEntryId;
	}

	public void setReleaseStatus(DaysToTargetStatus releaseStatus) {
		this.releaseStatus = releaseStatus;
		setStatus( releaseStatus );
	}

	public void setStatus(DaysToTargetStatus status) {
		if( this.status == null ){
			this.status = status;
		}
		switch( status ){
			case Green:
				switch( this.status ){
					case Green:
						break;
					case Yellow:
						break;
					case Red:
						break;
					default:
						this.status	= status;
						break;
				}
				break;
			case Yellow:
				switch( this.status ){
				case Green:
					this.status	= status;
					break;
				case Yellow:
					break;
				case Red:
					break;
				default:
					this.status	= status;
					break;
			}
				break;
			case Red:
				switch( this.status ){
				case Green:
					this.status	= status;
					break;
				case Yellow:
					this.status	= status;
					break;
				case Red:
					break;
				default:
					this.status	= status;
					break;
			}
				break;
			default:
				break;
		}
	}
}
