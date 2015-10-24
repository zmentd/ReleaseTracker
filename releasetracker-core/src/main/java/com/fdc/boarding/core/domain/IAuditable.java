/**
 * 
 */
package com.fdc.boarding.core.domain;

import java.io.Serializable;

import org.joda.time.DateTime;

public interface IAuditable extends Serializable {
	public abstract String getLastModifiedBy();
	public abstract DateTime getLastModifiedDate();
	public abstract void setLastModifiedBy(String lastModifiedBy);
	public abstract void setLastModifiedDate(DateTime lastModifiedDate);
}
