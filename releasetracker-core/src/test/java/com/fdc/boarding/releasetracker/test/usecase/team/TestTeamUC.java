package com.fdc.boarding.releasetracker.test.usecase.team;

import junit.framework.Assert;

import org.junit.Test;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.persistence.team.TeamEntity;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;
import com.fdc.boarding.releasetracker.usecase.LikeRequest;
import com.fdc.boarding.releasetracker.usecase.team.ListTeamResponse;
import com.fdc.boarding.releasetracker.usecase.team.TeamRequest;
import com.fdc.boarding.releasetracker.usecase.team.TeamResponse;
import com.fdc.boarding.releasetracker.usecase.team.TeamUC;
import com.fdc.boarding.releasetracker.usecase.team.dto.TeamDto;

public class TestTeamUC extends AbstractPersistenceTest{

    private TeamUC									usecase;
	private IEntityReaderSvc						reader;

	@Override
	public void injectServices() {
		usecase 			= CDIContext.getInstance().getBean( TeamUC.class );
		reader 				= CDIContext.getInstance().getBean( IEntityReaderSvc.class );
	}

	@Test
	public void testAddTeam(){
		TeamRequest						request;
		TeamResponse					response;
		TeamDto							entity;
		
		request	= new TeamRequest();
		entity	= new TeamDto();
		entity.setName( "New Team" );
		entity.setObs( "/FDA/MY/OBS");
		request.setTeam( entity );
		
		response	= usecase.addTeam( request );
		Assert.assertNotNull( response );
		Assert.assertNotNull( response.getTeam() );
		Assert.assertNotNull( response.getTeam().getId() );
		Assert.assertTrue( response.isSuccess() );
		Assert.assertEquals( response.getTeam().getName(), entity.getName() );
		Assert.assertEquals( response.getTeam().getObs(), entity.getObs() );
	}

	@Test
	public void testUpdateTeam(){
		TeamRequest						request;
		TeamResponse					response;
		TeamEntity						entity;
		TeamDto							dto;
		
		request	= new TeamRequest();
		entity 	= reader.findByKey( TeamEntity.class, 1L );
		dto		= TeamDto.from( entity );
		dto.setObs( "/FDA/MY/OBS");
		request.setTeam( dto );
		
		response	= usecase.updateTeam( request );
		Assert.assertNotNull( response );
		Assert.assertNotNull( response.getTeam() );
		Assert.assertNotNull( response.getTeam().getId() );
		Assert.assertTrue( response.isSuccess() );
		Assert.assertEquals( response.getTeam().getName(), entity.getName() );
		Assert.assertEquals( response.getTeam().getObs(), entity.getObs() );
	}

	@Test
	public void testRemoveTeam(){
		TeamRequest						request;
		TeamResponse					response;
		TeamDto							entity;
		
		request = new TeamRequest();
		entity	= new TeamDto();
		entity.setName( "New Team" );
		entity.setObs( "/FDA/MY/OBS");
		request.setTeam( entity );
		
		response	= usecase.addTeam( request );
		request.setTeam( response.getTeam() );
		response	= usecase.removeTeam( request );
		
		Assert.assertNotNull( response );
		Assert.assertNotNull( response.getTeam() );
		Assert.assertNotNull( response.getTeam().getId() );
		Assert.assertTrue( response.isSuccess() );
		Assert.assertEquals( response.getTeam().getName(), entity.getName() );
		Assert.assertEquals( response.getTeam().getObs(), entity.getObs() );
	}

	@Test
	public void testFindTeams(){
		LikeRequest 					request;
		ListTeamResponse				response;
		
    	try {
    		request		= new LikeRequest();
    		request.setSearchValue( "SN" );
     		response	= usecase.findTeams( request );
			Assert.assertNotNull( response );
			Assert.assertTrue( response.isSuccess() );
			Assert.assertFalse( response.getList().isEmpty() );
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
