package com.fdc.boarding.releasetracker.domain;

import java.io.Serializable;

import org.joda.time.DateTime;

public class AbstractAuditedDto extends AbstractDto implements Serializable {
	private static final long 			serialVersionUID = 1L;
	
	private String						lastModifiedBy;
	private DateTime					lastModifiedDate;

	public AbstractAuditedDto(){
		super();
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
