package com.fdc.boarding.releasetracker.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fdc.boarding.core.service.EntityReaderSvc;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;

public class TestEntityManager {

	private EntityReaderSvc				reader;
	
   @Before
    public void setUp() {
	   try {
		CDIContext.getInstance();
		   reader = CDIContext.getInstance().getBean( EntityReaderSvc.class );
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   	}
   
	@Test
	public void testLoadEm(){
		if( reader == null ){
			reader = CDIContext.getInstance().getBean( EntityReaderSvc.class );
		}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "primary" );
		EntityManager em = emf.createEntityManager();	
		
		Query query = em.createQuery(" from TeamEntity");
		List<?> results = query.getResultList();
		Assert.assertNotNull( results );
	}
}
