package com.fdc.boarding.workflow.usecase.process;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class ProcessStatusRequest implements Serializable {
	private static final long 			serialVersionUID 	= 1L;
	private static final String			dateTimeFormat		= "yyyy-MM-dd HH:mm:ss";
	private String 						process;
	private String						status;
	private String						correlationId;
	private String						utcDateTime;
	
	public ProcessStatusRequest(){
		super();
	}

	public ProcessStatusRequest(String process, String status, String correlationId, DateTime utcDateTime) {
		super();
		this.process 		= process;
		this.status 		= status;
		this.correlationId 	= correlationId;
		this.utcDateTime 	= utcDateTime.toString( dateTimeFormat );
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public String getProcess() {
		return process;
	}

	public String getStatus() {
		return status;
	}

	public String getUtcDateTime() {
		return utcDateTime;
	}

	public DateTime getUtcDateTimeAsDateTime() {
		return DateTime.parse( utcDateTime, DateTimeFormat.forPattern( dateTimeFormat ));
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUtcDateTimeFromDateTime(DateTime utcDateTime) {
		this.utcDateTime = utcDateTime.toString( dateTimeFormat );
	}

	public void setUtcDateTime(String utcDateTime) {
		this.utcDateTime = utcDateTime;
	}
}
