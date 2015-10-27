package com.fdc.boarding.releasetracker.persistence.idea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.core.service.EntityPersistenceService;
import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.idea.DaysToTargetStatus;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.idea.IIdeaPersistenceGateway;
import com.fdc.boarding.releasetracker.domain.idea.IdeaAp;
import com.fdc.boarding.releasetracker.domain.idea.IdeaPartialSearchResponse;
import com.fdc.boarding.releasetracker.domain.idea.IdeaSearchResponse;
import com.fdc.boarding.releasetracker.domain.idea.IdeaStatusResponse;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;
import com.fdc.boarding.releasetracker.gateway.excel.ReaderResponse;
import com.fdc.boarding.releasetracker.persistence.release.MilestoneEntity;

public class IdeaPersistenceGateway extends GenericDao<IdeaEntity, Long> implements IIdeaPersistenceGateway{
	private static final long 			serialVersionUID = 1L;

	@Inject
	private IEntityReaderSvc					reader;
	@Inject
	private EntityPersistenceService			service;
	private Map<MilestoneKeyCache, Object[]>	milestoneCache;
	
	public IdeaPersistenceGateway() {
		super( IdeaEntity.class );
	}

	@Override
	public void delete(IIdea entity) {
		super.delete( ( IdeaEntity )entity );
	}
	
