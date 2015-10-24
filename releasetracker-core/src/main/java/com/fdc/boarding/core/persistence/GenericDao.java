package com.fdc.boarding.core.persistence;


import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fdc.boarding.core.log.LoggerProxy;
/**
 * 
 * @param <ENTITY>
 * @param <ID>
 */
@Dependent
public class GenericDao<ENTITY extends Object, ID extends Serializable> {
    @Inject
    protected EntityManager entityManager;
    protected LoggerProxy	logger			= LoggerProxy.getLogger();
    
    protected Class<ENTITY>	entityClass	= null;

	public GenericDao(Class<ENTITY> cls) {

		this.entityClass = cls;
	}

	public void delete(ENTITY entity) {
		ENTITY merged;
		
		merged = entity;
		if( !entityManager.contains(merged) )
		{
			merged = entityManager.merge(merged);
		}
        entityManager.remove(merged);
	}

	public ENTITY find(ID id) {
		ENTITY entity;
		entity = entityManager.find(entityClass, id);

		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<ENTITY> findAll() {
		List<ENTITY> results;
		Query query = entityManager.createQuery("from " + entityClass.getSimpleName() + " entity");
		results = query.getResultList();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<ENTITY> findAll( String orderBy, boolean isAsc ) {
		String orderBySuffix = isAsc ? "ASC" : "DESC";
		List<ENTITY> results;

		String jql = String.format("from " + entityClass.getSimpleName() + " entity ORDER BY entity.%s %s", orderBy, orderBySuffix);
		Query query = entityManager.createQuery(jql);
   		results = query.getResultList();

		return results;
	}

	
	public void update(ENTITY entity) {
		ENTITY merged;

		merged = entity;
		if( !entityManager.contains(merged) )
		{
			merged = entityManager.merge(merged);
		}
		persist( merged );
	}

	public void persist(ENTITY entity) {
		entityManager.persist(entity);
	}

	public void persistAll(List<ENTITY> list) {
		for (ENTITY entity : list) {
			entityManager.persist(entity);
		}
	}
}
