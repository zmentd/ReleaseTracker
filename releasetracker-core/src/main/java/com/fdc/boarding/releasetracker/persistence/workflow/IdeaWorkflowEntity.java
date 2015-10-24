package com.fdc.boarding.releasetracker.persistence.workflow;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.workflow.IIdeaWorkflow;
import com.fdc.boarding.releasetracker.persistence.idea.IdeaEntity;

@Entity
@Audited
@DiscriminatorValue( "IDEA" )
public class IdeaWorkflowEntity extends AbstractWorkflowEntity implements IIdeaWorkflow {
	private static final long 			serialVersionUID = 1L;

	@Audited( targetAuditMode = RelationTargetAuditMode.NOT_AUDITED )
	@OneToOne( targetEntity = IdeaEntity.class )
	private IIdea						idea;
	
	public IdeaWorkflowEntity(){
		super();
	}

	@Override
	public IIdea getIdea() {
		return idea;
	}

	@Override
	public void setIdea(IIdea idea) {
		this.idea = idea;
	}
}
