package com.fdc.boarding.releasetracker.persistence.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.security.IUserPreferences;

@Entity
@Table( name = "RT_USER_PREFS" )
public class UserPreferencesEntity extends AbstractAuditedEntity<Long> implements Serializable, IUserPreferences{
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator( name="user_prefs_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="USER_PREFS_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="user_prefs_sequence")
	@Column( name = "ID" )
	private Long			id;

	@Column( name = "DATE_FORMAT" )
	private String 			dateFormat;
	
	@Column( name = "TIME_FORMAT" )
	private String			timeFormat;
	
	@Column( name = "TIMEZONE" )
	private String			timezone;
	
	@Column( name = "DAYLIGHT_SAVINGS" )
	private Boolean			dayLightSavings;
	
	public UserPreferencesEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public String getTimezone() {
		return timezone;
	}

	public Boolean isDayLightSavings() {
		return dayLightSavings;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setDayLightSavings(Boolean dayLightSavings) {
		this.dayLightSavings = dayLightSavings;
	}
}
