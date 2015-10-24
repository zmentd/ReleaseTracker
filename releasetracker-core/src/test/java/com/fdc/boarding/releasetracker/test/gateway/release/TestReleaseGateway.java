package com.fdc.boarding.releasetracker.test.gateway.release;

import javax.inject.Inject;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.release.IReleasePersistenceGateway;
import com.fdc.boarding.releasetracker.domain.release.MilestoneByRom;
import com.fdc.boarding.releasetracker.domain.workflow.PhaseType;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;

public class TestReleaseGateway extends AbstractPersistenceTest{

    @Inject
    private IReleasePersistenceGateway	gateway;
    
    @Test
    public void testFindMilestoneByRom(){
    	MilestoneByRom					result;
    	LocalDate						date;
    	
    	try {
			date	= new LocalDate().withMonthOfYear( 9 ).withDayOfMonth( 10 ).withYear( 2015 );
			result	= gateway.findMilestoneByTargetDate( date, Rom.Medium, PhaseType.HLAD );
			Assert.assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
