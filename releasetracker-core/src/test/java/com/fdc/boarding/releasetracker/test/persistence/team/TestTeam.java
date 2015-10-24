package com.fdc.boarding.releasetracker.test.persistence.team;

import javax.inject.Inject;

import org.junit.Test;

import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.IEntityDao;
import com.fdc.boarding.releasetracker.persistence.team.TeamEntity;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;

public class TestTeam extends AbstractPersistenceTest{

	@Inject
	private EntityPersistenceService	persistenceService;

	@Inject
	private IEntityDao					dao;

	@Test
	public void testAddTeam(){
		TeamEntity					team;
		TeamEntity					read;
		
		team	= new TeamEntity();
		team.setName( "test team" );
		team.setObs( "/test/team" );
		persistenceService.insert( team );
		System.out.println( "Last Mod:" + team.getLastModifiedDate().toString( "hh:mm:ss a zzz" ) );
		
		read	= dao.findByNaturalKey( TeamEntity.class, "name", "test team" );
		System.out.println( "Last Mod:" + read.getLastModifiedDate().toString( "hh:mm:ss a zzz" ) );
		System.out.println( "Done." );
		
		dao.delete( read );
	}
}
