package com.fdc.boarding.releasetracker.test.security;

import junit.framework.Assert;

import org.junit.Test;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.security.LocateUserRequest;
import com.fdc.boarding.releasetracker.domain.security.LocateUserResponse;
import com.fdc.boarding.releasetracker.domain.security.UserUC;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;

public class TestUser extends AbstractPersistenceTest{
    private UserUC								usecase;

	@Override
	public void injectServices() {
		usecase	= CDIContext.getInstance().getBean( UserUC.class );
	}

	@Test
	public void testLocateUser(){
		LocateUserRequest 				request;
		LocateUserResponse				response;
	
		request		= new LocateUserRequest();
		request.setNamePrefix( "T" );
		response	= usecase.locateUsers( request );

		Assert.assertNotNull( response );
		Assert.assertNotNull( response.getUsers() );
		Assert.assertTrue( !response.getUsers().isEmpty() );
		Assert.assertTrue( response.isSuccess() );

	}
}
