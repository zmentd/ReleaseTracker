package com.fdc.boarding.core.query;

import java.io.Serializable;

public class Conjunction extends AbstractCriteria implements Serializable
{

    public static Conjunction and(
      AbstractCriteria                  leftHandSide
    , AbstractCriteria                  rightHandSide
    )
    {
        return new Conjunction( TYPE.AND, leftHandSide, rightHandSide );
    }

    public static Conjunction or(
      AbstractCriteria                  leftHandSide
    , AbstractCriteria                  rightHandSide
    )
    {
        return new Conjunction( TYPE.OR, leftHandSide, rightHandSide );
    }

    public enum TYPE
    {
        AND
      , OR
    }

    private static final long           serialVersionUID    = 1L;

    private TYPE                        type;
    private AbstractCriteria            leftHandSide;
    private AbstractCriteria            rightHandSide;


    /**
     *
     */
    public Conjunction(
      TYPE                              type
    )
    {
        //
        // Default constructor.
        //
        super();
        this.type   = type;
    }

    /**
     *
     */
    public Conjunction(
      TYPE                              type
    , AbstractCriteria                  leftHandSide
    , AbstractCriteria                  rightHandSide
    )
    {
        //
        // Default constructor.
        //
        this( type );
        this.leftHandSide   = leftHandSide;
        this.rightHandSide  = rightHandSide;
    }

    /**
     * @return
     *    The type
     */
    public TYPE getType(
    )
    {
        return type;
    }

    /**
     * @param type
     *    The type to set
     */
    public void setType(
      TYPE                              type
    )
    {
        this.type = type;
    }

    /**
     * @return
     *    The leftHandSide
     */
    public AbstractCriteria getLeftHandSide(
    )
    {
        return leftHandSide;
    }

    /**
     * @return
     *    The rightHandSide
     */
    public AbstractCriteria getRightHandSide(
    )
    {
        return rightHandSide;
    }

    /**
     * @param leftHandSide
     *    The leftHandSide to set
     */
    public void setLeftHandSide(
      AbstractCriteria                  leftHandSide
    )
    {
        this.leftHandSide = leftHandSide;
    }

    /**
     * @param rightHandSide
     *    The rightHandSide to set
     */
    public void setRightHandSide(
      AbstractCriteria                  rightHandSide
    )
    {
        this.rightHandSide = rightHandSide;
    }

}
