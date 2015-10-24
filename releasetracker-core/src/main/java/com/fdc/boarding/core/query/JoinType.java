package com.fdc.boarding.core.query;

import java.util.HashMap;

/**
 *
 */
public enum JoinType
{
    FULL_JOIN   ( org.hibernate.sql.JoinType.FULL_JOIN   )
  , INNER_JOIN  ( org.hibernate.sql.JoinType.INNER_JOIN  )
  , LEFT_JOIN   ( org.hibernate.sql.JoinType.LEFT_OUTER_JOIN   )
  ; 
 
  private static HashMap<String , JoinType> map;

  static
  {
      map = new HashMap<String , JoinType>();

      for( JoinType x : JoinType.values() )
      {
          map.put( x.toString() , x );
      }
  }

  public static JoinType locate(
    String                            reqType
  )
  {
      return map.get( reqType );
  }


  //  ~ Instance fields ******************************************************
  private org.hibernate.sql.JoinType	specification;

  
  //  ~ Constructor **********************************************************
  JoinType(
    org.hibernate.sql.JoinType			specification
  )
  {
      this.specification    = specification;
  }


  //  ~ Methods **************************************************************

  public org.hibernate.sql.JoinType specification(
  )
  {
      return specification;
  }
}
