package com.fdc.boarding.releasetracker.usecase;

import java.io.Serializable;

public class ValidationFailure implements Serializable {
	private static final long 			serialVersionUID = 1L;
	
	private String						message;
	private String						path;
	private String						validator;
	
	public ValidationFailure(){
		super();
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public String getValidator() {
		return validator;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}
}
