package com.fdc.boarding.releasetracker.persistence.security;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fdc.boarding.core.persistence.GenericDao;
import com.fdc.boarding.releasetracker.domain.security.IAuthenticatedUser;
import com.fdc.boarding.releasetracker.domain.security.IUser;
import com.fdc.boarding.releasetracker.domain.security.IUserPersistenceGateway;

public class UserPersistenceGateway extends GenericDao<UserEntity, Long> implements IUserPersistenceGateway{
	private static final long 			serialVersionUID = 1L;

	public UserPersistenceGateway() {
		super( UserEntity.class );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IUser> findAllUsers() {
		List<? extends IUser>		list;
		
		list	= findAll();
		
		return ( List<IUser> )list;
	}
	
	@Override
	public IAuthenticatedUser findByUserId( String userId ){
		AuthenticatedUserEntity			entity	= null;
		Query							query;
		
		try {
			query	= entityManager.createQuery( "from AuthenticatedUserEntity entity left join fetch entity.user as user left join fetch user.preferences as prefs where entity.userId = '" + userId + "'" );
			entity	= ( AuthenticatedUserEntity )query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return entity;
	}
	
	@Override
	public IAuthenticatedUser findAuthenticatedByEmail( String email ){
		AuthenticatedUserEntity			entity	= null;
		Query							query;
		
		try {
			query	= entityManager.createQuery( "from AuthenticatedUserEntity inner join user as user where user.email = '" + email + "'" );
			entity	= ( AuthenticatedUserEntity )query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return entity;
	}
	
	@Override
	public IUser findByEmail( String email ){
		UserEntity			entity	= null;
		Query							query;
		
		try {
			query	= entityManager.createQuery( "from UserEntity where email = '" + email + "'" );
			entity	= ( UserEntity )query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return entity;
	}

	@Override
	public void add(IUser entity) {
		super.persist( ( UserEntity )entity);
	}

	@Override
	public void add(IAuthenticatedUser entity) {
		entityManager.persist( ( AuthenticatedUserEntity )entity);
	}
}