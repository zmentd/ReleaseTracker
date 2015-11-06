package com.fdc.boarding.releasetracker.gateway.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.service.EntityReaderSvc;
import com.fdc.boarding.core.transaction.annotation.Transactional;
import com.fdc.boarding.releasetracker.domain.common.CommentType;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.domain.release.MilestoneByRom;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.IIdeaWorkflow;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.domain.workflow.IStatusCompletion;
import com.fdc.boarding.releasetracker.domain.workflow.ITeamImpactWorkflow;
import com.fdc.boarding.releasetracker.domain.workflow.IWorkflow;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;
import com.fdc.boarding.releasetracker.domain.workflow.StatusType;
import com.fdc.boarding.releasetracker.gateway.idea.IIdeaPersistenceGateway;
import com.fdc.boarding.releasetracker.gateway.release.IReleasePersistenceGateway;
import com.fdc.boarding.releasetracker.persistence.common.CommentEntity;
import com.fdc.boarding.releasetracker.persistence.idea.IdeaEntity;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;
import com.fdc.boarding.releasetracker.persistence.team.TeamImpactEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.IdeaWorkflowEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.PhaseCompletionEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.StatusCompletionEntity;
import com.fdc.boarding.releasetracker.persistence.workflow.TeamImpactWorkflowEntity;

public class IdeaPriorityListReader {
	private static Pattern 				p1 			= Pattern.compile( "^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)[ :-](.*)" );
	private static Pattern 				p2 			= Pattern.compile( "^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])[ :-](.*)" );
	private static DateTimeFormatter	formatter 	= DateTimeFormat.forPattern("MM/dd/yyyy");

	@Inject
	private EntityReaderSvc					reader;
	
	@Inject
	private IReleasePersistenceGateway		releaseGateway;

    @Inject
    private IIdeaPersistenceGateway			gateway;

    private int								startRow	= 1;
    private int								endRow		= -1;
    private String							ideaNumber;
	private Map<String, ITeam>				teams		= new HashMap<>();
	private Map<String, IPhase>				phases		= new HashMap<>();
	private Map<String, IStatus>			statuses	= new HashMap<>();
	private Map<LocalDate, IReleaseEntry>	releases	= new HashMap<>();
	private Map<String, IUser>				users		= new HashMap<>();
	private List<LocalDate>					releaseDates = new ArrayList<>();
	
	public IdeaPriorityListReader() {
		super();
	}

	@Transactional
	public void importFile(InputStream is){
		ReaderResponse					response;

		response	= readFile( is );
		System.out.println( "Read complete." );
		gateway.insertIdeas( response );
		System.out.println( "Import complete." );
	}
	
	private void assertNotNull( LocalDate date, String msg ){
		if( date == null ){
			System.out.println( msg );
		}
	}
	
