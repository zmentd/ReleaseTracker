package com.fdc.boarding.releasetracker.test.security;

import junit.framework.Assert;

import org.junit.Test;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.security.Credentials;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;
import com.fdc.boarding.releasetracker.usecase.security.AuthenticationResponse;
import com.fdc.boarding.releasetracker.usecase.security.RegistrationRequest;
import com.fdc.boarding.releasetracker.usecase.security.RegistrationResponse;
import com.fdc.boarding.releasetracker.usecase.security.UserUC;

public class TestSecurity extends AbstractPersistenceTest{

	@Test
	public void testRegistrationFailValidation(){
		UserUC						usecase;
		RegistrationResponse		response;
		RegistrationRequest 		request;
		
		System.out.println( "Entering register." );
		request		= new RegistrationRequest();
		usecase		= CDIContext.getInstance().getBean( UserUC.class );
		response	= usecase.registerUser( request );
		Assert.assertFalse( response.isSuccess() );
		Assert.assertFalse( response.getFailures().isEmpty() );
		Assert.assertEquals( 9, response.getFailures().size() );
		System.out.println( "Exiting register." );
	}

	@Test
	public void testRegistrationFailEmail(){
		UserUC						usecase;
		RegistrationResponse		response;
		RegistrationRequest 		request;
		
		System.out.println( "Entering register." );
		request		= new RegistrationRequest();
		request.setConfirmPassword( "test123" );
		request.setFirstName( "test" );
		request.setLastName( "test" );
		request.setPassword( "test123" );
		request.setUsername( "test" );
		request.setEmail( "test" );
		usecase		= CDIContext.getInstance().getBean( UserUC.class );
		response	= usecase.registerUser( request );
		Assert.assertFalse( response.isSuccess() );
		Assert.assertFalse( response.getFailures().isEmpty() );
		Assert.assertEquals( 1, response.getFailures().size() );
		System.out.println( "Exiting register." );
	
	}

	@Test
	public void testRegistrationFailPassword(){
		UserUC						usecase;
		RegistrationResponse		response;
		RegistrationRequest 		request;
		
		try {
			System.out.println( "Entering register." );
			request		= new RegistrationRequest();
			request.setConfirmPassword( "test" );
			request.setFirstName( "test" );
			request.setLastName( "test" );
			request.setPassword( "test" );
			request.setUsername( "test" );
			request.setEmail( "test@test.com" );
			usecase		= CDIContext.getInstance().getBean( UserUC.class );
			response	= usecase.registerUser( request );
			Assert.assertFalse( response.isSuccess() );
			Assert.assertFalse( response.getFailures().isEmpty() );
			Assert.assertEquals( 1, response.getFailures().size() );
			System.out.println( "Exiting register." );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testRegistrationFailPasswordMismatch(){
		UserUC						usecase;
		RegistrationResponse		response;
		RegistrationRequest 		request;
		
		try {
			System.out.println( "Entering register." );
			request		= new RegistrationRequest();
			request.setUsername( "test" );
			request.setEmail( "test@test.com" );
			request.setFirstName( "test" );
			request.setLastName( "test" );
			request.setPassword( "test123" );
			request.setConfirmPassword( "test" );
			usecase		= CDIContext.getInstance().getBean( UserUC.class );
			response	= usecase.registerUser( request );
			Assert.assertFalse( response.isSuccess() );
			Assert.assertFalse( response.getFailures().isEmpty() );
			Assert.assertEquals( 1, response.getFailures().size() );
			System.out.println( "Exiting register." );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testRegistration(){
		UserUC						usecase;
		RegistrationResponse		response;
		RegistrationRequest 		request;
		Credentials					creds;
		AuthenticationResponse		authResponse;
		
		try {
			System.out.println( "Entering register." );
			request		= new RegistrationRequest();
			request.setUsername( "test" );
			request.setEmail( "test@test.com" );
			request.setFirstName( "test" );
			request.setLastName( "test" );
			request.setPassword( "test123" );
			request.setConfirmPassword( "test123" );
			usecase		= CDIContext.getInstance().getBean( UserUC.class );
			response	= usecase.registerUser( request );
			Assert.assertTrue( response.isSuccess() );
			Assert.assertNull( response.getFailures() );
			
			creds	= new Credentials();
			creds.setUserId( request.getUsername() );
			creds.setPassword( request.getPassword() );
			authResponse	= usecase.authenticateUser( creds );
			Assert.assertTrue( authResponse.isSuccess() );
			Assert.assertNull( authResponse.getFailures() );

			System.out.println( "Exiting register." );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testRegistrationNoAuth(){
		UserUC						usecase;
		RegistrationResponse		response;
		RegistrationRequest 		request;
		Credentials					creds;
		AuthenticationResponse		authResponse;
		
		try {
			System.out.println( "Entering register." );
			request		= new RegistrationRequest();
			request.setUsername( "testNowHasAuth" );
			request.setEmail( "noauth@test.com" );
			request.setFirstName( "test" );
			request.setLastName( "test" );
			request.setPassword( "test123" );
			request.setConfirmPassword( "test123" );
			usecase		= CDIContext.getInstance().getBean( UserUC.class );
			response	= usecase.registerUser( request );
			Assert.assertTrue( response.isSuccess() );
			Assert.assertNull( response.getFailures() );
			
			creds	= new Credentials();
			creds.setUserId( request.getUsername() );
			creds.setPassword( request.getPassword() );
			authResponse	= usecase.authenticateUser( creds );
			Assert.assertTrue( authResponse.isSuccess() );
			Assert.assertNull( authResponse.getFailures() );

			System.out.println( "Exiting register." );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testAuthenticate(){
		UserUC							usecase;
		Credentials						creds;
		AuthenticationResponse			response;
		
		usecase	= CDIContext.getInstance().getBean( UserUC.class );
		creds	= new Credentials();
		creds.setPassword( "test123" );
		creds.setUserId( "tzman" );
		response	= usecase.authenticateUser( creds );
		Assert.assertTrue( response.isSuccess() );
		Assert.assertNull( response.getFailures() );
	}
	
	@Test
	public void testAuthenticateFail(){
		UserUC							usecase;
		Credentials						creds;
		AuthenticationResponse			response;
		
		usecase	= CDIContext.getInstance().getBean( UserUC.class );
		creds	= new Credentials();
		creds.setPassword( "test" );
		creds.setUserId( "tzman" );
		response	= usecase.authenticateUser( creds );
		Assert.assertFalse( response.isSuccess() );
		Assert.assertNull( response.getFailures() );
	}

}
