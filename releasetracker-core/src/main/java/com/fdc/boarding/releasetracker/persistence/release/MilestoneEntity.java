package com.fdc.boarding.releasetracker.persistence.release;

import java.io.Serializable;

import javax.persistence.Cacheable;
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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.release.IMilestone;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;

@Entity
@Cacheable( true )
@Table( name = "RT_MILESTONE",
		indexes = {
		@Index( columnList="NAME", unique = false )
		}
)
public class MilestoneEntity extends AbstractAuditedEntity<Long> implements Serializable, IMilestone{
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator( name="milestone_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="MILESTONE_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="milestone_sequence")
	@Column( name = "ID" )
	private Long				id;

	@Column( name = "NAME" )
	private String				name;

	@Column( name = "SEQ" )
	private int					index;

	@Column( name = "DSC" )
	private String				description;

	@Column( name = "PHASE_TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.workflow.PhaseType" )
	}
	)
	private PhaseType			phaseType;

	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "DUE_DATE" )
	private LocalDate			dueDate;

	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "LARGE_DUE_DATE" )
	private LocalDate			largeDueDate;

	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "MED_DUE_DATE" )
	private LocalDate			mediumDueDate;

	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "SMALL_DUE_DATE" )
	private LocalDate			smallDueDate;

	@ManyToOne( targetEntity = ReleaseEntryEntity.class )
	@JoinColumn( name = "RELEASE_ENTRY_ID" )	
	private IReleaseEntry		releaseEntry;

	public MilestoneEntity() {
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public LocalDate getDueDate() {
		return dueDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public LocalDate getLargeDueDate() {
		return largeDueDate;
	}

	@Override
	public LocalDate getMediumDueDate() {
		return mediumDueDate;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PhaseType getPhaseType() {
		return phaseType;
	}

	@Override
	public IReleaseEntry getReleaseEntry() {
		return releaseEntry;
	}

	@Override
	public LocalDate getSmallDueDate() {
		return smallDueDate;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setLargeDueDate(LocalDate largeDueDate) {
		this.largeDueDate = largeDueDate;
	}
	
	@Override
	public void setMediumDueDate(LocalDate mediumDueDate) {
		this.mediumDueDate = mediumDueDate;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPhaseType(PhaseType phaseType) {
		this.phaseType = phaseType;
	}

	@Override
	public void setReleaseEntry(IReleaseEntry releaseEntry) {
		this.releaseEntry = releaseEntry;
	}

	@Override
	public void setSmallDueDate(LocalDate smallDueDate) {
		this.smallDueDate = smallDueDate;
	}
}
