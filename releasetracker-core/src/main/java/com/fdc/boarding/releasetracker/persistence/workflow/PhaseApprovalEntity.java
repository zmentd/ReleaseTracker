package com.fdc.boarding.releasetracker.persistence.workflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApproval;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;
import com.fdc.boarding.releasetracker.persistence.common.CommentEntity;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;

@Entity
@Audited
@Table( name = "RT_WORKFLOW_PHASE_APRVL",
		indexes = {
		@Index( columnList="PHASE_TYPE, PHASE_CMPLT_ID", unique = false ),
		@Index( columnList="PHASE_CMPLT_ID", unique = false )
		}
)
public class PhaseApprovalEntity extends AbstractAuditedEntity<Long> implements Serializable, IPhaseApproval {

	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="phase_approval_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="PHASE_APRVL_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="phase_approval_sequence")
	@Column( name = "ID" )
	private Long						id;

	@Column( name = "APRVL_CMPLT_DAYS" )
	private Integer						approvalCompletionDays;
	
	@Column( name = "PHASE_TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.workflow.PhaseType" )
	}
	)
	private PhaseType					phaseType;

	@Column( name = "APRVL_END_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					approvalComplete;

	@Column( name = "REJECT_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					rejectComplete;
	
	@Column( name = "APRVL_START_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					approvalStart;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = PhaseCompletionEntity.class )
	@JoinColumn( name = "PHASE_CMPLT_ID" )	
	private IPhaseCompletion			phaseCompletion;

	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = PhaseApprovalTypeEntity.class )
	@JoinColumn( name = "PHASE_APRVL_TYPE_ID" )	
	private IPhaseApprovalType			phaseApprovalType;

	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = UserEntity.class, cascade={CascadeType.ALL} )
	@JoinColumn( name = "APRVD_BY_ID" )	
	private IUser						approvedBy;
	
	@NotAudited
	@OrderBy( "commentDate DESC")
	@ManyToMany( targetEntity=CommentEntity.class, cascade={CascadeType.ALL} )
    @JoinTable(
        name="RT_WORKFLOW_PHASE_APRVL_COMMENTS",
        joinColumns=@JoinColumn(name="PHASE_APRVL_ID"),
        inverseJoinColumns=@JoinColumn(name="COMMENT_ID")
    )
	private List<IComment>				comments		= new ArrayList<>();
	
	public PhaseApprovalEntity(){
		super();
	}
	
	@Override
	public void addComment( IComment comment ){
		comments.add(comment);
	}
	
	@Override
	public LocalDate getApprovalComplete() {
		return approvalComplete;
	}

	@Override
	public Integer getApprovalCompletionDays() {
		return approvalCompletionDays;
	}

	@Override
	public LocalDate getApprovalStart() {
		return approvalStart;
	}

	@Override
	public IUser getApprovedBy() {
		return approvedBy;
	}

	@Override
	public List<IComment> getComments() {
		return comments;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public IPhaseApprovalType getPhaseApprovalType() {
		return phaseApprovalType;
	}

	@Override
	public IPhaseCompletion getPhaseCompletion() {
		return phaseCompletion;
	}

	@Override
	public PhaseType getPhaseType() {
		return phaseType;
	}

	@Override
	public LocalDate getRejectComplete() {
		return rejectComplete;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void removeComment( IComment comment ){
		comments.remove(comment);
	}

	@Override
	public void setApprovalComplete(LocalDate approvalComplete) {
		this.approvalComplete = approvalComplete;
		if( approvalComplete == null ){
			approvalCompletionDays = null;
		}
		else{
			approvalCompletionDays	= Days.daysBetween( approvalStart, this.approvalComplete ).getDays();	
		}
	}

	@Override
	public void setApprovalCompletionDays(Integer approvalCompletionDays) {
		this.approvalCompletionDays = approvalCompletionDays;
	}

	@Override
	public void setApprovalStart(LocalDate approvalStart) {
		this.approvalStart = approvalStart;
	}

	@Override
	public void setApprovedBy(IUser approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public void setComments(List<IComment> comments) {
		this.comments = comments;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setPhaseApprovalType(IPhaseApprovalType approvalType) {
		this.phaseApprovalType = approvalType;
	}

	@Override
	public void setPhaseCompletion(IPhaseCompletion phaseCompletion) {
		this.phaseCompletion = phaseCompletion;
	}

	@Override
	public void setPhaseType(PhaseType phaseType) {
		this.phaseType = phaseType;
	}

	@Override
	public void setRejectComplete(LocalDate rejectComplete) {
		this.rejectComplete = rejectComplete;
	}
}
