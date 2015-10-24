package com.fdc.boarding.releasetracker.domain.common;

import org.joda.time.DateTime;

import com.fdc.boarding.core.domain.IAuditable;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.releasetracker.domain.security.IUser;

public interface IComment extends IAuditable, IEntity<Long> {
	public abstract Long getId();
	public abstract String getComment();
	public abstract DateTime getCommentDate();
	public abstract CommentType getCommentType();
	public abstract Boolean getJiraSourced();
	public abstract IUser getUser();
	public abstract void setId(Long id);
	public abstract void setComment(String comment);
	public abstract void setCommentDate(DateTime commentDate);
	public abstract void setCommentType( CommentType commentType );
	public abstract void setJiraSourced(Boolean jiraSourced);
	public abstract void setUser(IUser user);
}