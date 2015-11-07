package com.fdc.boarding.releasetracker.test.gateway.release;

import javax.inject.Inject;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.fdc.boarding.releasetracker.domain.common.Rom;
import com.fdc.boarding.releasetracker.domain.release.MilestoneType;
import com.fdc.boarding.releasetracker.gateway.release.IReleasePersistenceGateway;
import com.fdc.boarding.releasetracker.test.AbstractPersistenceTest;
import com.fdc.boarding.releasetracker.usecase.release.MilestoneByRom;

public class TestReleaseGateway extends AbstractPersistenceTest{

    @Inject
    private IReleasePersistenceGateway	gateway;
    
    @Test
    public void testFindMilestoneByRom(){
    	MilestoneByRom					result;
    	LocalDate						date;
    	
    	try {
			date	= new LocalDate().withMonthOfYear( 9 ).withDayOfMonth( 10 ).withYear( 2015 );
			result	= gateway.findMilestoneByTargetDate( date, Rom.Medium, MilestoneType.HLAD );
			Assert.assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
