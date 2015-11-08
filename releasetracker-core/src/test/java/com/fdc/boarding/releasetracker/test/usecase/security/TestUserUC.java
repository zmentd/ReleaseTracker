package com.fdc.boarding.releasetracker.test.usecase.security;

import junit.framework.Assert;

import org.junit.Test;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;
import com.fdc.boarding.releasetracker.usecase.security.LocateUserRequest;
import com.fdc.boarding.releasetracker.usecase.security.LocateUserResponse;
import com.fdc.boarding.releasetracker.usecase.security.UserUC;

public class TestUserUC extends AbstractPersistenceTest{

    private UserUC									usecase;

	@Override
	public void injectServices() {
		usecase 			= CDIContext.getInstance().getBean( UserUC.class );
	}

	@Test
	public void testFindUsers(){
		LocateUserRequest 				request;
		LocateUserResponse				response;
		
    	try {
    		request		= new LocateUserRequest();
    		request.setNamePrefix( "tr" );
     		response	= usecase.locateUsers( request );
			Assert.assertNotNull( response );
			Assert.assertTrue( response.isSuccess() );
			Assert.assertFalse( response.getUsers().isEmpty() );
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
