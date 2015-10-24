package com.fdc.boarding.releasetracker.test.gateway.xcel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class TestSimple {
	private static Pattern 				p1 			= Pattern.compile( "^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)(.*)" );
	private static Pattern 				p2 			= Pattern.compile( "^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])[ :](.*)" );
	private static DateTimeFormatter	formatter 	= DateTimeFormat.forPattern("MM/dd/yyyy");

	@Test
	public void testComments(){
		String[] all	= new String[]{ "09/01/2015: Added via estimator record.", "9/3 Adriana requested Christy Wilson to review\n9/3/2015: Jesse approval request reminder to Adriana Mize(3).\n8/28 Al sent Biz(2) request\n8/24 Cathy Aiello deferred Biz approval to Adriana Mize\n8/21 In Business approval\n8/14/2015: Chris entered estimates prior to eBus Approvals.  We no longer enter Aperia (OneStop) estimates although they should still be informed as impacted team.\n8/12 Requested DEV approvals OneStop(2), eBus(2), ESCS(2)\n8/5 Roadmap review approved\n8/4 Al setting TID to 2/6/2016 based on release calendar,\n7/22 Al requested TID from Trish Vernstrom,\n06/30 changed phase start date due to Top11s & VACA,\n06/25 changed phase start date due to Top11s,\n6/15/2015: Per Clarity ROM and Release Calendar, requested date is close to valid October Release Date. Request for confirmation sent to Patricia Vernstrom. Assigned to Al.\n6/15/2015: Added via email from Deb."};
//		String[] all	= new String[]{ "9/28 Adriana requested Christy Wilson to review" };
		String[] raws;
		Matcher 						matcher;
		String							sdate		= null;
		String							comment		= null;
		LocalDate						date;
		
		try {
			for( String comments : all ){
				raws	= comments.split( "\n" );
				for( String rc : raws ){
					matcher = p1.matcher( rc );
					if( matcher.find() ){
						sdate	= matcher.group( 1 ) + "/" + matcher.group( 2 ) + "/" + matcher.group( 3 );
						comment	= matcher.group( 5 ).trim();
						if( comment.startsWith(":") )
						{
							comment = comment.replaceFirst( ":", "" );
						}
						comment	= comment.trim();
						System.out.println( "New[" + sdate + "-" + comment + "] Orignal[" + rc + "]" );
					}
					else{
						matcher = p2.matcher( rc );
						if( matcher.find() ){
							sdate	= matcher.group( 1 ) + "/" + matcher.group( 2 ) + "/2015";
							comment	= matcher.group( 3 ).trim();
							if( comment.startsWith(":") )
							{
								comment = comment.replaceFirst( ":", "" );
							}
							comment	= comment.trim();
							System.out.println( "New[" + sdate + "-" + comment + "] Orignal[" + rc + "]" );
						}
					}
					
					if( sdate != null && comment != null ){
//						date 	= LocalDate.parse( sdate );
//						System.out.println( "Date:" + date.toString( "MM/dd/yyy" ) );
						date 	= formatter.parseLocalDate( sdate );
						System.out.println( "New[" + date.toString( "MM/dd/yyy" ) + "-" + comment + "] Orignal[" + rc + "]" );
					}
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
