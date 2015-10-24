package com.fdc.boarding.core.query;

import java.io.Serializable;


/**
 *
 */
public class AliasSpecification implements Serializable
{
    private static final long           serialVersionUID    = 1L;
    
    private String                      alias;
    private String                      associationPath;
    private JoinType                    joinType;
    
    /**
     * 
     */
    public AliasSpecification(
    )
    {
        super();
    }

    /**
     * @param alias
     * @param associationPath
     * @param joinType
     */
    public AliasSpecification( 
      String                            alias
    , String                            associationPath
    , JoinType                          joinType 
    )
    {
        super();
        this.alias              = alias;
        this.associationPath    = associationPath;
        this.joinType           = joinType;
    }

    /**
     * @return 
     *    The alias
     */
    public String getAlias(
    )
    {
        return alias;
    }

    /**
     * @return 
     *    The associationPath
     */
    public String getAssociationPath(
    )
    {
        return associationPath;
    }

    /**
     * @return 
     *    The joinType
     */
    public JoinType getJoinType(
    )
    {
        return joinType;
    }

    /**
     * @param alias 
     *    The alias to set
     */
    public void setAlias( 
      String                            alias 
    )
    {
        this.alias = alias;
    }

    /**
     * @param associationPath 
     *    The associationPath to set
     */
    public void setAssociationPath( 
      String                            associationPath 
    )
    {
        this.associationPath = associationPath;
    }

    /**
     * @param joinType 
     *    The joinType to set
     */
    public void setJoinType( 
      JoinType                          joinType 
    )
    {
        this.joinType = joinType;
    }
}
