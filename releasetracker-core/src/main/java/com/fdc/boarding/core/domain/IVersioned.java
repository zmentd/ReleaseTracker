package com.fdc.boarding.core.domain;

import org.joda.time.DateTime;

public interface IVersioned {
	   /**
	    * @return 
	    *    The effectiveTimestamp
	    */
	   public DateTime getEffectiveTimestamp(
	   );

	   /**
	    * @return 
	    *    The expirationTimestamp
	    */
	   public DateTime getExpirationTimestamp(
	   );

	   /**
	    * @param effectiveTimestamp 
	    *    The effectiveTimestamp to set
	    */
	   public void setEffectiveTimestamp( 
	     DateTime                          effectiveTimestamp 
	   );

	   /**
	    * @param expirationTimestamp 
	    *    The expirationTimestamp to set
	    */
	   public void setExpirationTimestamp( 
	     DateTime                          expirationTimestamp 
	   );

}
