package com.fdc.boarding.releasetracker.test;

import java.util.Properties;

import javax.naming.Context;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;

public abstract class AbstractPersistenceTest {
	protected Context  					ctx;

    @BeforeClass
    public static void init() {
    	Properties 						p;
    	 
    	p = System.getProperties();
    	p.put( "hibernate.hbm2ddl.auto", "create-drop");
    	p.put( "hibernate.show_sql", "true");
    	p.put( "hibernate.format_sql", "true");
    	p.put( "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    	p.put( "hibernate.connection.driver_class", "org.h2.Driver");
    	p.put( "hibernate.connection.url", "jdbc:h2:tcp://localhost/~/releasetracker;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1");
    	p.put( "hibernate.connection.username", "sa");
    	p.put( "hibernate.connection.password", "");
    	p.put( "hibernate.hbm2ddl.import_files", "RT_SECURITY_USER_PREFERENCE.sql," 
        									   + "RT_SECURITY_USER.sql," 
        									   + "RT_SECURITY_AUTH_USER.sql, "
        									   + "RT_TEAM.sql," 
        									   + "RT_RELEASE_ENTRY.sql, "
        									   + "RT_RELEASE_MILESTONE.sql,"
        									   + "RT_WORKFLOW_STATUS.sql,"
        									   + "RT_WORKFLOW_PHASE.sql,"
        									   + "RT_WORKFLOW_PHASE_AVAIL_STATUS.sql,"
        									   + "RT_WORKFLOW_PHASE_NEXT_PHASE.sql,"
        									   + "RT_WORKFLOW_PHASE_APRVL_TYPE.sql,"
        									   + "RT_UNIQUE_KEY.sql,"
        									   + "RT_IDEA.sql,"
        									   + "RT_TEAM_IMPACT.sql,"
        									   + "RT_WORKFLOW_PHASE_COMPLETION_STAUTUS.sql,"
        									   + "RT_COMMON_COMMENT.sql,"
        									   + "RT_WORKFLOW_COMMENT.sql"
    			);
        
        p.put( "hibernate.connection.CharSet", "utf-8");
        p.put( "hibernate.connection.characterEncoding", "utf-8");
        p.put( "hibernate.connection.useUnicode", "true");
        p.put( "hibernate.event.merge.entity_copy_observer", "allow");
        p.put( "org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.ValidityAuditStrategy");
        p.put( "org.hibernate.cache.CacheProvider", "org.hibernate.cache.EhCacheProvider");
        p.put( "jadira.usertype.autoRegisterUserTypes", "true");

    }

    @Before
    public void setUp() {
    	CDIContext.getInstance().getBean( EntityManagerFactory.class );
    	injectServices();
   }
   
    public void injectServices(){
    	
    }
    
   	@After
   	public void tearDown() {
       System.out.println("Closing the container" );
   	}

   	@AfterClass
   	public static void afterClass(){
   		EntityManagerFactory			emf;
   		
   		emf	= CDIContext.getInstance().getBean( EntityManagerFactory.class );
   		emf.close();
   	}
}
