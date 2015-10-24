package com.fdc.boarding.core.query;

import java.io.Serializable;

public class Restriction extends AbstractCriteria implements Serializable
{
    private static final long           serialVersionUID = 1L;

    public static Restriction eq(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.EQ , attribute , value );
    }

    public static Restriction ilike(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.ILIKE , attribute , value + "%" );
    }

    public static Restriction ge(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.GE , attribute , value );
    }

    public static Restriction gt(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.GT , attribute , value );
    }

    public static Restriction le(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.LE , attribute , value );
    }

    public static Restriction like(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.LIKE , attribute , value + "%" );
    }

    public static Restriction lt(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.LT , attribute , value );
    }

    public static Restriction isNull(
      String                            attribute
    )
    {
        return new Restriction( Restriction.TYPE.NULL , attribute );
    }

    public static Restriction notEq(
      String                            attribute
    , Object                            value
    )
    {
        return new Restriction( Restriction.TYPE.NOT_EQ , attribute, value );
    }

    public static Restriction notNull(
      String                            attribute
    )
    {
        return new Restriction( Restriction.TYPE.NOT_NULL , attribute );
    }

    public enum TYPE
    {
        EQ
      , ILIKE
      , GE
      , GT
      , LE
      , LIKE
      , LT
      , NULL
      , NOT_EQ
      , NOT_NULL
    }

    private TYPE                        type;
    private String                      attribute;
    private Object                      value;

    /**
     *
     */
    public Restriction(
      TYPE                              type
    , String                            attribute
    )
    {
        //
        // Default constructor.
        //
        super();
        this.type       = type;
        this.attribute  = attribute;
    }

    public Restriction(
      TYPE                              type
    , String                            attribute
    , Object                            value
    )
    {
        super();
        this.type       = type;
        this.attribute  = attribute;
        this.value      = value;
    }

    /**
     * @return
     *    The type
     */
    public TYPE getType()
    {
        return type;
    }

    /**
     * @return
     *    The attribute
     */
    public String getAttribute()
    {
        return attribute;
    }

    /**
     * @return
     *    The value
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * @param type
     *    The type to set
     */
    public void setType( TYPE type )
    {
        this.type = type;
    }

    /**
     * @param attribute
     *    The attribute to set
     */
    public void setAttribute( String attribute )
    {
        this.attribute = attribute;
    }

    /**
     * @param value
     *    The value to set
     */
    public void setValue( Object value )
    {
        this.value = value;
    }

}
