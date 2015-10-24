package com.fdc.boarding.core.util;

public class AssertUtils {

	private static Integer		ONE = Integer.valueOf( 1 );
	
	public static boolean isPositiveInteger( Integer i ){
		if( i == null )
		{
			return false;
		}
		int compareResult = i.compareTo(ONE);
		if( compareResult == -1 )
		{
			return false;
		}
		
		return true;
	}
}
