package com.fdc.boarding.releasetracker.test.domain.team;

import java.util.Collection;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpected;
import com.fdc.boarding.releasetracker.test.domain.AbstractExpectedMap;

public class ExpectedTeamImpactIterator<P extends AbstractExpected> extends AbstractExpectedMap<P, ITeamImpact, ExpectedTeamImpact<ExpectedTeamImpactIterator<P>>> {

	public ExpectedTeamImpactIterator(IEntityReaderSvc reader, Collection<ITeamImpact> collection, P parent) {
		super(reader, collection, parent);
	}

	@Override
	protected ExpectedTeamImpact<ExpectedTeamImpactIterator<P>> createNext(ITeamImpact ent) {
		ExpectedTeamImpact<ExpectedTeamImpactIterator<P>>			epc;
		
		epc	= new ExpectedTeamImpact<ExpectedTeamImpactIterator<P>>( ent, reader, this );
		
		return epc;
	}

	public P parent(){
		return ( P )parent;
	}

	@Override
	protected String mapKey(ITeamImpact ent) {
		return ent.getTeam().getName();
	}
}
