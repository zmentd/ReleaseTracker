package com.fdc.boarding.workflow.usecase.report;

import java.util.ArrayList;
import java.util.List;

public class ProcessReportModel {
	
	private List<ProcessStatusModel>	processModels;
	
	public ProcessReportModel(){
		super();
		processModels	= new ArrayList<>();
	}

	public void addProcessModel( ProcessStatusModel model ){
		processModels.add( model );
	}
	
	public List<ProcessStatusModel> getProcessModels() {
		return processModels;
	}

	public void setProcessModels(List<ProcessStatusModel> model) {
		this.processModels = model;
	}
}
