package com.fdc.boarding.releasetracker.usecase.release;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fdc.boarding.releasetracker.usecase.AbstractRequest;

public class ReleaseCalendarRequest extends AbstractRequest implements Serializable{
	private static final long 			serialVersionUID = 1L;

	@NotNull
	private Integer						year;
	
	public ReleaseCalendarRequest(){
		super();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
