package com.fdc.boarding.releasetracker.persistence.idea;

import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

public class MilestoneKeyCache {
	private Long 						releaseId;
	private String 						romAbbr;
	private PhaseType 					phaseType;
	
	
	public MilestoneKeyCache(Long releaseId, String romAbbr, PhaseType phaseType) {
		super();
		this.releaseId = releaseId;
		this.romAbbr = romAbbr;
		this.phaseType = phaseType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilestoneKeyCache other = (MilestoneKeyCache) obj;
		if (phaseType != other.phaseType)
			return false;
		if (releaseId == null) {
			if (other.releaseId != null)
				return false;
		} else if (!releaseId.equals(other.releaseId))
			return false;
		if (romAbbr == null) {
			if (other.romAbbr != null)
				return false;
		} else if (!romAbbr.equals(other.romAbbr))
			return false;
		return true;
	}
	public PhaseType getPhaseType() {
		return phaseType;
	}
	public Long getReleaseId() {
		return releaseId;
	}
	public String getRomAbbr() {
		return romAbbr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((phaseType == null) ? 0 : phaseType.hashCode());
		result = prime * result
				+ ((releaseId == null) ? 0 : releaseId.hashCode());
		result = prime * result + ((romAbbr == null) ? 0 : romAbbr.hashCode());
		return result;
	}
	public void setPhaseType(PhaseType phaseType) {
		this.phaseType = phaseType;
	}
	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}
	public void setRomAbbr(String romAbbr) {
		this.romAbbr = romAbbr;
	}
	
	
}
