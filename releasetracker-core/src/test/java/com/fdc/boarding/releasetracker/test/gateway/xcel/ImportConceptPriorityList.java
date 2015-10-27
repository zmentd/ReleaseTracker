package com.fdc.boarding.releasetracker.test.gateway.xcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.gateway.excel.IdeaPriorityListReader;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;

public class ImportConceptPriorityList extends AbstractPersistenceTest {
	private static String				fullFile	= "src\\test\\resources\\Concept Priority List New.xlsm";

    private IdeaPriorityListReader		reader;
    
	@Override
	public void injectServices() {
		reader 				= CDIContext.getInstance().getBean( IdeaPriorityListReader.class );
	}

    @BeforeClass
    public static void init() {
    	Properties 						p;
    	
    	AbstractPersistenceTest.init();
	   	p = System.getProperties();
	   	p.put( "hibernate.hbm2ddl.auto", "create");
	   	p.put( "hibernate.show_sql", "false");
//	   	p.put( "hibernate.format_sql", "true");
//	   	p.put( "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//	   	p.put( "hibernate.connection.driver_class", "org.h2.Driver");
//	   	p.put( "hibernate.connection.url", "jdbc:h2:tcp://localhost/~/releasetracker;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1");
//	   	p.put( "hibernate.connection.username", "sa");
//	   	p.put( "hibernate.connection.password", "");
//	   	p.put( "hibernate.hbm2ddl.import_files", "RT_USER_PREFS.sql," 
//	       									   + "RT_USER.sql," 
//	       									   + "RT_AUTH_USER.sql, "
//	       									   + "RT_TEAM.sql," 
//	       									   + "RT_RELEASE_ENTRY.sql, "
//	       									   + "RT_MILESTONE.sql,"
//	       									   + "RT_COMMENT.sql,"
//	       									   + "RT_STATUS.sql,"
//	       									   + "RT_PHASE.sql,"
//	       									   + "RT_PHASE_AVAIL_STATUS.sql,"
//	       									   + "RT_PHASE_NEXT_PHASE.sql,"
//	       									   + "RT_PHASE_APRVL_TYPE.sql,"
//	       									   + "RT_UNIQUE_KEY.sql"
//	   			);
//	       
//	       p.put( "hibernate.connection.CharSet", "utf-8");
//	       p.put( "hibernate.connection.characterEncoding", "utf-8");
//	       p.put( "hibernate.connection.useUnicode", "true");
//	       p.put( "hibernate.event.merge.entity_copy_observer", "allow");
//	       p.put( "org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.ValidityAuditStrategy");
//	       p.put( "org.hibernate.cache.CacheProvider", "org.hibernate.cache.EhCacheProvider");
//	       p.put( "jadira.usertype.autoRegisterUserTypes", "true");
    }
    
    @Test
	public void readFullFile(){
		InputStream						stream;
		
		try {
			stream		= new FileInputStream( fullFile );
			System.out.println( "Path:" + new File( "." ).getAbsolutePath() );
			reader.importFile( stream );
			
		} catch( Exception e ) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
