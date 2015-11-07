package com.fdc.boarding.releasetracker.gateway.idea;

import java.io.Serializable;
import java.util.List;

import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.gateway.excel.ReaderResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaAp;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaPartialSearchResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchResponse;

public interface IIdeaPersistenceGateway extends Serializable{

	public void delete(IIdea entity);
	public abstract IIdea determineById(Long id, String... initialize);
	public abstract List<IIdea> findAllIdeas();
	public abstract IdeaSearchResponse findIdeas(IdeaAp ap);
	public abstract void insertIdeas(ReaderResponse response);
	public void persist(IIdea entity);
	public void update(IIdea entity);
	public abstract List<IIdea> findIdeasByAp(IdeaAp ap);
	public abstract List<ITeamImpact> readImpactsForIdea(Long ideaId);
	public abstract List<IComment> readCommentsForIdea(Long ideaId);
	public abstract List<IComment> readCommentsForTeamImpact(Long teamImpactId);
	public abstract List<IdeaPartialSearchResponse> searchIdeas(String value);
}