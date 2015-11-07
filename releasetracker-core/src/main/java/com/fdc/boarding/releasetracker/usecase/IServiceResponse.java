package com.fdc.boarding.releasetracker.usecase;

import java.util.Set;

public interface IServiceResponse {

	public abstract void addValidationFailure(ValidationFailure failure);

	public abstract String getMessage();

	public abstract boolean isSuccess();

	public abstract void setMessage(String message);

	public abstract void setSuccess(boolean success);

	public abstract Set<ValidationFailure> getFailures();

	public abstract void setFailures(Set<ValidationFailure> failures);

}