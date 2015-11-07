package com.fdc.boarding.releasetracker.usecase;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractResponse implements Serializable, IServiceResponse {
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

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#addValidationFailure(com.fdc.boarding.releasetracker.usecase.ValidationFailure)
	 */
	@Override
	public void addValidationFailure( ValidationFailure failure ){
		if( failures == null ){
			failures = new HashSet<>();
		}
		failures.add( failure );
	}
	
	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#isSuccess()
	 */
	@Override
	public boolean isSuccess() {
		return success;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#setSuccess(boolean)
	 */
	@Override
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#getFailures()
	 */
	@Override
	public Set<ValidationFailure> getFailures() {
		return failures;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.usecase.IServiceResponse#setFailures(java.util.Set)
	 */
	@Override
	public void setFailures(Set<ValidationFailure> failures) {
		this.failures = failures;
	}

}
