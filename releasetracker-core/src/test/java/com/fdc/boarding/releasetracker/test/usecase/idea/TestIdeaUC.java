package com.fdc.boarding.releasetracker.test.usecase.idea;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.junit.Test;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaListPartialResponse;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaSearchRequest;
import com.fdc.boarding.releasetracker.usecase.idea.IdeaUC;

public class TestIdeaUC extends AbstractPersistenceTest{

    private IdeaUC									usecase;

	@Override
	public void injectServices() {
		EntityManager					entityManager;
		FullTextEntityManager 			fullTextEntityManager;
		
		usecase 		= CDIContext.getInstance().getBean( IdeaUC.class );
		entityManager	= CDIContext.getInstance().getBean( EntityManager.class );
		try {
			fullTextEntityManager = Search.getFullTextEntityManager( entityManager );
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testSearchWildcard(){
		IdeaSearchRequest 				request;
		IdeaListPartialResponse			response;
	
		request		= new IdeaSearchRequest();
		request.setSearchValue( "CON-04*" );
		response	= usecase.searchIdeas( request );
		Assert.assertNotNull( response );
		Assert.assertTrue( response.isSuccess() );
		Assert.assertNotNull( response.getList() );
		Assert.assertFalse( response.getList().isEmpty() );
	}

	@Test
	public void testSearchComment(){
		IdeaSearchRequest 				request;
		IdeaListPartialResponse			response;
	
		request		= new IdeaSearchRequest();
		request.setSearchValue( "snap" );
		response	= usecase.searchIdeas( request );
		Assert.assertNotNull( response );
		Assert.assertTrue( response.isSuccess() );
		Assert.assertNotNull( response.getList() );
		Assert.assertFalse( response.getList().isEmpty() );
	}
}
