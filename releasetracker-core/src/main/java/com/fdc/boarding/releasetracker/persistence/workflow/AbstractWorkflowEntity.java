package com.fdc.boarding.releasetracker.persistence.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.persistence.common.CommentEntity;
import com.fdc.boarding.releasetracker.persistence.release.ReleaseEntryEntity;

@Audited
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="DISC",
    				  discriminatorType=DiscriminatorType.STRING
)
@Table( name = "RT_WORKFLOW",
		indexes = {
		@Index( columnList="CUR_PHASE_CMPLT_ID", unique = false ),
		@Index( columnList="REL_ID", unique = false )
		}
)
public abstract class AbstractWorkflowEntity extends AbstractAuditedEntity<Long> implements IWorkflow{
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="workflow_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="WORKFLOW_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="workflow_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Column( name = "TARGT_IMPL_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					targetImplementationDate;

	@Column( name = "PHASE_TARGET_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					phaseTargetDate;

	@Column( name = "CANCELED_DATE" )
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	private LocalDate					cacnelledDate;
	
	@Column( name = "ROM" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.common.Rom" )
	}
	)
	private Rom							rom;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = PhaseCompletionEntity.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL} )
	@JoinColumn( name = "CUR_PHASE_CMPLT_ID" )
	private IPhaseCompletion			currentPhaseCompletion;
	
	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@ManyToOne( targetEntity = ReleaseEntryEntity.class, fetch = FetchType.LAZY )
	@JoinColumn( name = "REL_ID" )	
	private IReleaseEntry				release;

	@NotAudited
	@OrderBy( "phaseIndex DESC" )
	@OneToMany( mappedBy = "workflow", targetEntity = PhaseCompletionEntity.class, cascade={CascadeType.ALL} )
	private List<IPhaseCompletion>		phaseCompletions	= new ArrayList<>();

	@NotAudited
	@OrderBy( "commentDate DESC")
	@ManyToMany( targetEntity=CommentEntity.class, cascade={CascadeType.ALL} )
    @JoinTable(
        name="RT_WORKFLOW_COMMENTS",
        joinColumns=@JoinColumn(name="WORKFLOW_ID"),
        inverseJoinColumns=@JoinColumn(name="COMMENT_ID")
    )
	private List<IComment>				comments		= new ArrayList<>();
	
	public AbstractWorkflowEntity(){
		super();
	}

	@Override
	public void addComment( IComment comment ){
		comments.add(comment);
	}

	@Override
	public void addPhaseCompletion( IPhaseCompletion phaseCompletion ){
		phaseCompletions.add( phaseCompletion );	
	}

	@Override
	public LocalDate getCacnelledDate() {
		return cacnelledDate;
	}

	@Override
	public List<IComment> getComments() {
		return comments;
	}
	
	@NotNull
	@Override
	public IPhaseCompletion getCurrentPhaseCompletion() {
		return currentPhaseCompletion;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public List<IPhaseCompletion> getPhaseCompletions() {
		return phaseCompletions;
	}
	
	@Override
	public LocalDate getPhaseTargetDate() {
		return phaseTargetDate;
	}

	@Override
	public IReleaseEntry getRelease() {
		return release;
	}

	@Override
	public Rom getRom() {
		return rom;
	}

	@Override
	public LocalDate getTargetImplementationDate() {
		return targetImplementationDate;
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
	public void removePhaseCompletion( IPhaseCompletion phaseCompletion ){
		phaseCompletions.remove( phaseCompletion );	
	}

	@Override
	public void setCacnelledDate(LocalDate cacnelledDate) {
		this.cacnelledDate = cacnelledDate;
	}

	@Override
	public void setComments(List<IComment> comments) {
		this.comments = comments;
	}

	@Override
	public void setCurrentPhaseCompletion(IPhaseCompletion currentPhaseCompletion) {
		this.currentPhaseCompletion = currentPhaseCompletion;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setPhaseCompletions(List<IPhaseCompletion> phaseCompletions) {
		this.phaseCompletions = phaseCompletions;
	}

	@Override
	public void setPhaseTargetDate(LocalDate phaseTargetDate) {
		this.phaseTargetDate = phaseTargetDate;
	}

	@Override
	public void setRelease(IReleaseEntry release) {
		this.release = release;
	}

	@Override
	public void setRom(Rom rom) {
		this.rom = rom;
	}

	@Override
	public void setTargetImplementationDate(LocalDate targetImplementationDate) {
		this.targetImplementationDate = targetImplementationDate;
	}

}
