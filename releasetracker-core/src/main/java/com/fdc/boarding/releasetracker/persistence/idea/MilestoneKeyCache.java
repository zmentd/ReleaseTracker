package com.fdc.boarding.releasetracker.persistence.idea;

import com.fdc.boarding.releasetracker.domain.release.MilestoneType;

public class MilestoneKeyCache {
	private Long 						releaseId;
	private String 						romAbbr;
	private MilestoneType 				milestoneType;
	
	
	public MilestoneKeyCache(Long releaseId, String romAbbr, MilestoneType milestoneType) {
		super();
		this.releaseId 		= releaseId;
		this.romAbbr 		= romAbbr;
		this.milestoneType 	= milestoneType;
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
		if (milestoneType != other.milestoneType)
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
	public MilestoneType getMilestoneType() {
		return milestoneType;
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
				+ ((milestoneType == null) ? 0 : milestoneType.hashCode());
		result = prime * result
				+ ((releaseId == null) ? 0 : releaseId.hashCode());
		result = prime * result + ((romAbbr == null) ? 0 : romAbbr.hashCode());
		return result;
	}
	public void setMilestoneType(MilestoneType milestoneType) {
		this.milestoneType = milestoneType;
	}
	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}
	public void setRomAbbr(String romAbbr) {
		this.romAbbr = romAbbr;
	}
	
	
}
