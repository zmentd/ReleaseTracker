package com.fdc.boarding.workflow.persistence.process;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;

import com.fdc.boarding.workflow.domain.process.IProcessStatus;


@Entity
@Audited
@Table( name = "BRDWRKFLW_PROCESS_STATUS",
		indexes = {
		},
		uniqueConstraints = {
		}
)
public class ProcessStatusEntity implements Serializable, IProcessStatus {
	private static final long 			serialVersionUID = 1L;

	@Id
	@TableGenerator( name="process_status_sequence" 
				   , table="BRDWRFLW_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="PROCESS_STATUS_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="process_status_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Column( name = "CORRELATIONID" )
	private String						correlationId;
	
	@Column( name = "PROCESS" )
	private String						process;
	
	@Column( name = "PROCESS_ENTRY_DATETIME" )
	@Type( type="com.fdc.boarding.core.persistence.type.UTCPersistentDateTime", parameters = {@Parameter( name="databaseZone", value="UTC" )} )
	private DateTime					processEntryDateTime;
	
	@Column( name = "PROCESS_COMPLETE_DATETIME" )
	@Type( type="com.fdc.boarding.core.persistence.type.UTCPersistentDateTime", parameters = {@Parameter( name="databaseZone", value="UTC" )} )
	private DateTime					processCompleteDateTime;
	
	@Column( name = "PROCESS_DURATION" )
	private Long						processDuration;
	
	@Column( name = "STATUS" )
	private String						status;
	
//	@Column( name = "STATUS_ENTRY_DATETIME" )
//	@Type( type="com.fdc.boarding.core.persistence.type.UTCPersistentDateTime", parameters = {@Parameter( name="databaseZone", value="UTC" )} )
//	private DateTime					statusEntryDateTime;
//	
//	@Column( name = "STATUS_COMPLETE_DATETIME" )
//	@Type( type="com.fdc.boarding.core.persistence.type.UTCPersistentDateTime", parameters = {@Parameter( name="databaseZone", value="UTC" )} )
//	private DateTime					statusCompleteDateTime;
//	
//	@Column( name = "STATUS_DURATION" )
//	private Long						statusDuration;
	
	public ProcessStatusEntity(){
		super();
	}
	
	@Override
	public DateTime getProcessCompleteDateTime() {
		return processCompleteDateTime;
	}

	@Override
	public String getCorrelationId() {
		return correlationId;
	}

	@Override
	public DateTime getProcessEntryDateTime() {
		return processEntryDateTime;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getProcess() {
		return process;
	}

	@Override
	public Long getProcessDuration() {
		return processDuration;
	}

	@Override
	public String getStatus() {
		return status;
	}

//	@Override
//	public DateTime getStatusEntryDateTime() {
//		return statusEntryDateTime;
//	}
//
//	@Override
//	public Long getStatusDuration() {
//		return statusDuration;
//	}

	@Override
	public void setProcessCompleteDateTime(DateTime completeDateTime) {
		this.processCompleteDateTime = completeDateTime;
	}

	@Override
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	@Override
	public void setProcessEntryDateTime(DateTime entryDateTime) {
		this.processEntryDateTime = entryDateTime;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setProcess(String process) {
		this.process = process;
	}

	@Override
	public void setProcessDuration(Long processDuration) {
		this.processDuration = processDuration;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

//	@Override
//	public void setStatusEntryDateTime(DateTime statusDateTime) {
//		this.statusEntryDateTime = statusDateTime;
//	}
//
//	@Override
//	public void setStatusDuration(Long statusDuration) {
//		this.statusDuration = statusDuration;
//	}
//
//	@Override
//	public DateTime getStatusCompleteDateTime() {
//		return statusCompleteDateTime;
//	}
//
//	@Override
//	public void setStatusCompleteDateTime(DateTime statusCompleteDateTime) {
//		this.statusCompleteDateTime = statusCompleteDateTime;
//	}

}