	@Override
	public IIdea determineById( Long id, String... initialize ){
		IIdea						idea	= null;
		
		idea	= reader.findByKey( IdeaEntity.class, id, initialize );
		idea	= entityManager.find( IdeaEntity.class, id );
		for( ITeamImpact pi : idea.getTeamImpacts() ){
			if( pi.getWorkflow().getRom() != null ){
				pi.getWorkflow().getRom().name();
			}
			if( pi.getRomOwner() != null ){
				pi.getRomOwner().getFullName();
			}
		}
		if( idea.getWorkflow().getCurrentPhaseCompletion().getPhase()!= null ){
			idea.getWorkflow().getCurrentPhaseCompletion().getPhase().getNextPhases().get( 0 );
			idea.getWorkflow().getCurrentPhaseCompletion().getPhase().getAvailableStatuses().get( 0 );
		}
		
		return idea;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IIdea> findAllIdeas() {
		List<? extends IIdea>		list;
		
		list	= findAll();
		
		return ( List<IIdea> )list;
	}
	
	@Override
	public IdeaSearchResponse findIdeas( IdeaAp ap ) {
		IdeaSearchResponse			response;
		List<IIdea>					ideas;
		IdeaStatusResponse			statusResponse;
		Object[]						queryResponse;
		int								days;
    	DateTime						start;
    	DateTime						end;
		
    	start			= new DateTime();
		milestoneCache	= new HashMap<>();
		loadCache();
		response		= new IdeaSearchResponse();
		ideas		= findIdeasByAp( ap );
		for( IIdea idea : ideas ){
			statusResponse	= new IdeaStatusResponse();
			statusResponse.setWorkflow( idea.getWorkflow() );
			response.getIdeas().add( statusResponse );
			if( idea.getWorkflow().getRelease() != null ){
				statusResponse.setReleaseDate( idea.getWorkflow().getRelease().getReleaseDate() );
				days = Days.daysBetween( new LocalDate(), statusResponse.getReleaseDate() ).getDays();
				statusResponse.setDaysToRelease( days );
				statusResponse.setReleaseStatus( DaysToTargetStatus.locateByDays( days ) );
			}
			if( idea.getWorkflow().getRelease() != null && idea.getWorkflow().getRom() != null && idea.getWorkflow().getCurrentPhaseCompletion().getPhase() != null ){
				queryResponse	= queryStatusInfo( idea.getWorkflow().getRelease().getId(), idea.getWorkflow().getRom().value(), idea.getWorkflow().getCurrentPhaseCompletion().getPhase().getType() );
				if( queryResponse != null ){
					statusResponse.setMilestoneId( ( Long )queryResponse[0] );
					statusResponse.setMilestoneName( ( String )queryResponse[1] );
					statusResponse.setMilestoneDueDate( ( LocalDate )queryResponse[2] );
					if( statusResponse.getMilestoneDueDate() != null ){
						days = Days.daysBetween( new LocalDate(), statusResponse.getMilestoneDueDate() ).getDays();
						statusResponse.setDaysToMilestone( days );
						statusResponse.setMilestoneStatus( DaysToTargetStatus.locateByDays( days ) );
					}
					else{
						System.out.println( "---------> Milestone not found by ROM for idea " + idea.getIdeaNumber() );
					}
				}
				if( idea.getWorkflow().getPhaseTargetDate() != null ){
					days = Days.daysBetween( new LocalDate(), idea.getWorkflow().getPhaseTargetDate() ).getDays();
					statusResponse.setDaysToTarget( days );
					statusResponse.setPhaseStatus( DaysToTargetStatus.locateByDays( days ) );
				}
				else{
					statusResponse.setPhaseStatus( DaysToTargetStatus.Red );
				}
			}
//			idea.getComments().size();
//			idea.getTeamImpacts().size();
			if( ap.getGroupBy() != null ){
				switch( ap.getGroupBy() ){
				case MilestoneStatus:
					if( postFilter(ap, statusResponse) ){
						response.addIdeaStatus( statusResponse.getMilestoneStatus().display(), statusResponse );
					}
					break;
				case Phase:
					if( postFilter(ap, statusResponse) ){
						response.addIdeaStatus( statusResponse.getWorkflow().getCurrentPhaseCompletion().getPhase().getName(), statusResponse );
					}
					break;
				case Release:
					if( postFilter(ap, statusResponse) ){
						response.addIdeaStatus( statusResponse.getReleaseDate() != null ? statusResponse.getReleaseDate().toString("yy.MM") : "", statusResponse );
					}
					break;
				case ReleaseStatus:
					if( postFilter(ap, statusResponse) ){
						response.addIdeaStatus( statusResponse.getReleaseStatus().display(), statusResponse );
					}
					break;
				default:
					break;
				}
					
			}
			else{
				if( postFilter(ap, statusResponse) ){
					response.addIdeaStatus( "", statusResponse );
				}
			}
		}
		milestoneCache.clear();
    	end			= new DateTime();
    	System.out.println( "Query complete in:" + new Duration( start, end ) );
		
		return response;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<IIdea> findIdeasByAp( IdeaAp ap ) {
		List<? extends IIdea>		list;
		Query 							query;
		String							jql;
		
		jql		= "select distinct entity from IdeaEntity entity ";
		jql		= jql + "left outer join fetch entity.teamImpacts as impact ";
		jql		= jql + "inner join fetch entity.workflow as workflow ";
		jql		= jql + "inner join fetch workflow.currentPhaseCompletion phaseCompletion ";
		jql		= jql + "inner join fetch phaseCompletion.currentStatusCompletion as statusCompletion ";
		jql		= jql + "inner join fetch statusCompletion.status as status ";
		jql		= jql + "inner join fetch phaseCompletion.phase as phase ";
		jql		= jql + "left outer join fetch entity.assignedTo as assignedTo ";
		jql		= jql + "left outer join fetch entity.solutionArchitect as solutionArchitect ";
		jql		= jql + "left outer join fetch entity.demandOwner as demandOwner ";
		jql		= jql + "left outer join fetch entity.originalRequestor as originalRequestor ";
		jql		= jql + "left outer join fetch workflow.release as release ";

		jql		= jql + "where phase.type  != :phaseType ";
		jql		= jql + "and ( release.releaseDate is null or release.releaseDate  >= current_date ) ";
		
		if( ap.getTeam() != null ){
			jql 	= jql + "and ";
			jql		= jql + "impact.team.id = " + ap.getTeam().getId() + " ";
		}
		
		if( ap.getPhase() != null ){
			jql 	= jql + "and ";
			jql		= jql + "phase.id = " + ap.getPhase().getId() + " ";
		}
		
		if( ap.getExcludeStatuses() != null && !ap.getExcludeStatuses().isEmpty() ){
			jql 	= jql + "and ";
			jql		= jql + "status.id not in ( " + ap.getExcludeStatusesIds() + " ) ";
		}
		if( ap.getAssignedTo() != null ){
			jql 	= jql + "and ";
			jql		= jql + "assignedTo.id = " + ap.getAssignedTo() + " ";
		}

		jql			= jql + "order by phaseCompletion.phase.index ";
		query 		= entityManager.createQuery( jql );
		query.setParameter( "phaseType", PhaseType.Done );
		list 		= query.getResultList();
		
		return ( List<IIdea> )list;
	}

	@Override
	public void insertIdeas( ReaderResponse response ){
		
		if( response.getUsers() == null || response.getIdeas() == null ){
			return;
		}
		for( IUser user : response.getUsers() ){
			if( user.getId() != null && !entityManager.contains( user ) ){
				user = entityManager.merge( user );
			}
			entityManager.persist( user );
		}
		entityManager.flush();
		for( IIdea idea : response.getIdeas() ){
			service.insert( idea );
			entityManager.flush();
		}
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	private void loadCache(){
		Query 							query;
		String							jql;
		Object[]						results	= null;
		MilestoneKeyCache				key;
		List<MilestoneEntity>			entities;
		
		jql 		= "from PhaseEntity entity inner join fetch entity.availableStatuses ";
		query		= entityManager.createQuery( jql );
		query.getResultList();
		jql 		= "from ReleaseEntryEntity entity inner join fetch entity.milestones ";
		query		= entityManager.createQuery( jql );
		query.getResultList();
		jql 		= "from MilestoneEntity ";
		query		= entityManager.createQuery( jql );
		entities	= query.getResultList();
		for( MilestoneEntity e : entities ){
			results	= new Object[3];
			results[0]	= e.getId();
			results[1]	= e.getName();
		
			results[2]	= e.getSmallDueDate();
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "S", e.getPhaseType() );
			milestoneCache.put( key, results );
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "XS", e.getPhaseType() );
			milestoneCache.put( key, results );
			results[2]	= e.getMediumDueDate();
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "M", e.getPhaseType() );
			milestoneCache.put( key, results );
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "L", e.getPhaseType() );
			milestoneCache.put( key, results );
			results[2]	= e.getLargeDueDate();
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "XL", e.getPhaseType() );
			milestoneCache.put( key, results );
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "XXL", e.getPhaseType() );
			milestoneCache.put( key, results );
			key	= new MilestoneKeyCache( e.getReleaseEntry().getId(), "XXXL", e.getPhaseType() );
			milestoneCache.put( key, results );
		}
	}
	
	@Override
	public void persist(IIdea entity) {
		super.persist( ( IdeaEntity )entity );
	}
	
	private boolean postFilter( IdeaAp ap, IdeaStatusResponse statusResponse){
		boolean							notFiltered		= true;
		
		if( ap.getMilestoneStatus() != null ){
			notFiltered	= ap.getMilestoneStatus().equals( statusResponse.getMilestoneStatus() );
		}
		if( ap.getReleaseStatus() != null ){
			notFiltered	= ap.getReleaseStatus().equals( statusResponse.getReleaseStatus() );
		}
		
		
		return notFiltered;
	}

	private Object[] queryStatusInfo( Long releaseId, String romAbbr, PhaseType phaseType ){
		MilestoneKeyCache				key;
		
		key	= new MilestoneKeyCache( releaseId, romAbbr, phaseType );
		
		return milestoneCache.get( key );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IComment> readCommentsForIdea( Long ideaId ){
		Query 							query;
		String							jql;
		List<IComment>					list;

		jql		= "select entity.workflow.comments from IdeaEntity entity ";
		jql		= jql + "where entity.id = " + ideaId;
		query	= entityManager.createQuery( jql );
		list 	= query.getResultList();

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IComment> readCommentsForTeamImpact(Long teamImpactId) {
		Query 							query;
		String							jql;
		List<IComment>					list;

		jql		= "select entity.workflow.comments from TeamImpactEntity entity ";
		jql		= jql + "where entity.id = " + teamImpactId;
		query	= entityManager.createQuery( jql );
		list 	= query.getResultList();

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ITeamImpact> readImpactsForIdea( Long ideaId ){
		Query 							query;
		String							jql;
		List<ITeamImpact>			list;

		jql		= "select entity.teamImpacts from IdeaEntity entity ";
		jql		= jql + "where entity.id = " + ideaId;
		query	= entityManager.createQuery( jql );
		list 	= query.getResultList();

		return list;
	}

	@Override
	public void update(IIdea entity) {
		super.update( ( IdeaEntity )entity );
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<IdeaPartialSearchResponse> searchIdeas( String value ){
		FullTextEntityManager 			ftem;
		QueryBuilder 					qb;
		org.apache.lucene.search.Query 	query;
		Query 							jquery;
		List<Object[]>					results;
		List<IdeaPartialSearchResponse>	list;
		IdeaPartialSearchResponse		partial;
		
		list	= new ArrayList<>();
		ftem	= Search.getFullTextEntityManager( entityManager );
		qb 		= ftem.getSearchFactory().buildQueryBuilder().forEntity( IdeaEntity.class ).get();
		query 	= qb.keyword()
				    .onFields( "ideaNumber", "projectNumber", "name", "description" )
				    .matching( value )
				    .createQuery();
		jquery	= ftem.createFullTextQuery( query )
					  .setProjection( "id", "ideaNumber", "projectNumber", "name" )
		;
		results	= jquery.getResultList();
		for( Object[] result : results ){
			System.out.println( "Array:" + result );
			partial	= new IdeaPartialSearchResponse();
			partial.setId( ( Long )result[0] );
			partial.setIdeaNumber( ( String )result[1] );
			partial.setPrjNumber( ( String )result[2] );
			partial.setName( ( String )result[3] );
			list.add( partial );
		}
		
		return list;
	}
}
