package com.fdc.boarding.releasetracker.test;

import java.util.Properties;

import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class AbstractPersistenceTest {
	protected Context  					ctx;

    @BeforeClass
    public static void init() {
    	Properties 						p;
    	 
    	p = System.getProperties();
    	p.put( "hibernate.hbm2ddl.auto", "create-drop");
    	p.put( "hibernate.show_sql", "true");
    }

    @Before
    public void setUp() {
    	
    	injectServices();
   }
   
    public void injectServices(){
    	
    }
    
   	@After
   	public void tearDown() {
       System.out.println("Closing the container" );
   	}

}
