package com.fdc.boarding.workflow.persistence.report;

import java.util.List;

import com.fdc.boarding.workflow.domain.process.IProcessStatus;

public interface IProcessReportPersistenceGateway {

	public abstract List<IProcessStatus> find();

}