package com.fdc.boarding.releasetracker.test.gateway.xcel;

import javax.inject.Inject;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fdc.boarding.core.query.Conjunction;
import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.security.IUser;

public class AbstractExpected {
	@Inject
	protected IEntityReaderSvc			reader;

	protected void assertDate( String value, LocalDate actual ){
		LocalDate						date;
		DateTimeFormatter 				formatter;
		
		if( value == null || value.isEmpty() ){
			Assert.assertNull( actual );
		}
		else{
			formatter	= DateTimeFormat.forPattern( "MM/dd/yyyy" );
			date		= LocalDate.parse( value, formatter );
			Assert.assertEquals( date , actual );
		}
	}

	
	protected void assertSameUser( String value, IUser actual ){
		IUser							expected	= null;
		String[]						split;

		if( value == null ){
			Assert.assertNull( actual );
		}
		else{
			split	= value.split( " " );
			try {
				expected	= reader.find( IUser.class, "id", true, Conjunction.and( 
									Restriction.eq( "firstName", split[0]), 
									Restriction.eq("lastName", split[1])) ).get( 0 );
			} catch (QueryException e) {
				e.printStackTrace();
			}
			Assert.assertNotNull( actual );
			Assert.assertEquals( expected.getFirstName(), actual.getFirstName() );
			Assert.assertEquals( expected.getLastName(), actual.getLastName() );
			Assert.assertEquals( expected.getEmail(), actual.getEmail() );
		}
	}

	public void setReader(IEntityReaderSvc reader) {
		this.reader = reader;
	}

}
