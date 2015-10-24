package com.fdc.boarding.releasetracker.persistence.release;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.release.IMilestone;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;

@Entity
@Cacheable( true )
@Table( name = "RT_RELEASE_ENTRY",
		indexes = {
		@Index( columnList="RELEASE_DATE", unique = true )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "RELEASE_DATE" )
		}
)
public class ReleaseEntryEntity extends AbstractAuditedEntity<Long> implements Serializable, IReleaseEntry{
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="release_entry_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="RELEASE_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="release_entry_sequence")
	@Column( name = "ID" )
	private Long			id;

	@Column( name = "IS_MINOR" )
	private Boolean			minor;
	
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "RELEASE_DATE" )
	private LocalDate		releaseDate;
	
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "LOCK_DOWN_DATE" )
	private LocalDate		lockDownDate;
	
	@Type( type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
	@Column( name = "LAST_CAT_REV_DATE" )
	private LocalDate		lastCatRevDate;
	
	@OrderColumn( name = "SEQ" )
	@OneToMany( mappedBy = "releaseEntry", targetEntity = MilestoneEntity.class, fetch = FetchType.EAGER )
	private List<IMilestone>	milestones;

	public ReleaseEntryEntity() {
		super();
	}

	@Override
	public void addMilestone( IMilestone milestone ){
		milestones.add(milestone);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public LocalDate getLastCatRevDate() {
		return lastCatRevDate;
	}
	
	@Override
	public LocalDate getLockDownDate() {
		return lockDownDate;
	}

	@Override
	public List<IMilestone> getMilestones() {
		return milestones;
	}

	@Override
	public Boolean getMinor() {
		return minor;
	}

	@Override
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void removeMilestone( IMilestone milestone ){
		milestones.remove(milestone);
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setLastCatRevDate(LocalDate lastCatRevDate) {
		this.lastCatRevDate = lastCatRevDate;
	}

	@Override
	public void setLockDownDate(LocalDate lockDownDate) {
		this.lockDownDate = lockDownDate;
	}

	@Override
	public void setMilestones(List<IMilestone> milestones) {
		this.milestones = milestones;
	}

	@Override
	public void setMinor(Boolean minor) {
		this.minor = minor;
	}

	@Override
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
}
