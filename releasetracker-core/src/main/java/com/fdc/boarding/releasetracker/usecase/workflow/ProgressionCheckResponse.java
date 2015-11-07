package com.fdc.boarding.releasetracker.usecase.workflow;

import com.fdc.boarding.releasetracker.usecase.AbstractResponse;

public class ProgressionCheckResponse extends AbstractResponse {
	private static final long 			serialVersionUID = 1L;

	private boolean						canProgress;
	
	public ProgressionCheckResponse(){
		super();
	}

	public boolean isCanProgress() {
		return canProgress;
	}

	public void setCanProgress(boolean canProgress) {
		this.canProgress = canProgress;
	}
}
