package com.fdc.boarding.releasetracker.usecase.common.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.releasetracker.domain.common.CommentType;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.usecase.AbstractAuditedDto;
import com.fdc.boarding.releasetracker.usecase.security.dto.UserDto;

public class CommentDto extends AbstractAuditedDto implements Serializable {
	private static final long 			serialVersionUID = 1L;

	public static CommentDto from( IComment entity ){
		CommentDto						dto		= null;
		
		if( entity != null ){
			dto	= new CommentDto();
			dto.setComment( entity.getComment() );
			dto.setCommentDate( entity.getCommentDate() );
			dto.setCommentType( entity.getCommentType() );
			dto.setId( entity.getId() );
			dto.setJiraSourced( entity.getJiraSourced() );
			dto.setUser( UserDto.from( entity.getUser() ) );
			dto.setLastModifiedBy( entity.getLastModifiedBy() );
			dto.setLastModifiedDate( entity.getLastModifiedDate() );
		}
		
		return dto;
	}
	
	private Long						id;
	
	@NotNull
	private String						comment;
	
	@NotNull
	private DateTime					commentDate;
	
	@NotNull
	private CommentType					commentType;
	private Boolean						jiraSourced;

	@NotNull
	@EntityExists( IUser.class )
	private UserDto						user;

	public CommentDto(){
		super();
	}

	public String getComment() {
		return comment;
	}

	public DateTime getCommentDate() {
		return commentDate;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	public Long getId() {
		return id;
	}

	public Boolean getJiraSourced() {
		return jiraSourced;
	}

	public UserDto getUser() {
		return user;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCommentDate(DateTime commentDate) {
		this.commentDate = commentDate;
	}

	public void setCommentType(CommentType commentType) {
		this.commentType = commentType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJiraSourced(Boolean jiraSourced) {
		this.jiraSourced = jiraSourced;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}
