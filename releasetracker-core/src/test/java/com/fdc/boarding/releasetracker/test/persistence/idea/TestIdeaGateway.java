package com.fdc.boarding.releasetracker.test.persistence.idea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.Assert;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.fdc.boarding.core.query.Restriction;
import com.fdc.boarding.core.query.exception.QueryException;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.release.IReleaseEntry;
import com.fdc.boarding.releasetracker.domain.team.ITeam;
import com.fdc.boarding.releasetracker.domain.workflow.IPhase;
import com.fdc.boarding.releasetracker.domain.workflow.IStatus;
import com.fdc.boarding.releasetracker.gateway.idea.IIdeaPersistenceGateway;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaAp;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaGroupBy;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaPartialDto;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaStatusResponse;

public class TestIdeaGateway extends AbstractPersistenceTest{
    @Inject
    protected EntityManager 				entityManager;

	@Inject
	private IEntityReaderSvc				reader;

    @Inject
    private IIdeaPersistenceGateway			gateway;
	
	private Map<String, ITeam>				teams	= new HashMap<>();
	private Map<String, IPhase>				phases		= new HashMap<>();
	private Map<String, IStatus>			statuses	= new HashMap<>();
	private Map<LocalDate, IReleaseEntry>	releases	= new HashMap<>();

	@Override
	public void injectServices() {
		
		gateway 		= CDIContext.getInstance().getBean( IIdeaPersistenceGateway.class );
		reader 			= CDIContext.getInstance().getBean( IEntityReaderSvc.class );
		entityManager	= CDIContext.getInstance().getBean( EntityManager.class );

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testJoinFetch(){
		Query 							query;
		String							jql;
		List<? extends IIdea>		list;
		
		try {
			jql		= "select distinct entity from IdeaEntity entity ";
			jql		= jql + "left outer join fetch entity.teamImpacts as impact ";
			jql		= jql + "inner join fetch entity.currentStatus as status ";
			jql		= jql + "inner join fetch entity.currentPhase as phase ";
			jql		= jql + "left outer join fetch entity.assignedTo as assignedTo ";
			jql		= jql + "left outer join fetch entity.solutionArchitect as solutionArchitect ";
			jql		= jql + "left outer join fetch entity.demandOwner as demandOwner ";
			jql		= jql + "left outer join fetch entity.originalRequestor as originalRequestor ";
			jql		= jql + "left outer join fetch entity.release as release ";
			jql		= jql + "where entity.id in ( 10821, 10282 ) ";
			query	= entityManager.createQuery( jql );
			list 	= query.getResultList();
			System.out.println( "List:" + list.size() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Test
    public void testReadIdeas(){
    	IdeaSearchResponse			response;
    	IdeaAp						ap;
    	List<IStatus>					excludeStatuses;
    	DateTime						start;
    	DateTime						end;
    	
    	System.out.println( "Reading Ideas..." );
    	readConfigData();
    	ap			= new IdeaAp();
    	ap.setGroupBy( IdeaGroupBy.Release );
//    	ap.setTeam( teams.get( "Business Track" ) );
    	excludeStatuses	= new ArrayList<>();
//    	excludeStatuses.add( statuses.get( "Complete" ) );
//    	excludeStatuses.add( statuses.get( "No Impact" ) );
//    	excludeStatuses.add( statuses.get( "Cancelled" ) );
//    	excludeStatuses.add( statuses.get( "On Hold" ) );
//    	excludeStatuses.add( statuses.get( "Slotted" ) );
    	ap.setExcludeStatuses( excludeStatuses );
    	
    	start		= new DateTime();
    	response	= gateway.findIdeas( ap );
    	end			= new DateTime();
    	System.out.println( "Response completed in:" + new Duration( start, end ) );
    	Assert.assertNotNull( response.getIdeas() );
    	Assert.assertTrue( !response.getGroupedIdeas().isEmpty() );
   
    	System.out.println( "Idea count:" + response.getIdeas().size() );
    	for( IdeaStatusResponse idea : response.getIdeas() )
    	{
        	Assert.assertTrue( !excludeStatuses.contains( idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getStatus() ) );
        	System.out.println( "Idea:" + idea.getWorkflow().getIdea().getIdeaNumber() 
        					  + "\n                 Name:" + idea.getWorkflow().getIdea().getName() 
        					  + "\n                Phase:" + idea.getWorkflow().getCurrentPhaseCompletion().getPhase().getName() 
        					  + "\n               Status:" + idea.getWorkflow().getCurrentPhaseCompletion().getCurrentStatusCompletion().getStatus().getName()
        					  + "\n              Release:" + ( idea.getReleaseDate() != null ? idea.getReleaseDate().toString( "yy.MM" ) : "" )
        					  + "\n       Release Status:" + idea.getReleaseStatus()
        					  + "\n      Days To Release:" + idea.getDaysToRelease()
        					  + "\n            Milestone:" + idea.getMilestoneName()
        					  + "\n   Milestone Due Date:" + ( idea.getMilestoneDueDate() != null ? idea.getMilestoneDueDate().toString( "MM/dd/yyyy" ) : "" )
        					  + "\n     Milestone Status:" + idea.getMilestoneStatus()
        					  + "\n       Days To Target:" + idea.getDaysToTarget()
       					  
        					   );
    	}
    	
    }
    
	@Test
	public void testReadAssignedTo(){
		List<IIdea>					ideas;
		
    	System.out.println( "Reading Ideas..." );
    	try {
			ideas	= reader.find( IIdea.class
									 , "priority"
									 , false
									 , Restriction.eq( "assignedTo.id", 1L )
									 , "comments" 
			);
			System.out.println( "Response received:" + ideas.size() );
		} catch (QueryException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testPartialSearch(){
		List<IdeaPartialDto>	list;
		
		try {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list	= gateway.searchIdeas( "CON-0" );
		System.out.println( "Result count:" + list.size() );
		Assert.assertFalse( list.isEmpty() );
	}
	
	private void readConfigData(){
		List<ITeam>						lteams;
		List<IPhase>					lphases;
		List<IStatus>					lstatuses;
		List<IReleaseEntry>				lentries;
		
		try {
			lteams		= reader.find( ITeam.class );
			lphases		= reader.find( IPhase.class );
			lstatuses	= reader.find( IStatus.class );
			lentries	= reader.find( IReleaseEntry.class );
			
			for( ITeam team : lteams )
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
			}
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
