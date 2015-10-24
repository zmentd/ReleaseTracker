package com.fdc.boarding.releasetracker.persistence.security;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.common.security.EncryptionProvider;
import com.fdc.boarding.releasetracker.domain.security.IAuthenticatedUser;
import com.fdc.boarding.releasetracker.domain.security.IUser;

@Entity
@Table( name = "RT_AUTH_USER",
		indexes = {
		@Index( columnList="USER_ID", unique = true )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "USER_ID" )
		}
)
public class AuthenticatedUserEntity extends AbstractAuditedEntity<Long> implements IAuthenticatedUser{
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator( name="auth_user_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="AUTH_USER_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="auth_user_sequence")
	@Column( name = "ID" )
	private Long		id;
	 
	@NotNull
	@Column( name = "USER_ID" )
	private String		userId;
	
	@NotNull
	@Convert( converter = EncryptionProvider.class )
	@Column( name = "PASSWORD" )
	private String		password;
	
	@NotNull
	@OneToOne( targetEntity = UserEntity.class, cascade={CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn( name="FK_USER_ID" )
	private IUser		user;
	
	public AuthenticatedUserEntity() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}
}
