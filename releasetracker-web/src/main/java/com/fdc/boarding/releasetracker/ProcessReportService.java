package com.fdc.boarding.releasetracker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fdc.boarding.workflow.usecase.report.ProcessReportModel;
import com.fdc.boarding.workflow.usecase.report.ProcessReportUC;

@Path("/processreport")
@RequestScoped
public class ProcessReportService {

	@Inject
	private ProcessReportUC				usecase;
	
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public ProcessReportModel processReport() {
        return usecase.createReport();
    }

}
