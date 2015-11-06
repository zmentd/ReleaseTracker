package com.fdc.boarding.releasetracker.persistence.workflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;

@Entity
@Table( name = "RT_WORKFLOW_PHASE_COMPLETION",
		indexes = {
		@Index( columnList="ENTRY_DATE", unique = false ),
		@Index( columnList="WORKFLOW_ID", unique = false ),
		@Index( columnList="CUR_STATUS_CMPLT_ID", unique = false )
		}
)
public class PhaseCompletionEntity extends AbstractAuditedEntity<Long> implements Serializable, IPhaseCompletion {

	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="phase_completion_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="PHASE_CMPLT_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="phase_completion_sequence")
	@Column( name = "ID" )
	private Long						id;

	@Column( name = "PHASE_SEQ" )
	private int							phaseIndex;
	
	@Column( name = "DAYS_IN_PHASE" )
	private Integer						daysInPhase;
	
	@Column( name = "DAYS_FROM_EXPCTD" )
	private Integer						daysFromExpectedCompletion;
	
	@Column( name = "ENTRY_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					entryDate;
	
	@Column( name = "CMPLT_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					completionDate;
	
	@Column( name = "EXPCTD_CMPLT_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					expectedCompletionDate;
	
	@ManyToOne( targetEntity = PhaseEntity.class )
	@JoinColumn( name = "PHASE_ID" )	
	private IPhase						phase;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = StatusCompletionEntity.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL} )
	@JoinColumn( name = "CUR_STATUS_CMPLT_ID" )	
	private IStatusCompletion			currentStatusCompletion;
	
	@ManyToOne( targetEntity = AbstractWorkflowEntity.class )
	@JoinColumn( name = "WORKFLOW_ID" )	
	private IWorkflow					workflow;
	
	@OneToMany( mappedBy = "phaseCompletion", targetEntity = PhaseApprovalEntity.class )
	private Set<IPhaseApproval>			phaseApprovals;

	@NotAudited
	@OrderBy( "lastModifiedDate DESC")
	@OneToMany( mappedBy = "phaseCompletion", targetEntity = StatusCompletionEntity.class, cascade={CascadeType.ALL} )
	private List<IStatusCompletion>		statusCompletions	= new ArrayList<>();
	
	public PhaseCompletionEntity(){
		super();
	}

	@Override
	public void addPhaseApproval( IPhaseApproval phaseApproval ){
		phaseApprovals.add(phaseApproval);
	}

	@Override
	public void addStatusCompletion( IStatusCompletion statusCompletion ){
		statusCompletions.add( statusCompletion );	
	}

	@Override
	public LocalDate getCompletionDate() {
		return completionDate;
	}

	@NotNull
	@Override
	public IStatusCompletion getCurrentStatusCompletion() {
		return currentStatusCompletion;
	}

	@Override
	public Integer getDaysFromExpectedCompletion() {
		return daysFromExpectedCompletion;
	}

	@Override
	public Integer getDaysInPhase() {
		return daysInPhase;
	}

	@NotNull
	@Override
	public LocalDate getEntryDate() {
		return entryDate;
	}

	@Override
	public LocalDate getExpectedCompletionDate() {
		return expectedCompletionDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@NotNull
	@Override
	public IPhase getPhase() {
		return phase;
	}

	@Override
	public Set<IPhaseApproval> getPhaseApprovals() {
		return phaseApprovals;
	}

	@Override
	public int getPhaseIndex() {
		return phaseIndex;
	}

	@Override
	public List<IStatusCompletion> getStatusCompletions() {
		return statusCompletions;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public IWorkflow getWorkflow() {
		return workflow;
	}

	@Override
	public void removePhaseApproval( IPhaseApproval phaseApproval ){
		phaseApprovals.remove(phaseApproval);
	}

	@Override
	public void removeStatusCompletion( IStatusCompletion statusCompletion ){
		statusCompletions.remove( statusCompletion );	
	}

	@Override
	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	@Override
	public void setCurrentStatusCompletion(IStatusCompletion currentStatusCompletion) {
		this.currentStatusCompletion = currentStatusCompletion;
	}

	@Override
	public void setDaysFromExpectedCompletion(Integer daysFromExpectedCompletion) {
		this.daysFromExpectedCompletion = daysFromExpectedCompletion;
	}

	@Override
	public void setDaysInPhase(Integer daysInPhase) {
		this.daysInPhase = daysInPhase;
	}

	@Override
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public void setExpectedCompletionDate(LocalDate expectedCompletionDate) {
		this.expectedCompletionDate = expectedCompletionDate;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setPhase(IPhase phase) {
		this.phase 		= phase;
		this.phaseIndex	= phase.getIndex();
	}

	@Override
	public void setPhaseApprovals(Set<IPhaseApproval> phaseApprovals) {
		this.phaseApprovals = phaseApprovals;
	}

	@Override
	public void setPhaseIndex(int phaseIndex) {
		this.phaseIndex = phaseIndex;
	}

	@Override
	public void setStatusCompletions(List<IStatusCompletion> statusCompletions) {
		this.statusCompletions = statusCompletions;
	}

	@Override
	public void setWorkflow(IWorkflow workflow) {
		this.workflow = workflow;
	}
}
