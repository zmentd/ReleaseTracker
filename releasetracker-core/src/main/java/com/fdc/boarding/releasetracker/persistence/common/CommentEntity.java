package com.fdc.boarding.releasetracker.persistence.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.joda.time.DateTime;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.common.CommentType;
import com.fdc.boarding.releasetracker.domain.common.IComment;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.persistence.security.UserEntity;

@Entity
@Table( name = "RT_COMMON_COMMENT" )
public class CommentEntity extends AbstractAuditedEntity<Long> implements Serializable, IComment{
	private static final long 			serialVersionUID = 1L;

	@Id
	@TableGenerator( name="comment_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="COMMENT_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="comment_sequence")
	@Column( name = "ID" )
	private Long						id;
	
	@Lob
	@NotNull
	@Column( name = "COMMENT" )
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String						comment;

	@Type( type="com.fdc.boarding.core.persistence.type.UTCPersistentDateTime", parameters = {@Parameter( name="databaseZone", value="UTC" )} )
	@Column( name = "COMMENT_DATE" )
	private DateTime					commentDate;
	
	
	@Column( name = "COMMENT_TYPE" )
	@Type( type 		= "com.fdc.boarding.core.persistence.type.EnumType",
	   parameters	= {
		@Parameter( name = "enum_type", value = "com.fdc.boarding.releasetracker.domain.common.CommentType" )
	}
	)
	private CommentType					commentType;
	
	@Column( name = "JIRA_SRCD" )
	private Boolean						jiraSourced;
	
	@ManyToOne( targetEntity = UserEntity.class )
	@JoinColumn( name = "USER_ID" )	
	private IUser						user;

	public CommentEntity() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#getCommentDate()
	 */
	@Override
	public DateTime getCommentDate() {
		return commentDate;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#getJiraSourced()
	 */
	@Override
	public Boolean getJiraSourced() {
		return jiraSourced;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#getUniqueId()
	 */
	@Override
	public Long getUniqueId() {
		return getId();
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#getUser()
	 */
	@Override
	public IUser getUser() {
		return user;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#setComment(java.lang.String)
	 */
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#setCommentDate(org.joda.time.DateTime)
	 */
	@Override
	public void setCommentDate(DateTime commentDate) {
		this.commentDate = commentDate;
	}

	public void setCommentType(CommentType commentType) {
		this.commentType = commentType;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#setJiraSourced(java.lang.Boolean)
	 */
	@Override
	public void setJiraSourced(Boolean jiraSourced) {
		this.jiraSourced = jiraSourced;
	}

	/* (non-Javadoc)
	 * @see com.fdc.boarding.releasetracker.persistence.common.IComment#setUser(com.fdc.boarding.releasetracker.domain.security.IUser)
	 */
	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", comment=" + comment
				+ ", commentDate=" + commentDate + ", jiraSourced="
				+ jiraSourced + ", user=" + user + "]";
	}
	
}