	private void createPhaseProgress( ReaderResponse response, IWorkflow workflow, Row row, CurrentPhaseStatus currentPs ){
		Cell 							cell;
		LocalDate						reqsAppr;
		LocalDate						hladCmplt;
		LocalDate						hleCmplt;
		LocalDate						hleAppr;
		LocalDate						dddAppr;
		LocalDate						sltgSbmt;
		LocalDate						sltgCfmd;
		LocalDate						phaseStrt;
		IPhase							phase;
		IPhase							current;
		int								phaseIndex;
		
		cell		= row.getCell( 22 );
		phaseStrt	= readDate( cell );
		
		cell		= row.getCell( 42 );
		reqsAppr	= readDate( cell );
		
		cell		= row.getCell( 43 );
		hladCmplt	= readDate( cell );
		
		cell		= row.getCell( 44 );
		hleCmplt	= readDate( cell );
		
		cell		= row.getCell( 46 );
		hleAppr		= readDate( cell );
		
		cell		= row.getCell( 48 );
		sltgSbmt	= readDate( cell );
		
		cell		= row.getCell( 49 );
		sltgCfmd	= readDate( cell );
		
		cell		= row.getCell( 51 );
		dddAppr		= readDate( cell );
		
		current	= currentPs.getPhase();
		if( current == null ){
			System.out.println( "Cannot progress phase, no current phase." );
			return;
		}
		phaseIndex	= current.getIndex();
		if( phaseIndex == 9 ){
			//
			// Parked
			//
			if( reqsAppr == null ){
				phaseIndex	= 1;	
			}
			if( reqsAppr != null ){
				phaseIndex	= 2;	
			}
			if( hladCmplt != null ){
				phaseIndex	= 3;	
			}
			if( hleCmplt != null ){
				phaseIndex	= 4;	
			}
			if( sltgCfmd != null ){
				phaseIndex	= 5;	
			}
		}
		if( phaseIndex > 0 ){
			//
			// Requirements
			//
			phase	= phases.get( "Requirements" );
			assertNotNull( workflow.getIdea().getCreateDate(), workflow.getIdea().getIdeaNumber() + " Phase is in Requirements without an idea create date. Using today's date." );
			
			progressTo( response, workflow, phase, workflow.getIdea().getCreateDate(), reqsAppr, phaseStrt, currentPs );
		}
		if( phaseIndex > 1 ){
			//
			// HLAD
			//
			phase	= phases.get( "HLAD" );
			assertNotNull( reqsAppr, workflow.getIdea().getIdeaNumber() + " Phase is past Requirements without Requirements approved date" );
			progressTo( response, workflow, phase, reqsAppr, hladCmplt, phaseStrt, currentPs );
		}
		if( phaseIndex > 2 ){
			//
			// HLE
			//
			phase	= phases.get( "HLE" );
			assertNotNull( hladCmplt, workflow.getIdea().getIdeaNumber() + " Phase is past HLAD without HLAD complete date" );
			progressTo( response, workflow, phase, hladCmplt, hleAppr, phaseStrt, currentPs );
		}
		if( phaseIndex > 3 ){
			//
			// Slotting
			//
			phase	= phases.get( "Slotting" );
			assertNotNull( sltgSbmt, workflow.getIdea().getIdeaNumber() + " Phase is past HLE without slotting submitted date" );
			progressTo( response, workflow, phase, sltgSbmt, sltgCfmd, phaseStrt, currentPs );
		}
		if( phaseIndex > 4 ){
			//
			// DDD
			//
			phase	= phases.get( "DDD" );
			assertNotNull( sltgCfmd, workflow.getIdea().getIdeaNumber() + " Phase is past Slotting without slotting confirmed date" );
			progressTo( response, workflow, phase, sltgCfmd, dddAppr, phaseStrt, currentPs );
		}
		if( current.getIndex() == 9 ){
			//
			// Parked
			//
			phase	= phases.get( "Parked" );
			assertNotNull( phaseStrt, workflow.getIdea().getIdeaNumber() + " Phase is Parked without a phase start date" );
			progressTo( response, workflow, phase, phaseStrt, null, phaseStrt, currentPs );
		}
		
		if( workflow.getCurrentPhaseCompletion() == null ){
			System.out.println( "No current phase completion determined for idea " + workflow.getIdea().getIdeaNumber() );
		}
	}
	
	private IReleaseEntry findRelease( LocalDate date ){
		IReleaseEntry					entry = null;
		
		if( date != null ){
			for( LocalDate ed : releaseDates ){
				if( date.getMonthOfYear() == ed.getMonthOfYear() && date.getYear() == ed.getYear() ){
					entry	= releases.get( ed );
					break;
				}
			}
		}
		if( entry == null ){
			System.out.println( "Unable to locate release for date " + date.toString( "MM/dd/yyyy" ) );
		}
		
		return entry;
	}
	
	private Rom getRomSize( String cplValue ){
		
		if( "XS: 0 - 50".equals( cplValue.trim() ) ){
			return Rom.XSmall;
		}
		else if( "S: 51 - 250".equals( cplValue.trim() ) ){
			return Rom.Small;
		}
		else if( "M: 251 - 500".equals( cplValue.trim() ) ){
			return Rom.Medium;
		}
		else if( "L: 501 - 999".equals( cplValue.trim() ) ){
			return Rom.Large;
		}      
		else if( "XL: 1000 - 2500".equals( cplValue.trim() ) ){
			return Rom.XLarge;
		}
		else if( "XXL: 2501 - 5000".equals( cplValue.trim() ) ){
			return Rom.XXLarge;
		}         
		else if( "XXXL: 5000+".equals( cplValue.trim() ) ){
			return Rom.XXXLarge;
		}
		
		return null;
	}
	
	private ITeam getTeam( String cplValue ){
		String							value;
		ITeam						team		= null;
		
		value	= cplValue;
		if( value == null || value.trim().isEmpty() ){
			return null;
		}
		if( value.contains( "\n" ) ){
			value	= value.split( "\n" )[0];
		}
		if( "CMS memphis".equalsIgnoreCase( value) ){
			value = "CMS Memphis";
		}

		if( teams.containsKey( value ) ){
			team	= teams.get( value );
		}
		else{
			System.out.println( "--------- > Unknown Team:" + value + ":" );
		}
		
		return team;
	}
	
	
	private IUser getUser( String userName ){
		IUser							user;
		String							scrubbed;
		String							first;
		String							last;
		String[]						name;
		
		if( userName == null || userName.isEmpty() || "N/A".equals( userName ) || "NA".equals( userName ) || "?".equals( userName ) || "TBD".equals( userName ) ){
			return null;
		}
		scrubbed	= scrubName( userName );
		user		= users.get( scrubbed );
		if( user == null ){
			name	= scrubbed.split( " " );
			user	= new UserEntity();
			if( name.length == 2 ){
				first	= name[0].trim();
				last	= name[1].trim();
				if( first != null && !first.isEmpty() && last != null && !last.isEmpty() ){
					user.setFirstName( first );
					user.setLastName( last );
					user.setLastModifiedBy( "42" );
				}
				users.put( scrubbed, user );
			}
			else{
				System.out.println( "----------> Unknown Name  :" + scrubbed );
				user.setFirstName( scrubbed );
				user.setLastModifiedBy( "42" );
				users.put( scrubbed, user );
			}
		}
		
		return user;
	}
	
