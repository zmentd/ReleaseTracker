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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.workflow.ApprovalType;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseApprovalType;

@Entity
@Table( name = "RT_WORKFLOW_PHASE_APRVL_TYPE",
		indexes = {
		@Index( columnList="NAME", unique = false ),
		@Index( columnList="PHASE_ID", unique = false ),
		@Index( columnList="SEQ", unique = false )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = { "NAME", "PHASE_ID" } )
		}
)
public class PhaseApprovalTypeEntity extends AbstractAuditedEntity<Long> implements Serializable, IPhaseApprovalType {

	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="phase_approval_type_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="PHASE_APRVL_TYPE_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="phase_approval_type_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Column( name = "NAME" )
	private String						name;
	
	@Column( name = "SEQ" )
	private Integer						sequence;
	
	@Column( name = "TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.workflow.ApprovalType" )
	}
	)
	private ApprovalType				type;

	@ManyToOne( targetEntity = PhaseEntity.class )
	@JoinColumn( name = "PHASE_ID" )	
	private IPhase						phase;
	
	public PhaseApprovalTypeEntity(){
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPhase getPhase() {
		return phase;
	}

	@Override
	public Integer getSequence() {
		return sequence;
	}

	@Override
	public ApprovalType getType() {
		return type;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPhase(IPhase phase) {
		this.phase = phase;
	}

	@Override
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public void setType(ApprovalType type) {
		this.type = type;
	}
}
