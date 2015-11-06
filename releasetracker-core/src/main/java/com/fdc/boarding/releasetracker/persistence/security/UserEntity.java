package com.fdc.boarding.releasetracker.persistence.security;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.fdc.boarding.core.persistence.AbstractAuditedEntity;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.security.IUserPreferences;


@Entity
@Cacheable( true )
@Table( name = "RT_SECURITY_USER",
		indexes = {
		@Index( columnList="FIRST_NAME", unique = false ),
		@Index( columnList="EMAIL", unique = true )
		},
		uniqueConstraints = {
		@UniqueConstraint( columnNames = "EMAIL" )
		}
)
public class UserEntity extends AbstractAuditedEntity<Long> implements Serializable, IUser{
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator( name="user_sequence" 
				   , table="RT_UNIQUE_KEY"
				   , pkColumnName="KEY_NAME" 
				   , valueColumnName="LAST_USED_KEY_PREFIX" 
				   , pkColumnValue="USER_KEY"
				   , allocationSize=100
				   )
	@GeneratedValue(strategy=GenerationType.TABLE , generator="user_sequence")
	@Column( name = "ID" )
	private Long				id;

//	@NotNull
	@Column( name = "FIRST_NAME" )
	private String				firstName;
	
//	@NotNull
	@Column( name = "LAST_NAME" )
	private String				lastName;
	
//	@NotNull
	@Column( name = "EMAIL" )
	private String				email;
	
	@Column( name = "JIRA_USER_ID" )
	private String				jiraUserName;
	
	@Column( name = "IMAGE" )
	private byte[]				imageBytes;
	
	@OneToOne( targetEntity = UserPreferencesEntity.class, fetch= FetchType.LAZY )
	@JoinColumn( name="USER_PREF_ID" )
	private IUserPreferences	preferences;
	
//	@OneToOne( targetEntity = AuthenticatedUserEntity.class, mappedBy = "user", fetch= FetchType.LAZY )
//	private IAuthenticatedUser	authUser;
	
	public UserEntity() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(imageBytes, other.imageBytes))
			return false;
		if (jiraUserName == null) {
			if (other.jiraUserName != null)
				return false;
		} else if (!jiraUserName.equals(other.jiraUserName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public byte[] getImageBytes() {
		return imageBytes;
	}

	@Override
	public String getJiraUserName() {
		return jiraUserName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public IUserPreferences getPreferences() {
		return preferences;
	}

	@Override
	public Long getUniqueId() {
		return getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(imageBytes);
		result = prime * result
				+ ((jiraUserName == null) ? 0 : jiraUserName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setImageBytes(byte[] image) {
		this.imageBytes = image;
	}

	@Override
	public void setJiraUserName(String jiraUserName) {
		this.jiraUserName = jiraUserName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public void setPreferences(IUserPreferences preferences) {
		this.preferences = preferences;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}

//	public String getFullUniqueName() {
//		String							value;
//		
//		if( authUser != null ){
//			value	= getFirstName() + " " + getLastName() + "  (" + authUser.getUserId() + ")";
//		}
//		else{
//			value	= getFirstName() + " " + getLastName() + "  (" + getId() + ")";
//		}
//		
//		return value;
//	}
//
//	public IAuthenticatedUser getAuthUser() {
//		return authUser;
//	}
//
//	public void setAuthUser(IAuthenticatedUser authUser) {
//		this.authUser = authUser;
//	}

}
