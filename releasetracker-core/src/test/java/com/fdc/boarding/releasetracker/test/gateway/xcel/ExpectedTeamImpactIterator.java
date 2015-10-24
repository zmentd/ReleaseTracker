package com.fdc.boarding.releasetracker.test.gateway.xcel;

import java.util.Collection;

import com.fdc.boarding.core.service.IEntityReaderSvc;
import com.fdc.boarding.releasetracker.domain.team.ITeamImpact;

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
