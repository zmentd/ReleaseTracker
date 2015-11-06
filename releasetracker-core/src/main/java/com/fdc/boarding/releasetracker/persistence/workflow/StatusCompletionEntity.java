package com.fdc.boarding.releasetracker.persistence.workflow;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;

@Entity
@Table( name = "RT_WORKFLOW_STATUS_COMPLETION",
		indexes = {
		@Index( columnList="ENTRY_DATE", unique = false ),
		@Index( columnList="PHASE_CMPLT_ID", unique = false )
		}
)
public class StatusCompletionEntity extends AbstractAuditedEntity<Long> implements Serializable, IStatusCompletion {

	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="status_completion_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="STATUS_CMPLT_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="status_completion_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Column( name = "DAYS_IN_STATUS" )
	private Integer						daysInStatus;
	
	@Column( name = "ENTRY_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					entryDate;
	
	@Column( name = "CMPLT_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					completionDate;
	
	@ManyToOne( targetEntity = StatusEntity.class )
	@JoinColumn( name = "STATUS_ID" )	
	private IStatus						status;
	
	@ManyToOne( targetEntity = PhaseCompletionEntity.class )
	@JoinColumn( name = "PHASE_CMPLT_ID" )	
	private IPhaseCompletion			phaseCompletion;

	public StatusCompletionEntity(){
		super();
	}
	
	@Override
	public LocalDate getCompletionDate() {
		return completionDate;
	}

	@Override
	public Integer getDaysInStatus() {
		return daysInStatus;
	}

	@Override
	public LocalDate getEntryDate() {
		return entryDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public IPhaseCompletion getPhaseCompletion() {
		return phaseCompletion;
	}

	@NotNull
	@Override
	public IStatus getStatus() {
		return status;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	@Override
	public void setDaysInStatus(Integer daysInStatus) {
		this.daysInStatus = daysInStatus;
	}

	@Override
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setPhaseCompletion(IPhaseCompletion phaseCompletion) {
		this.phaseCompletion = phaseCompletion;
	}

	@Override
	public void setStatus(IStatus status) {
		this.status = status;
	}
}
