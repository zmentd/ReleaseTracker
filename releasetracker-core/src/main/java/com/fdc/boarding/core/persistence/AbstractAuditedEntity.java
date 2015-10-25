package com.fdc.boarding.core.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.jaxb.DateTimeAdapter;

@MappedSuperclass
@EntityListeners( AuditedListener.class )
public abstract class AbstractAuditedEntity<T extends Object> extends AbstractEntity<T> implements IAuditable, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "LAST_MODIFIED_BY")
	private String		lastModifiedBy;

	@Version
	@XmlJavaTypeAdapter( DateTimeAdapter.class )
	@Column(name = "LAST_MODIFIED_DATE")
	@Type( type="com.fdc.boarding.core.persistence.type.UTCPersistentDateTime", parameters = {@Parameter( name="databaseZone", value="UTC" )} )
	private DateTime	lastModifiedDate;
	
	public AbstractAuditedEntity() {
	}

	@Override
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Override
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	@Override
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy	= lastModifiedBy;
	}

	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate	= lastModifiedDate;
	}
}
