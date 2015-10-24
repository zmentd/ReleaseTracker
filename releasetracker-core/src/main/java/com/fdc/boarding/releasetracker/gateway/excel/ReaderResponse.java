package com.fdc.boarding.releasetracker.gateway.excel;

import java.util.ArrayList;
import java.util.Collection;

import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.workflow.IPhaseCompletion;

public class ReaderResponse {
	private Collection<IUser>				users;
	private Collection<IIdea>				ideas;
	private Collection<IPhaseCompletion>	phaseCmplts;
	
	public ReaderResponse(){
		super();
		phaseCmplts	= new ArrayList<>();
	}

	public void addPhaseCmplt( IPhaseCompletion ent ){
		phaseCmplts.add( ent );	
	}
	public Collection<IIdea> getIdeas() {
		return ideas;
	}

	public Collection<IPhaseCompletion> getPhaseCmplts() {
		return phaseCmplts;
	}

	public Collection<IUser> getUsers() {
		return users;
	}

	public void setIdeas(Collection<IIdea> ideas) {
		this.ideas = ideas;
	}

	public void setPhaseCmplts(Collection<IPhaseCompletion> phaseCmplts) {
		this.phaseCmplts = phaseCmplts;
	}

	public void setUsers(Collection<IUser> users) {
		this.users = users;
	}
}
