package com.fdc.boarding.releasetracker.usecase.release;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.domain.release.MilestoneType;

public class MilestoneByRom implements Serializable{
	private static final long 	serialVersionUID = 1L;

	private Long				id;
	private String				name;
	private int					index;
	private String				description;
	private Rom					rom;
	private MilestoneType		milestoneType;
	private LocalDate			dueDate;
	private IReleaseEntry		releaseEntry;

	public MilestoneByRom(){
		super();
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public Long getId() {
		return id;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public MilestoneType getMilestoneType() {
		return milestoneType;
	}

	public IReleaseEntry getReleaseEntry() {
		return releaseEntry;
	}

	public Rom getRom() {
		return rom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMilestoneType(MilestoneType milestoneType) {
		this.milestoneType = milestoneType;
	}

	public void setReleaseEntry(IReleaseEntry releaseEntry) {
		this.releaseEntry = releaseEntry;
	}

	public void setRom(Rom rom) {
		this.rom = rom;
	}
	
}
