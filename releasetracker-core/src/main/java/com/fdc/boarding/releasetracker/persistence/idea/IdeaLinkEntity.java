package com.fdc.boarding.releasetracker.persistence.idea;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.idea.IIdea;
import com.fdc.boarding.releasetracker.domain.idea.IIdeaLink;
import com.fdc.boarding.releasetracker.domain.idea.IdeaLinkType;

@Entity
@Table( name = "RT_IDEA_LINK",
		indexes = {
		@Index( columnList="PARENT_IDEA_ID", unique = false ),
		@Index( columnList="CHILD_IDEA_ID", unique = false )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = {"PARENT_IDEA_ID", "CHILD_IDEA_ID" } )
		}
)
public class IdeaLinkEntity extends AbstractAuditedEntity<Long> implements Serializable, IIdeaLink{
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator( name="idealink_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="IDEA_LINK_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="idealink_sequence")
	@Column( name = "ID" )
	private Long						id;

	@NotNull
	@Column( name = "TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.idea.IdeaLinkType" )
	}
	)
	private IdeaLinkType				type;
	
	@NotNull
	@ManyToOne( targetEntity = IdeaEntity.class )
	@JoinColumn( name = "PARENT_IDEA_ID" )
	private IIdea					parentIdea;
	
	@NotNull
	@ManyToOne( targetEntity = IdeaEntity.class )
	@JoinColumn( name = "CHILD_IDEA_ID" )
	private IIdea					childIdea;
	
	public IdeaLinkEntity(){
		super();
	}

	@Override
	public IIdea getChildIdea() {
		return childIdea;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public IIdea getParentIdea() {
		return parentIdea;
	}

	@Override
	public IdeaLinkType getType() {
		return type;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public void setChildIdea(IIdea childIdea) {
		this.childIdea = childIdea;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setParentIdea(IIdea parentIdea) {
		this.parentIdea = parentIdea;
	}

	@Override
	public void setType(IdeaLinkType type) {
		this.type = type;
	}
}
