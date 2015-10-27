/**
 * 
 */
package com.fdc.boarding.releasetracker.test.gateway.xcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.joda.time.Days;
import org.junit.Test;

import com.fdc.boarding.core.service.EntityReaderSvc;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.gateway.excel.IdeaPriorityListReader;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;

/**
 * @author f6mkw7d
 *
 */
public class TestXcelReader extends AbstractPersistenceTest {

	protected static String				fullFile	= "src\\test\\resources\\Concept Priority List New.xlsm";
	private static String				testFile	= "src\\test\\resources\\Concept Priority List Test.xls";

    private IdeaPriorityListReader		reader;
    private EntityReaderSvc				ereader;
    
	@Override
	public void injectServices() {
		ereader	= CDIContext.getInstance().getBean( EntityReaderSvc.class );
		reader	= CDIContext.getInstance().getBean( IdeaPriorityListReader.class );
	}

    @Test
	public void testCON053117_FullTest(){
		InputStream						stream;
		ExpectedIdea					expected;

		expected	= new ExpectedIdea();
		expected.setReader( ereader );
		try {
			stream		= new FileInputStream( testFile );
			System.out.println( "Path:" + new File( "." ).getAbsolutePath() );
			reader.setStartRow( 2 );
			reader.setEndRow( 5 );
			reader.importFile( stream );
			
			expected.expectFrom( "CON-053117" )
					.name( "General Profile Updates - Memphis Platform" )
					.priority( 10 )
					.umbrellaIdea( false )
					.descriptionStartsWith( "BAU Data Table" )
					.projectNumber( null )
					.amendmentNumber( null )
					.createDate( "9/30/2015" )
					.clarityRom( Rom.Small )
					.rom( Rom.Small )
					.overallRom( Rom.Small )
					.assignedTo( "Mukesh Kumar3" )
					.originalRequestor( "Lou Falanga" )
					.demandOwner( "Elaine Schaefer" )
					.solutionArchitect( "Mukesh Kumar3" )
					.targetImplementationDate( "10/8/2016" )
					.phaseTargetDate( "10/15/2015" )
					.currentPhaseEntryDate( "10/9/2015" )
					.currentStatusEntryDate( "10/12/2015" )
					.currentPhase( "HLE" )
					.currentStatus( "Pending Approval" )
					.release( null )
					.withCurrentPhaseCompletion()
  						.entryDate( "10/9/2015" )
						.completionDate( null )
						.expectedCompletionDate( "7/30/2016" )
						.phase( "HLE" )
						.daysInPhase( null )
						.daysFromExpectedCompletion( null )
						.withCurrentStatusCompletion()
	  						.entryDate( "10/12/2015" )
							.completionDate( null )
							.status( "Pending Approval" )
							.daysInStatus( null )
							.parent()
					.parent()
					.withPhaseCompletions()
						.size( 3 )
						.next()
							.entryDate( "10/9/2015" )
							.completionDate( null )
							.expectedCompletionDate( "7/30/2016" )
							.phase( "HLE" )
							.daysInPhase( null )
							.daysFromExpectedCompletion( null )
							.withStatusCompletions()
								.size( 1 )
								.with(  "HLE"  )
									.entryDate( "10/12/2015" )
									.completionDate( null )
									.status( "Pending Approval" )
									.daysInStatus( null )
									.parent()
								.parent()
							.parent()
						.next()
						    .entryDate( "10/5/2015" )
							.completionDate( "10/9/2015" )
							.expectedCompletionDate( "7/20/2016" )
							.phase( "HLAD" )
							.daysInPhase( 4 )
							.daysFromExpectedCompletion( Days.daysBetween( ExpectedIdea.toDate( "10/5/2015" ), ExpectedIdea.toDate( "7/20/2016" ) ).getDays() )
							.withStatusCompletions()
								.size( 1 )
								.with( "HLAD" )
								    .entryDate( "10/5/2015" )
									.completionDate( "10/9/2015" )
									.status( "Complete" )
									.daysInStatus( 4 )
									.parent()
								.parent()
							.parent()
						.next()
							.entryDate( "9/30/2015" )
							.completionDate( "10/5/2015" )
							.expectedCompletionDate( "7/5/2016" )
							.phase( "Requirements" )
							.daysInPhase( 5 )
							.daysFromExpectedCompletion( Days.daysBetween( ExpectedIdea.toDate( "9/30/2015" ), ExpectedIdea.toDate( "7/5/2016" ) ).getDays() )
							.withStatusCompletions()
								.size( 1 )
								.with( "Requirements" )
									.entryDate( "9/30/2015" )
									.completionDate( "10/5/2015" )
									.status( "Complete" )
									.daysInStatus( 5 )
									.parent()
								.parent()
							.parent()
						.parent()
					.withTeamImpacts()
						.size( 2 )
						.with( "Solutions Architect" )
							.team( "Solutions Architect" )
							.withCurrentPhaseCompletion()
		  						.entryDate( "10/9/2015" )
								.completionDate( null )
								.expectedCompletionDate( "7/30/2016" )
								.phase( "HLE" )
								.daysInPhase( null )
								.daysFromExpectedCompletion( null )
								.withCurrentStatusCompletion()
			  						.entryDate( "10/12/2015" )
									.completionDate( null )
									.status( "Pending Approval" )
									.daysInStatus( null )
									.parent()
								.parent()
							.withPhaseCompletions()
								.size( 3 )
								.next()
									.entryDate( "10/9/2015" )
									.completionDate( null )
									.expectedCompletionDate( "7/30/2016" )
									.phase( "HLE" )
									.daysInPhase( null )
									.daysFromExpectedCompletion( null )
									.withStatusCompletions()
										.size( 1 )
										.with( "HLE" )
											.entryDate( "10/12/2015" )
											.completionDate( null )
											.status( "Pending Approval" )
											.daysInStatus( null )
											.parent()
										.parent()
									.parent()
								.next()
								    .entryDate( "10/5/2015" )
									.completionDate( "10/9/2015" )
									.expectedCompletionDate( "7/20/2016" )
									.phase( "HLAD" )
									.daysInPhase( 4 )
									.daysFromExpectedCompletion( Days.daysBetween( ExpectedIdea.toDate( "10/5/2015" ), ExpectedIdea.toDate( "7/20/2016" ) ).getDays() )
									.withStatusCompletions()
										.size( 1 )
										.with( "HLAD" )
										    .entryDate( "10/5/2015" )
											.completionDate( "10/9/2015" )
											.status( "Complete" )
											.daysInStatus( 4 )
											.parent()
										.parent()
									.parent()
								.next()
									.entryDate( "9/30/2015" )
									.completionDate( "10/5/2015" )
									.expectedCompletionDate( "7/5/2016" )
									.phase( "Requirements" )
									.daysInPhase( 5 )
									.daysFromExpectedCompletion( Days.daysBetween( ExpectedIdea.toDate( "9/30/2015" ), ExpectedIdea.toDate( "7/5/2016" ) ).getDays() )
									.withStatusCompletions()
										.size( 1 )
										.with( "Requirements" )
											.entryDate( "9/30/2015" )
											.completionDate( "10/5/2015" )
											.status( "Complete" )
											.daysInStatus( 5 )
											.parent()
										.parent()
									.parent()
								.parent()
							.parent()
						.with( "CMS Memphis" )
							.team( "CMS Memphis" )
							.withCurrentPhaseCompletion()
		  						.entryDate( "10/9/2015" )
								.completionDate( null )
								.expectedCompletionDate( "7/30/2016" )
								.phase( "HLE" )
								.daysInPhase( null )
								.daysFromExpectedCompletion( null )
								.withCurrentStatusCompletion()
			  						.entryDate( "10/12/2015" )
									.completionDate( null )
									.status( "Pending Approval" )
									.daysInStatus( null )
									.parent()
								.parent()
							.withPhaseCompletions()
								.size( 3 )
								.next()
									.entryDate( "10/9/2015" )
									.completionDate( null )
									.expectedCompletionDate( "7/30/2016" )
									.phase( "HLE" )
									.daysInPhase( null )
									.daysFromExpectedCompletion( null )
									.withStatusCompletions()
										.size( 1 )
										.with( "HLE" )
											.entryDate( "10/12/2015" )
											.completionDate( null )
											.status( "Pending Approval" )
											.daysInStatus( null )
											.parent()
										.parent()
									.parent()
								.next()
								    .entryDate( "10/5/2015" )
									.completionDate( "10/9/2015" )
									.expectedCompletionDate( "7/20/2016" )
									.phase( "HLAD" )
									.daysInPhase( 4 )
									.daysFromExpectedCompletion( Days.daysBetween( ExpectedIdea.toDate( "10/5/2015" ), ExpectedIdea.toDate( "7/20/2016" ) ).getDays() )
									.withStatusCompletions()
										.size( 1 )
										.with( "HLAD" )
										    .entryDate( "10/5/2015" )
											.completionDate( "10/9/2015" )
											.status( "Complete" )
											.daysInStatus( 4 )
											.parent()
										.parent()
									.parent()
								.next()
									.entryDate( "9/30/2015" )
									.completionDate( "10/5/2015" )
									.expectedCompletionDate( "7/5/2016" )
									.phase( "Requirements" )
									.daysInPhase( 5 )
									.daysFromExpectedCompletion( Days.daysBetween( ExpectedIdea.toDate( "9/30/2015" ), ExpectedIdea.toDate( "7/5/2016" ) ).getDays() )
									.withStatusCompletions()
										.size( 1 )
										.with( "Requirements" )
											.entryDate( "9/30/2015" )
											.completionDate( "10/5/2015" )
											.status( "Complete" )
											.daysInStatus( 5 )
											.parent()
										.parent()
									.parent()
								.parent()
							.parent()
						.parent()
						.withComments()
							.size( 7 )
							.next()
								.commentDate( "10/12/2015" )
								.startsWith( "Dev team" )
							.parent()
							.next()
								.commentDate( "10/9/2015" )
								.startsWith( "HLAD" )
							.parent()
							.next()
								.commentDate( "10/6/2015" )
								.startsWith( "Mukesh set" )
							.parent()
							.next()
								.commentDate( "10/5/2015" )
								.startsWith( "Mukesh placed" )
							.parent()
							.next()
								.commentDate( "10/5/2015" )
								.startsWith( "BRD" )
							.parent()
							.next()
								.commentDate( "10/5/2015" )
								.startsWith( "Mukesh" )
							.parent()
							.next()
								.commentDate( "9/30/2015" )
								.startsWith( "Added" )
							.parent()
						.parent()
						;
						
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

    @Test
	public void testCON051247_OnHold(){
		InputStream						stream;
		ExpectedIdea					expected;

		expected	= new ExpectedIdea();
		expected.setReader( ereader );
		
		try {
			stream		= new FileInputStream( testFile );
			System.out.println( "Path:" + new File( "." ).getAbsolutePath() );
			reader.setStartRow( 5 );
			reader.setEndRow( 7 );
			reader.importFile( stream );
			
			expected.expectFrom( "CON-051247" )
					.name( "Lvl2-CON-051977 Extend Sales tools to Support Needs-Based Selling" )
					.createDate( "5/15/2015" )
					.umbrellaIdea( true )
					.clarityRom( Rom.Large )
					.rom( Rom.Large )
					.overallRom( Rom.XLarge )
					.targetImplementationDate( "1/9/2016" )
					.phaseTargetDate( null )
					.currentPhaseEntryDate( "9/15/2015" )
					.currentStatusEntryDate( "9/15/2015" )
					.currentPhase( "Parked" )
					.currentStatus( "On Hold" )
					.release( "16.05" )
					.withCurrentPhaseCompletion()
  						.entryDate( "9/15/2015" )
						.completionDate( null )
						.expectedCompletionDate( null )
						.phase( "Parked" )
						.daysInPhase( null )
						.daysFromExpectedCompletion( null )
						.withCurrentStatusCompletion()
	  						.entryDate( "9/15/2015" )
							.completionDate( null )
							.status( "On Hold" )
							.parent()
						.parent()
					.withPhaseCompletions()
						.size( 5 )
						.next()
							.entryDate( "9/15/2015" )
							.completionDate( null )
							.phase( "Parked" )
							.withStatusCompletions()
								.size( 1 )
								.with( "Parked" )
									.status( "On Hold" )
									.entryDate( "9/15/2015" )
									.completionDate( null )
									.parent()
								.parent()
							.parent()
						.next()
							.entryDate( "9/24/2015" )
							.completionDate( null )
							.expectedCompletionDate( null )
							.phase( "Slotting" )
							.daysFromExpectedCompletion( null )
							.withStatusCompletions()
								.size( 1 )
								.with( "Slotting" )
									.status( "In Queue" )
									.entryDate( "9/24/2015" )
									.completionDate( null )
									.parent()
								.parent()
							.parent()
						.next()
							.entryDate( "7/30/2015" )
							.completionDate( "9/15/2015" )
							.phase( "HLE" )
							.parent()
						.next()
							.entryDate( "6/30/2015" )
							.completionDate( "7/30/2015" )
							.phase( "HLAD" )
							.parent()
						.next()
							.entryDate( "5/15/2015" )
							.completionDate( "6/30/2015" )
							.phase( "Requirements" )
							.parent()
						.parent()
					
					;
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
    }

    @Test
	public void testCON051247_DDD_Parked(){
		InputStream						stream;
		ExpectedIdea					expected;

		expected	= new ExpectedIdea();
		expected.setReader( ereader );
		
		try {
			stream		= new FileInputStream( testFile );
			System.out.println( "Path:" + new File( "." ).getAbsolutePath() );
			reader.setStartRow( 8 );
			reader.setEndRow( 10 );
			reader.importFile( stream );
			
			expected.expectFrom( "CON-037256" )
					.name( "CBO ACQ LEGIS Realtime Boarding TIN/TFN Validation" )
					.createDate( "12/26/2013" )
					.umbrellaIdea( false )
					.clarityRom( Rom.XXXLarge )
					.rom( Rom.Medium )
					.overallRom( Rom.XXXLarge )
					.targetImplementationDate( "8/1/2015" )
					.phaseTargetDate( null )
					.currentPhaseEntryDate( "6/10/2015" )
					.currentStatusEntryDate( "6/10/2015" )
					.currentPhase( "Parked" )
					.currentStatus( "On Hold" )
					.release( "15.08" )
					.withCurrentPhaseCompletion()
  						.entryDate( "6/10/2015" )
						.completionDate( null )
						.expectedCompletionDate( null )
						.phase( "Parked" )
						.daysInPhase( null )
						.daysFromExpectedCompletion( null )
						.withCurrentStatusCompletion()
	  						.entryDate( "6/10/2015" )
							.completionDate( null )
							.status( "On Hold" )
							.parent()
						.parent()
					.withPhaseCompletions()
						.size( 6 )
						.next()
							.entryDate( "6/10/2015" )
							.completionDate( null )
							.phase( "Parked" )
							.withStatusCompletions()
								.size( 1 )
								.with( "Parked" )
									.status( "On Hold" )
									.entryDate( "6/10/2015" )
									.completionDate( null )
									.parent()
								.parent()
							.parent()
						.next()
							.entryDate( "3/11/2014" )
							.completionDate( null )
							.expectedCompletionDate( "6/3/2015" )
							.phase( "DDD" )
							.daysFromExpectedCompletion( null )
							.withStatusCompletions()
								.size( 1 )
								.with( "DDD" )
									.status( "In Queue" )
									.entryDate( "3/11/2014" )
									.completionDate( null )
									.parent()
								.parent()
							.parent()
						.parent()
					
					;
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
    }

    @Test
	public void testCON051247_DDD_Approved(){
		InputStream						stream;
		ExpectedIdea					expected;

		expected	= new ExpectedIdea();
		expected.setReader( ereader );
		
		try {
			stream		= new FileInputStream( testFile );
			System.out.println( "Path:" + new File( "." ).getAbsolutePath() );
			reader.setStartRow( 10 );
			reader.setEndRow( 14 );
			reader.importFile( stream );
			
			expected.expectFrom( "CON-040793" )
					.name( "RSA - November (Fall) 2014 Release - MPA/boarding tools" )
					.createDate( "6/1/2014" )
					.umbrellaIdea( false )
					.clarityRom( Rom.Medium )
					.rom( Rom.Medium )
					.overallRom( Rom.Small )
					.targetImplementationDate( null )
					.phaseTargetDate( null )
					.currentPhaseEntryDate( "10/28/2014" )
					.currentStatusEntryDate( "10/28/2014" )
					.currentPhase( "Slotting" )
					.currentStatus( "Complete" )
					.release( "14.11" )
					.withCurrentPhaseCompletion()
  						.entryDate( "10/28/2014" )
						.completionDate( "11/14/2014" )
						.expectedCompletionDate( null )
						.phase( "Slotting" )
						.daysInPhase( 17 )
						.daysFromExpectedCompletion( null )
						.withCurrentStatusCompletion()
	  						.entryDate( "10/28/2014" )
							.completionDate( "11/14/2014" )
							.status( "Complete" )
							.parent()
						.parent()
					.withPhaseCompletions()
						.size( 4 )
						.next()
							.entryDate( "10/28/2014" )
							.completionDate( "11/14/2014" )
							.phase( "Slotting" )
							.withStatusCompletions()
								.size( 1 )
								.with( "Slotting" )
									.status( "Complete" )
									.entryDate( "10/28/2014" )
									.completionDate( "11/14/2014" )
									.parent()
								.parent()
							.parent()
						.next()
							.entryDate( "10/7/2014" )
							.completionDate( "10/13/2014" )
							.expectedCompletionDate( null )
							.phase( "HLE" )
							.daysFromExpectedCompletion( null )
							.withStatusCompletions()
								.size( 1 )
								.with( "HLE" )
									.status( "Complete" )
									.entryDate( "10/7/2014" )
									.completionDate( "10/13/2014" )
									.parent()
								.parent()
							.parent()
						.parent()
					
					;
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
    }
}
