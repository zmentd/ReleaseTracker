package com.fdc.boarding.core.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import java.util.List;
import java.util.Map;

/**
 */
@ApplicationScoped
public class EntityManagerDelegate implements EntityManager{

	@Inject
	private IEntityManagerStore entityManagerStore;

	public void clear() {
		entityManagerStore.get().clear();
	}

	public void close() {
		entityManagerStore.get().close();
	}

	public boolean contains(Object entity) {
		return entityManagerStore.get().contains(entity);
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> arg0) {
		return entityManagerStore.get().createEntityGraph(arg0);
	}

	@Override
	public EntityGraph<?> createEntityGraph(String arg0) {
		return entityManagerStore.get().createEntityGraph(arg0);
	}

	public Query createNamedQuery(String name) {
		return entityManagerStore.get().createNamedQuery(name);
	}

	public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		return entityManagerStore.get().createNamedQuery(name, resultClass);
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String arg0) {
		return entityManagerStore.get().createNamedStoredProcedureQuery(arg0);
	}

	public Query createNativeQuery(String sqlString) {
		return entityManagerStore.get().createNativeQuery(sqlString);
	}

	@SuppressWarnings("rawtypes")
	public Query createNativeQuery(String sqlString, Class resultClass) {
		return entityManagerStore.get().createNativeQuery(sqlString,
				resultClass);
	}

	public Query createNativeQuery(String sqlString, String resultSetMapping) {
		return entityManagerStore.get().createNativeQuery(sqlString,
				resultSetMapping);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Query createQuery(CriteriaDelete arg0) {
		return entityManagerStore.get().createQuery(arg0);
	}

	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		return entityManagerStore.get().createQuery(criteriaQuery);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Query createQuery(CriteriaUpdate arg0) {
		return entityManagerStore.get().createQuery(arg0);
	}

	public Query createQuery(String qlString) {
		return entityManagerStore.get().createQuery(qlString);
	}

	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		return entityManagerStore.get().createQuery(qlString, resultClass);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0) {
		return createStoredProcedureQuery(arg0);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public StoredProcedureQuery createStoredProcedureQuery(String arg0,
			Class... arg1) {
		return entityManagerStore.get().createStoredProcedureQuery(arg0, arg1);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0,
			String... arg1) {
		return entityManagerStore.get().createStoredProcedureQuery(arg0, arg1);
	}

	public void detach(Object entity) {
		entityManagerStore.get().detach(entity);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return entityManagerStore.get().find(entityClass, primaryKey);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode) {
		return entityManagerStore.get().find(entityClass, primaryKey, lockMode);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode, Map<String, Object> properties) {
		return entityManagerStore.get().find(entityClass, primaryKey, lockMode,
				properties);
	}

	public <T> T find(Class<T> entityClass, Object primaryKey,
			Map<String, Object> properties) {
		return entityManagerStore.get().find(entityClass, primaryKey,
				properties);
	}

	public void flush() {
		entityManagerStore.get().flush();
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return entityManagerStore.get().getCriteriaBuilder();
	}

	public Object getDelegate() {
		return entityManagerStore.get().getDelegate();
	}

	@Override
	public EntityGraph<?> getEntityGraph(String arg0) {
		return entityManagerStore.get().getEntityGraph( arg0 );
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> arg0) {
		return entityManagerStore.get().getEntityGraphs(arg0);
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerStore.get().getEntityManagerFactory();
	}

	public FlushModeType getFlushMode() {
		return entityManagerStore.get().getFlushMode();
	}

	public LockModeType getLockMode(Object entity) {
		return entityManagerStore.get().getLockMode(entity);
	}

	public Metamodel getMetamodel() {
		return entityManagerStore.get().getMetamodel();
	}

	public Map<String, Object> getProperties() {
		return entityManagerStore.get().getProperties();
	}

	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		return entityManagerStore.get().getReference(entityClass, primaryKey);
	}

	public EntityTransaction getTransaction() {
		return entityManagerStore.get().getTransaction();
	}

	@Override
	public boolean isJoinedToTransaction() {
		return entityManagerStore.get().isJoinedToTransaction();
	}

	public boolean isOpen() {
		return entityManagerStore.get().isOpen();
	}

	public void joinTransaction() {
		entityManagerStore.get().joinTransaction();
	}

	public void lock(Object entity, LockModeType lockMode) {
		entityManagerStore.get().lock(entity, lockMode);
	}

	public void lock(Object entity, LockModeType lockMode,
			Map<String, Object> properties) {
		entityManagerStore.get().lock(entity, lockMode, properties);
	}

	public <T> T merge(T entity) {
		return entityManagerStore.get().merge(entity);
	}

	public void persist(Object entity) {
		entityManagerStore.get().persist(entity);
	}

	public void refresh(Object entity) {
		entityManagerStore.get().refresh(entity);
	}

	public void refresh(Object entity, LockModeType lockMode) {
		entityManagerStore.get().refresh(entity, lockMode);
	}

	public void refresh(Object entity, LockModeType lockMode,
			Map<String, Object> properties) {
		entityManagerStore.get().refresh(entity, lockMode, properties);
	}

	public void refresh(Object entity, Map<String, Object> properties) {
		entityManagerStore.get().refresh(entity, properties);
	}

	public void remove(Object entity) {
		entityManagerStore.get().remove(entity);
	}

	public void setFlushMode(FlushModeType flushMode) {
		entityManagerStore.get().setFlushMode(flushMode);
	}

	public void setProperty(String propertyName, Object value) {
		entityManagerStore.get().setProperty(propertyName, value);
	}

	public <T> T unwrap(Class<T> cls) {
		return entityManagerStore.get().unwrap(cls);
	}
}