package com.fdc.boarding.workflow.usecase.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.fdc.boarding.workflow.domain.process.IProcessStatus;
import com.fdc.boarding.workflow.persistence.report.IProcessReportPersistenceGateway;

public class ProcessReportUC 
{
	
	@Inject
	private IProcessReportPersistenceGateway	gateway;

	public ProcessReportModel createReport(){
		ProcessReportModel								report;
		ProcessStatusModel								psm;
		Map<String, Map<String, List<IProcessStatus>>>	map;
		Map<String, List<IProcessStatus>>				nested;
		List<IProcessStatus>							lnested;
		String											lastStatus		= null;
		long											currentCount	= 0;
		long											totalCount		= 0;
		long											minDuration		= 0;
		long											maxDuration		= 0;
		long											totalDuration	= 0;
		boolean											complete		= false;
		
		
		PeriodFormatter formatter = new PeriodFormatterBuilder()
	     .appendDays()
	     .appendSuffix("d")
	     .appendHours()
	     .appendSuffix("h")
	     .appendMinutes()
	     .appendSuffix("m")
	     .appendSeconds()
	     .appendSuffix("s")
	     .toFormatter();
		report	= new ProcessReportModel();
		map		= getReportData();
		for( String p : map.keySet() ){
			nested	= map.get( p );
			for( String c : nested.keySet() ){
				lnested	= nested.get( c );
				for( IProcessStatus s : lnested ){
					if( s.getProcessCompleteDateTime() != null ){
						totalCount++;
						lastStatus		= s.getStatus();
						complete 		= true;
						totalDuration	= totalDuration + s.getProcessDuration();
						minDuration		= ( s.getProcessDuration() < minDuration ? s.getProcessDuration() : minDuration );
						maxDuration		= ( s.getProcessDuration() > maxDuration ? s.getProcessDuration() : maxDuration );
					}
				}
				if( !complete ){
					currentCount++;
				}
				complete	= false;
			}
			psm	= new ProcessStatusModel();
			psm.setProcess( p );
			psm.setCurrentCount( currentCount );
			psm.setMaxDuration( formatter.print( new Duration( maxDuration ).toPeriod() ) );
			psm.setMinDuration( formatter.print( new Duration( minDuration ).toPeriod() ) );
			psm.setTotalCount( totalCount );
			psm.setAverageDuration( formatter.print( new Duration( new Double( totalDuration / totalCount ).longValue() ).toPeriod() ) );	
			if( lastStatus != null ){
				psm.setStatus( lastStatus );
			}
			report.addProcessModel( psm );
			lastStatus		= null;
			currentCount	= 0;
			totalCount		= 0;
			minDuration		= 0;
			maxDuration		= 0;
			totalDuration	= 0;
		
		}
		
		return report;
	}
	
	protected Map<String, Map<String, List<IProcessStatus>>> getReportData(){
		List<IProcessStatus>							entities;
		Map<String, Map<String, List<IProcessStatus>>>	map;
		Map<String, List<IProcessStatus>>				nested;
		List<IProcessStatus>							lnested;
		
		entities	= gateway.find();
		map			= new HashMap<>();
		
		for( IProcessStatus stat : entities ){
			if( !map.containsKey( stat.getProcess() ) ){
				nested	= new HashMap<>();
				lnested	= new ArrayList<>();
				lnested.add( stat );
				nested.put( stat.getCorrelationId(), lnested );
				map.put( stat.getProcess(), nested );
			}
			else
			{
				nested	= map.get( stat.getProcess() );
				if( nested.containsKey( stat.getCorrelationId() ) ){
					nested.get( stat.getCorrelationId() ).add( stat );
				}
				else{
					lnested	= new ArrayList<>();
					lnested.add( stat );
					nested.put( stat.getCorrelationId(), lnested );
				}
			}
		}
		
		return map;
	}
}
