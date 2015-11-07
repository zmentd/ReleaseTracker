package com.fdc.boarding.releasetracker.persistence.release;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.domain.release.MilestoneType;
import com.fdc.boarding.releasetracker.gateway.release.IReleasePersistenceGateway;
import com.fdc.boarding.releasetracker.usecase.release.MilestoneByRom;

public class ReleasePersistenceGateway extends GenericDao<ReleaseEntryEntity, Long> implements IReleasePersistenceGateway{

	
	public ReleasePersistenceGateway() {
		super(ReleaseEntryEntity.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IReleaseEntry> findAllReleases() {
		List<? extends IReleaseEntry>		list;
		
		list	= findAll();
		
		return ( List<IReleaseEntry> )list;
	}


	@Override
	public List<IReleaseEntry> findReleaseCalendarForYear( int year){
		String							jql;
		TypedQuery<ReleaseEntryEntity> 	query;
		LocalDate						startOfYear;
		LocalDate						endOfYear;
		List<IReleaseEntry>				list;
		List<ReleaseEntryEntity>		results;
		
		startOfYear	= new LocalDate().withYear(year)
								     .withDayOfYear(1);
		
		endOfYear	= new LocalDate().withYear(year + 1)
								     .withDayOfYear(1)
								     .minusDays(1);
		System.out.println( "StartYear:" + startOfYear );
		System.out.println( "EndYear:" + endOfYear );
		jql			= "from ReleaseEntryEntity re "
				    + "where re.releaseDate >= :startOfYear "
				    + "and re.releaseDate <= :endOfYear ";
		query 		= entityManager.createQuery( jql, ReleaseEntryEntity.class );		
		query.setParameter( "startOfYear", startOfYear );
		query.setParameter( "endOfYear", endOfYear );
		
		results		= query.getResultList();
		list		= new ArrayList<>();
		for( ReleaseEntryEntity entity : results )
		{
			list.add( entity );
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> determineReleaseYears(){
		String							jql;
		Query 							query;
		List<Integer>					list;
		
		jql			= "SELECT distinct YEAR( releaseDate ) from ReleaseEntryEntity";
		query 		= entityManager.createQuery( jql, Integer.class );		
		
		list		= query.getResultList();
		
		return list;
		
	}
	
	@Override
	public MilestoneByRom findMilestoneByTargetDate( LocalDate date, Rom rom, MilestoneType milestoneType ){
		Query 							query;
		String							jql;
		Object[]						results		= null;
		MilestoneByRom					milestone	= null;
		
		jql	= " select milestone.id, milestone.name, milestone.index, milestone.description, milestone.releaseEntry";
		if( "XS".equals( rom.value() ) || "S".equals( rom.value() ) ){
			jql = jql + ", milestone.smallDueDate ";
		}
		else if( "M".equals( rom.value() ) || "L".equals( rom.value() ) ){
			jql = jql + ", milestone.mediumDueDate ";
		}
		else{
			jql = jql + ", milestone.largeDueDate ";
		}
		jql = jql + "from MilestoneEntity milestone ";
		
		jql = jql + "where YEAR( milestone.releaseEntry.releaseDate ) = :releaseYear ";
		jql = jql + " and MONTH( milestone.releaseEntry.releaseDate ) = :releaseMonth";
		jql = jql + " and milestone.milestoneType = :milestoneType ";
		if( "XS".equals( rom.value() ) || "S".equals( rom.value() ) ){
			jql = jql + "and milestone.smallDueDate is not null ";
		}
		else if( "M".equals( rom.value() ) || "L".equals( rom.value() ) ){
			jql = jql + "and milestone.mediumDueDate is not null ";
		}
		else{
			jql = jql + "and milestone.largeDueDate is not null ";
		}

		
		
		query	= entityManager.createQuery( jql );
		query.setParameter( "milestoneType", milestoneType );
		query.setParameter( "releaseYear", date.year().get() );
		query.setParameter( "releaseMonth", date.monthOfYear().get() );

		try {
			results	= ( Object[] )query.getSingleResult();
			milestone	= new MilestoneByRom();
			milestone.setId( ( Long )results[0] );
			milestone.setName( ( String )results[1]);
			milestone.setIndex( ( Integer )results[2] );
			milestone.setDescription( ( String )results[3] );
			milestone.setReleaseEntry( ( IReleaseEntry )results[4] );
			milestone.setDueDate( ( LocalDate )results[5] );
			milestone.setRom( rom );
			milestone.setMilestoneType( milestoneType );
			
		} catch( NoResultException e ) {
			System.out.println( "No Milestone found for milestone type " + milestoneType.display() );
		}
		
		
		
		return milestone;
	}
}
