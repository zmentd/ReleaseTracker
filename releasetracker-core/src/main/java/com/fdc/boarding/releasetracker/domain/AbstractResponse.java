package com.fdc.boarding.releasetracker.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractResponse  implements Serializable {
	private static final long 			serialVersionUID 		= 1L;

	private boolean						success;
	private String						message;
	private Set<ValidationFailure>		failures;
	
	public AbstractResponse() {
		super();
	}
	
	public AbstractResponse(boolean success, String message) {
		super();
		this.success 	= success;
		this.message 	= message;
	}

	public void addValidationFailure( ValidationFailure failure ){
		if( failures == null ){
			failures = new HashSet<>();
		}
		failures.add( failure );
	}
	
	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Set<ValidationFailure> getFailures() {
		return failures;
	}

	public void setFailures(Set<ValidationFailure> failures) {
		this.failures = failures;
	}

}