	private String normalizeName( String userName ){
		String							scrubbed;
		String[]						name;
		String							norm		= null;
		
		if( userName == null || userName.isEmpty() || "N/A".equals( userName ) || "NA".equals( userName ) || "?".equals( userName ) || "TBD".equals( userName ) ){
			return null;
		}
		scrubbed	= userName;
		scrubbed	= scrubbed.trim().replace( "  ", " " );
		scrubbed	= scrubbed.replace( "(", "" );
		scrubbed	= scrubbed.replace( ")", "" );
		name		= scrubbed.split( " " );
		if( name.length == 2 ){
			if( scrubbed.contains( "," ) ){
				scrubbed	= scrubbed.replace( ",", " " );
				scrubbed	= scrubbed.trim().replace( "  ", " " );
				name		= scrubbed.split( " " );
				norm		= name[1].trim() + " " + name[0].trim();
			}
			else{
				norm		= name[0].trim() + " " + name[1].trim();
			}
		}
		else if( name.length == 3 ){
			if( scrubbed.contains( "," ) ){
				scrubbed	= scrubbed.replace( ",", "" );
				name		= scrubbed.split( " " );
				norm		= name[1].trim() + " " + name[0].trim();
			}
			else{
				norm		= name[0].trim() + " " + name[2].trim();
			}
		}
		else if( scrubbed.contains( "," ) ){
			name		= scrubbed.split( "," );
			if( name.length == 2 ){
				scrubbed	= scrubbed.replace( ",", " " );
				scrubbed	= scrubbed.trim().replace( "  ", " " );
				name		= scrubbed.split( " " );
				norm		= name[1].trim() + " " + name[0].trim();
			}
		}
		
		return norm;
	}
	
	private void progressTo( 
	  ReaderResponse 					response
	, IWorkflow 						workflow
	, IPhase 							phase
	, LocalDate 						entryDate
	, LocalDate 						completeDate
	, LocalDate 						statusEntryDate 
	, CurrentPhaseStatus 				current
	){
		IPhaseCompletion				pc			= null;
		IStatusCompletion				sc			= null;
    	MilestoneByRom					milestone	= null;
    	Rom								rom;
    	LocalDate						date;
		
    	if( entryDate == null ){
    		return;
    	}
		date	= workflow.getTargetImplementationDate();
		rom		= workflow.getRom();
		if( date != null && rom != null ){
			milestone	= releaseGateway.findMilestoneByTargetDate( date, rom, phase.getType() );
		}

		pc		= new PhaseCompletionEntity();
		pc.setCompletionDate( completeDate );
		pc.setEntryDate( entryDate );
		if( completeDate == null ){
			pc.setDaysInPhase( null );
		}
		else{
			pc.setDaysInPhase( Days.daysBetween( pc.getEntryDate(), pc.getCompletionDate() ).getDays() );
		}
		
		pc.setWorkflow( workflow );
		pc.setPhase( phase );
		if( milestone != null && milestone.getDueDate() != null ){
			pc.setExpectedCompletionDate( milestone.getDueDate() );
			if( completeDate != null ){
				pc.setDaysFromExpectedCompletion( Days.daysBetween( pc.getEntryDate(), pc.getExpectedCompletionDate() ).getDays() );
			}
			else{
				pc.setDaysFromExpectedCompletion( null );
			}
		}
		workflow.setCurrentPhaseCompletion( pc );
		workflow.addPhaseCompletion( pc );
		
		sc			= new StatusCompletionEntity();
		sc.setCompletionDate( completeDate );
		if( completeDate == null ){
			sc.setDaysInStatus( null );
			if( current.getStatus().getType().equals( StatusType.OnHold ) || 
					current.getStatus().getType().equals( StatusType.Cancelled ) ){
				if( !phase.getType().equals( PhaseType.Parked ) && !phase.getType().equals( PhaseType.Done ) ){
					sc.setStatus( statuses.get( "In Queue" ) );
					sc.setEntryDate( entryDate );
				}
				else{
					sc.setStatus( current.getStatus() );
					sc.setEntryDate( statusEntryDate );
				}
			}
			else{
				sc.setStatus( current.getStatus() );
				sc.setEntryDate( statusEntryDate );
			}
		}
		else{
			sc.setStatus( statuses.get( "Complete" ) );
			sc.setEntryDate( entryDate );
			sc.setDaysInStatus( Days.daysBetween( sc.getEntryDate(), sc.getCompletionDate() ).getDays() );
		}
		sc.setPhaseCompletion( pc );
		pc.setCurrentStatusCompletion( sc );
		pc.addStatusCompletion( sc );
	
		response.addPhaseCmplt( pc );

	}
	
