package com.fdc.boarding.releasetracker.usecase.release;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;

public class ReleaseCalendarResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int							year;
	private List<IReleaseEntry>			releaseEntries;

	public ReleaseCalendarResponse(){
		super();
	}

	public List<IReleaseEntry> getReleaseEntries() {
		return releaseEntries;
	}

	public int getYear() {
		return year;
	}
	
	public void setReleaseEntries(List<IReleaseEntry> releaseEntries) {
		this.releaseEntries = releaseEntries;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
}