	private void readChildIdea( ReaderResponse response, Row	row, IIdea idea ){
		Cell 							cell;
		String							value;
		String							phase;
		IUser							user;
		ITeamImpact						impact;
		ITeam							team;
		ITeamImpactWorkflow				workflow;
		LocalDate						last;
		LocalDate						date;
		CurrentPhaseStatus				current;
		
		current	= new CurrentPhaseStatus();
		cell	= row.getCell( 6 );
		value	= cell.getStringCellValue();
		team		= getTeam( value );
		if( team == null ){
			return;
		}
		impact		= new TeamImpactEntity();
		workflow	= new TeamImpactWorkflowEntity();
		impact.setTeam( getTeam( value ) );
		impact.setWorkflow( workflow );
		workflow.setTeamImpact( impact );
		impact.setIdea( idea );
		
		cell	= row.getCell( 16 );
		value	= cell.getStringCellValue();
		workflow.setRom( getRomSize(value ) );
		
		cell	= row.getCell( 19 );
		date	= readDate( cell );
		impact.getWorkflow().setTargetImplementationDate( date );

		cell	= row.getCell( 20 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		impact.setRomOwner( user );
		impact.setLastModifiedBy( "42" );
		
		cell	= row.getCell( 22 );
		date	= readDate( cell );
		current.setPhaseEntry( date );
		current.setStatusEntry( date );
		
		cell	= row.getCell( 21 );
		phase	= cell.getStringCellValue();
		setPhaseStatus( phase, impact.getWorkflow(), current );

		cell	= row.getCell( 23 );
		date	= readDate( cell );
		impact.getWorkflow().setPhaseTargetDate( date );

		cell	= row.getCell( 24 );
		last	= readDate( cell );

		cell	= row.getCell( 29 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		impact.getTeam().setDevelopmentManager( user );

		cell	= row.getCell( 30 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		impact.getTeam().setBusinessOwner( user );

		cell	= row.getCell( 32 );
		value	= null;
		try {
			value	= cell.getStringCellValue();
		} catch (Exception e) {
			System.out.println( "-----> Unexpected data at row " + row.getRowNum() + " and cell 32, expected a user."  );
		}
		if( value != null ){
			user	= getUser( value );
			impact.getTeam().setPortfolioManager( user );
		}
		workflow.setPhaseTargetDate( idea.getWorkflow().getPhaseTargetDate() );
		
		if( "Planned Effort Not Open".equals( phase ) ){
			impact.setPlannedEffortNotOpen( true );
		}
		else if( "Support Only".equals( phase ) ){
			impact.setSupportOnly( true );
		}
		else if( "No Impact".equals( phase ) ){
			impact.setNoImpact( true );
			impact.setNoImpactDate( last );
		}
		
		idea.addTeamImpact( impact );
		createPhaseProgress( response, impact.getWorkflow(), row, current );
	}
	
	private List<IComment> readComments( String raw, IUser user ){
		List<IComment>					list;
		String[] 						raws;
		Matcher 						matcher;
		String							sdate		= null;
		String							scomment	= null;
		LocalDate						date;
		IComment						comment;
		
		list	= new ArrayList<>();
		try {
			raws	= raw.split( "\n" );
			for( String rc : raws ){
				matcher = p1.matcher( rc );
				if( matcher.find() ){
					sdate	= matcher.group( 1 ) + "/" + matcher.group( 2 ) + "/" + matcher.group( 3 );
					scomment	= matcher.group( 5 ).trim();
					if( scomment.startsWith(":") ){
						scomment = scomment.replaceFirst( ":", "" );
					}
					scomment	= scomment.trim();
				}
				else{
					matcher = p2.matcher( rc );
					if( matcher.find() ){
						sdate	= matcher.group( 1 ) + "/" + matcher.group( 2 ) + "/2015";
						scomment	= matcher.group( 3 ).trim();
						if( scomment.startsWith(":") ){
							scomment = scomment.replaceFirst( ":", "" );
						}
						scomment	= scomment.trim();
					}
				}
				
				if( sdate != null && scomment != null ){
					date 	= formatter.parseLocalDate( sdate );
					comment	= new CommentEntity();
					comment.setComment( scomment );
					comment.setCommentDate( date.toDateTime( LocalTime.MIDNIGHT ) );
					comment.setUser( user );
					comment.setLastModifiedBy( "42" );
					comment.setCommentType( CommentType.Public );
					list.add( comment );
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return list;
	}
	
	private void readConfigData(){
		List<ITeam>						lteams;
		List<IPhase>					lphases;
		List<IStatus>					lstatuses;
		List<IReleaseEntry>				lentries;
		List<IUser>						lusers;
		
		try {
			lteams		= reader.find( ITeam.class );
			lphases		= reader.find( IPhase.class );
			lstatuses	= reader.find( IStatus.class );
			lentries	= reader.find( IReleaseEntry.class );
			lusers		= reader.find( IUser.class );
			for( ITeam team :lteams )
			{
				teams.put( team.getName(), team );	
			}
			for( IPhase phase : lphases )
			{
				phases.put( phase.getName(), phase );	
			}
			for( IStatus status : lstatuses )
			{
				statuses.put( status.getName(), status );	
			}
			for( IReleaseEntry release : lentries )
			{
				releases.put( release.getReleaseDate(), release );	
				releaseDates.add( release.getReleaseDate() );
				Collections.sort( releaseDates );
			}
			for( IUser user : lusers )
			{
				users.put( user.getFirstName() + " " + user.getLastName() , user );	
			}
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private LocalDate readDate( Cell cell ){
		String							value;
		Date							date;
		LocalDate						ldate	= null;
		
		try {
			if( cell.getCellType() == Cell.CELL_TYPE_STRING ){
				value	= cell.getStringCellValue();
				if( !"N/A".equalsIgnoreCase( value ) && !"NA".equalsIgnoreCase( value ) ){
					System.out.println( "---------> String formatted date:" + value );
				}
			}
			else{
				date	= cell.getDateCellValue();
				if( date != null ){
					ldate	= new LocalDate( date );	
				}
			}
		} catch (Exception e) {
			System.out.println( "---------> Not a date:" + cell.getStringCellValue() );
		}
		
		return ldate;
	}
	
	public ReaderResponse readFile( InputStream is ){
		ReaderResponse					response	= null;
		Workbook 						wb;
		Sheet 							sheet;
		Row 							row;
		Cell 							cell;
		String							con;
		IIdea							idea;
		Map<String, IIdea>				conMap;
		Map<String, Row>				rowMap;
		Map<String, String>				nameMap;
		String							rel;
		String							name;
		int								rowNum;
		int								pcount		= 0;
		int								ptotal		= 0;
		int								ccount		= 0;
		int								ctotal		= 0;
		IIdeaWorkflow					workflow;
		boolean							foundParent	= false;
		
		try {
			readConfigData();
			conMap		= new HashMap<>();
			rowMap		= new HashMap<>();
			nameMap		= new HashMap<>();
			wb 			= WorkbookFactory.create( is );
			sheet		= wb.getSheetAt( 0 );
			response	= new ReaderResponse();
			System.out.println( "Parsing " + ( sheet.getPhysicalNumberOfRows() - 1 ) + " rows.");
			for( int i = startRow; i < sheet.getPhysicalNumberOfRows(); i++ ){
				if( endRow == i ){
					break;
				}
				row 	= sheet.getRow( i );
				if( row == null ){
					System.out.println( "Stopping at row " + i );
					break;
				}
				rowNum	= row.getRowNum() + 1;	

				cell	= row.getCell( 9 );
				con		= cell.getStringCellValue().trim();
				if( ideaNumber != null ){
					if( ideaNumber.equals( con ) ){
						if( !foundParent ){
							foundParent = true;
						}
					}
					else{
						if( foundParent ){
							break;
						}
						else{
							continue;
						}
					}
				}				
				cell	= row.getCell( 1 );
				rel		= cell.getStringCellValue().trim();

				cell	= row.getCell( 7 );
				name	= cell.getStringCellValue().trim();
			
				if( "P".equalsIgnoreCase( rel ) ){
					ptotal++;
				}
				else if( "C".equalsIgnoreCase( rel ) ){
					ctotal++;
				}
					
				
				if( con.isEmpty() && rel.isEmpty() ){
					System.out.println( "Parsing completed at row " + rowNum + ".");
					break;
				}
				if( con.isEmpty() ){
					System.out.println( "-----> Entry with no idea number found at row " + rowNum + ", this row will be ignored. Relation marked as " + rel + " with the idea name " + name );
					continue;
				}

				if( "P".equalsIgnoreCase( rel ) ){
					if( nameMap.containsKey( name ) ){
						if( !con.equals( nameMap.get( name) ) ){
							System.out.println( "-----> Duplicate Idea name (" + name + ") found at row:" + rowNum + ", this idea number is " + con + " and previous idea number is " + nameMap.get( name ) );
						}
					}
					else{
						nameMap.put( name, con );
					}
				}
				
				if( !conMap.containsKey( con ) && "P".equalsIgnoreCase( rel ) ){
					idea		= new IdeaEntity();
					workflow	= new IdeaWorkflowEntity();
					idea.setWorkflow( workflow );
					conMap.put( con, idea );
					rowMap.put( con, row );
					idea.setLastModifiedBy( "42" );
					readParentIdea( response, row, idea );
					pcount++;
				}
				else{
					if( "C".equalsIgnoreCase( rel ) ){
						if( conMap.containsKey( con ) ){
							readChildIdea( response, row, conMap.get( con ) );
							ccount++;
						}
						else{
							System.out.println( "-----> Child idea row found without a parent:" + con );
						}
					}
					else{
						System.out.println( "-----> Found duplicate idea marked as a parent:" + con );
					}
				}
			}
			
			response.setIdeas( conMap.values() );
			response.setUsers( users.values() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( " Created " + pcount + " ideas out of " + ptotal + " rows marked as Relation P " );
		System.out.println( " Created " + ccount + " team impacts out of " + ctotal + " rows marked as Relation C " );
		return response;
	}
	
	public void setIdeaNumber(String ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	private void readParentIdea( ReaderResponse response, Row row, IIdea idea ){
		Cell 							cell;
		String							value;
		String							phase;
		double							num;
		LocalDate						date;
		LocalDate						last;
		IUser							user;
		CurrentPhaseStatus				current;
		
		current	= new CurrentPhaseStatus();
		cell	= row.getCell( 3 );
		num		= cell.getNumericCellValue();
		if( num > 0 ){
			idea.setPriority( ( int )num );
		}		
		
		cell	= row.getCell( 6 );
		value	= cell.getStringCellValue();
		if( "Top 11".equals( value ) ){
			idea.setIsUmbrellaIdea( true );
		}
		
		cell	= row.getCell( 7 );
		value	= cell.getStringCellValue();
		idea.setName( value );

		cell	= row.getCell( 9 );
		value	= cell.getStringCellValue();
		idea.setIdeaNumber( value );

		cell	= row.getCell( 10 );
		value	= cell.getStringCellValue();
		if( value != null && !value.isEmpty() ){
			idea.setProjectNumber( value );
		}

		cell	= row.getCell( 11 );
		value	= cell.getStringCellValue();
		if( value != null && !value.isEmpty() ){
			idea.setAmendmentNumber( value );
		}
		
		cell	= row.getCell( 13 );
		value	= cell.getStringCellValue();
		idea.setClarityRom( getRomSize(value ) );
		
		cell	= row.getCell( 15 );
		value	= cell.getStringCellValue();
		idea.setOverallRom( getRomSize(value ) );
		
		cell	= row.getCell( 16 );
		value	= cell.getStringCellValue();
		idea.getWorkflow().setRom( getRomSize(value ) );
		
		cell	= row.getCell( 19 );
		date	= readDate( cell );
		idea.getWorkflow().setTargetImplementationDate( date );
		
		cell	= row.getCell( 20 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		idea.setSolutionArchitect( user );
		
		cell	= row.getCell( 22 );
		date	= readDate( cell );
		current.setPhaseEntry( date );
		current.setStatusEntry( date );
		
		cell	= row.getCell( 21 );
		phase	= cell.getStringCellValue();
		setPhaseStatus( phase, idea.getWorkflow(), current );

		cell	= row.getCell( 23 );
		date	= readDate( cell );
		idea.getWorkflow().setPhaseTargetDate( date );

		cell	= row.getCell( 24 );
		last	= readDate( cell );
		
		cell	= row.getCell( 25 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		idea.setAssignedTo( user );
		
		cell	= row.getCell( 26 );
		value	= cell.getStringCellValue();
		idea.getWorkflow().setComments( readComments( value, idea.getSolutionArchitect() ) );
		
		cell	= row.getCell( 27 );
		value	= cell.getStringCellValue();
		if( value != null && !value.isEmpty() ){
			idea.setDescription( value );
		}
		
		cell	= row.getCell( 28 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		idea.setOriginalRequestor( user );

		cell	= row.getCell( 31 );
		value	= cell.getStringCellValue();
		user	= getUser( value );
		idea.setDemandOwner( user );
		
		cell	= row.getCell( 38 );
		date	= readDate( cell );
		if( date != null ){
			idea.setCreateDate( date );
		}
		else{
			System.out.println( "Idea " + idea.getIdeaNumber() + " does not have a create date, using today." );
			idea.setCreateDate( new LocalDate() );
		}
		cell	= row.getCell( 50 );
		date	= readDate( cell );
		if( date != null ){
			idea.getWorkflow().setRelease( findRelease( date ) );
		}
		if( "Cancelled".equals( phase ) ){
			idea.getWorkflow().setCacnelledDate( last );
		}
		createPhaseProgress( response, idea.getWorkflow(), row, current );
	}
	
	private String scrubName( String userName ){
		String							scrubbed;
	
		scrubbed	= userName.trim();
		scrubbed	= normalizeName( scrubbed );
		if( scrubbed == null ){
			scrubbed	= userName;
		}
		if( "Al".equalsIgnoreCase( userName ) ){
			scrubbed	= "Al Otto";
		}
		else if( "Manning, Tina/Moody, Teresa".equalsIgnoreCase( userName ) ){
			scrubbed	= "Teresa Moody";
		}
		else if( "Joe Deriu/Graca Cordeiro".equalsIgnoreCase( userName ) ){
			scrubbed	= "Joe Deriu";
		}
		else if( "Marie Lesette Westbrook".equalsIgnoreCase( userName ) ){
			scrubbed	= "Marie Westbrook";
		}
		else if( "Jason Everitt/Lynn Fox".equalsIgnoreCase( userName ) ){
			scrubbed	= "Jason Everitt";
		}
		else if( "Manju".equalsIgnoreCase( userName ) ){
			scrubbed	= "Manjunatha Ramachandragowda";
		}
		else if( "Christy".equalsIgnoreCase( userName ) ){
			scrubbed	= "Christy Wilson";
		}
		else if( "Chrsity".equalsIgnoreCase( userName ) ){
			scrubbed	= "Christy Wilson";
		}
		else if( "Aric".equalsIgnoreCase( userName ) ){
			scrubbed	= "Aric McDonald";
		}
		else if( "Aric Macdonald".equalsIgnoreCase( userName ) ){
			scrubbed	= "Aric McDonald";
		}
		else if( "Aric McDonald".equalsIgnoreCase( userName ) ){
			scrubbed	= "Aric McDonald";
		}
		else if( "Debi".equalsIgnoreCase( userName ) ){
			scrubbed	= "Debi Rivera";
		}
		else if( "Tasha".equalsIgnoreCase( userName ) ){
			scrubbed	= "Tasha Nichols";
		}
		else if( "Tom".equalsIgnoreCase( userName ) ){
			scrubbed	= "Tom Borcherding";
		}
		else if( "Amit".equalsIgnoreCase( userName ) ){
			scrubbed	= "Amit Phatak";
		}
		else if( "Pavan".equalsIgnoreCase( userName ) ){
			scrubbed	= "Pavan Joshi";
		}
		else if( "Ginger".equalsIgnoreCase( userName ) ){
			scrubbed	= "Ginger Garza";
		}
		else if( "Dinesh Babu Nair Bhaskaran".equalsIgnoreCase( userName ) ){
			scrubbed	= "Dinesh Bhaskaran";
		}
		else if( "Khaleil Bisnath /Jennifer Lawrence".equalsIgnoreCase( userName ) ){
			scrubbed	= "Khaleil Bisnath";
		}
		else if( "Lilibeth/Deb".equalsIgnoreCase( userName ) ){
			scrubbed	= "Lilibeth Ong";
		}
		else if( "Fred".equalsIgnoreCase( userName ) ){
			scrubbed	= "Frederick Kleinsteuber";
		}
		else if( "Chris/Jesse".equalsIgnoreCase( userName ) ){
			scrubbed	= "Chris Richerson";
		}
		else if( "Robert Sawyer/ Tasha Nichols".equalsIgnoreCase( userName ) ){
			scrubbed	= "Robert Sawyer";
		}
		else if( "Bob Sawyer".equalsIgnoreCase( userName ) ){
			scrubbed	= "Robert Sawyer";
		}
		else if( "Shannon".equalsIgnoreCase( userName ) ){
			scrubbed	= "Shannon Garcia";
		}
		else if( "Javier Mier y Teran Bracho".equalsIgnoreCase( userName ) ){
			scrubbed	= "Javier Bracho";
		}
		else if( "Beatriz Irene Calderon Ramirez".equalsIgnoreCase( userName ) ){
			scrubbed	= "Beatriz Ramirez";
		}
		else if( "Subrat".equalsIgnoreCase( userName ) ){
			scrubbed	= "Subrat Pattnaik";
		}
		else if( "Marshall".equalsIgnoreCase( userName ) ){
			scrubbed	= "Marshall Evans";
		}
		else if( "Van Riper, Steven C".equalsIgnoreCase( userName ) ){
			scrubbed	= "Steven VanRiper";
		}
		else if( "Mitchell\nFriedman".equalsIgnoreCase( userName ) ){
			scrubbed	= "Mitchell Friedman";
		}
		else if( "Kristen".equalsIgnoreCase( userName ) ){
			scrubbed	= "Kristen Turner";
		}
		else if( "Ronnie".equalsIgnoreCase( userName ) ){
			scrubbed	= "Ronnie Morgan";
		}
		else if( "Glenn".equalsIgnoreCase( userName ) ){
			scrubbed	= "Glenn Coyle";
		}
		else if( "Mukesh Kumar3".equalsIgnoreCase( userName ) ){
			scrubbed	= "Mukesh Kumar3";
		}
		else if( "Lisa Lee/Chris Richerson".equalsIgnoreCase( userName ) ){
			scrubbed	= "Chris Richerson";
		}
		else if( "Chris".equalsIgnoreCase( userName ) ){
			scrubbed	= "Chris Richerson";
		}
		else if( "Joyce Kirsh/Cathy Aiello".equalsIgnoreCase( userName ) ){
			scrubbed	= "Joyce Kirsh";
		}
		else if( "(Cathy) Aiello".equalsIgnoreCase( userName ) ){
			scrubbed	= "Cathy Aiello";
		}
		else if( "Catherine Aiello".equalsIgnoreCase( userName ) ){
			scrubbed	= "Cathy Aiello";
		}

		
		
		return scrubbed;
	}
	
	private void setPhaseStatus( String cplValue, IWorkflow workflow, CurrentPhaseStatus current ){
		if( "Pending Requirements".equals( cplValue ) ){
			current.setPhase( phases.get( "Requirements" ) );
			current.setStatus( statuses.get( "In Progress" ) );
		}
		else if( "Reviewing Requirements".equals( cplValue ) ){
			current.setPhase( phases.get( "HLAD" ) );
			current.setStatus( statuses.get( "Reviewing" ) );
		}
		if( "In Queue".equals( cplValue ) ){
			current.setPhase( phases.get( "HLAD" ) );
			current.setStatus( statuses.get( "In Queue" ) );
		}
		else if( "HLAD".equals( cplValue ) ){
			current.setPhase( phases.get( "HLAD" ) );
			current.setStatus( statuses.get( "In Progress" ) );
		}
		else if( "HLE".equals( cplValue ) ){
			current.setPhase( phases.get( "HLE" ) );
			current.setStatus( statuses.get( "In Progress" ) );
		}
		else if( "Sign Off Pending (Dev)".equals( cplValue ) ){
			current.setPhase( phases.get( "HLE" ) );
			current.setStatus( statuses.get( "Pending Approval" ) );
		}
		else if( "Sign Off Pending (Biz)".equals( cplValue ) ){
			current.setPhase( phases.get( "HLE" ) );
			current.setStatus( statuses.get( "Pending Approval" ) );
		}
		else if( "Planned Effort Not Open".equals( cplValue ) ){
			current.setPhase( phases.get( "Slotting" ) );
			current.setStatus( statuses.get( "Complete" ) );
		}
		else if( "Support Only".equals( cplValue ) ){
			current.setPhase( phases.get( "Slotting" ) );
			current.setStatus( statuses.get( "Complete" ) );
		}
		else if( "Slotting Pending".equals( cplValue ) ){
			current.setPhase( phases.get( "Slotting" ) );
			current.setStatus( statuses.get( "In Progress" ) );
		}
		else if( "Slotting Submitted".equals( cplValue ) ){
			current.setPhase( phases.get( "Slotting" ) );
			current.setStatus( statuses.get( "In Queue" ) );
		}
		else if( "Slotted".equals( cplValue ) ){
			current.setPhase( phases.get( "Slotting" ) );
			current.setStatus( statuses.get( "Complete" ) );
		}
		else if( "No Impact".equals( cplValue ) ){
			current.setPhase( phases.get( "Slotting" ) );
			current.setStatus( statuses.get( "Complete" ) );
		}
		else if( "Cancelled".equals( cplValue ) ){
			current.setPhase( phases.get( "Done" ) );
			current.setStatus( statuses.get( "Cancelled" ) );
		}
		else if( "On Hold".equals( cplValue ) ){
			current.setPhase( phases.get( "Parked" ) );
			current.setStatus( statuses.get( "On Hold" ) );
		}
		else{
			System.out.println( "Unkown Phase '" + cplValue + " found for Idea " + workflow.getIdea().getIdeaNumber() + "' setting it to requirements." );
			current.setPhase( phases.get( "Requirements" ) );
			current.setStatus( statuses.get( "In Progress" ) );
		}
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
}
