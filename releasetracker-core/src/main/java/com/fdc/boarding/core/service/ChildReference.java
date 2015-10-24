package com.fdc.boarding.core.service;

import com.fdc.boarding.core.domain.IEntity;

/**
 *
 */
public class ChildReference
{

    public static ChildReference owned(
      IEntity<?>   				child
    , String                            attributeName 
    )
    {
        return new ChildReference( child, attributeName, true );
    }

    public static ChildReference owned(
      IEntity<?>   				child
    , String                            attributeName 
    , ChildReference...                 nestedChildren
    )
    {
        return new ChildReference( child, attributeName, true, nestedChildren );
    }
    
    public static ChildReference shared(
      IEntity<?>   				child
    , String                            attributeName 
    )
    {
        return new ChildReference( child, attributeName, false );
    }
   
    public static ChildReference shared(
      IEntity<?>   				child
    , String                            attributeName 
    , ChildReference...                 nestedChildren
    )
    {
        return new ChildReference( child, attributeName, false, nestedChildren );
    }
    
    private IEntity<?> 				child;
    private String                          attributeName;
    private boolean                         owned           = true;
    private ChildReference[]                nested;
    
    /**
     * 
     */
    public ChildReference()
    {
        nested  = new ChildReference[]{};
    }

    /**
     * @param child
     * @param attributeName
     */
    public ChildReference(
      IEntity<?>   				child
    , String                            attributeName 
    )
    {
        this();
        this.child          = child;
        this.attributeName  = attributeName;
    }

    /**
     * @param child
     * @param attributeName
     * @param owned
     */
    public ChildReference(
      IEntity<?>   				child
    , String                            attributeName
    , boolean                           owned 
    )
    {
        super();
        this.child          = child;
        this.attributeName  = attributeName;
        this.owned          = owned;
        this.nested         = new ChildReference[]{};
    }

    /**
     * @param child
     * @param attributeName
     * @param owned
     */
    public ChildReference(
      IEntity<?>  				child
    , String                            attributeName
    , boolean                           owned 
    , ChildReference...                 nestedChildren
    )
    {
        this( child, attributeName, owned );
        this.nested = nestedChildren;
    }

    /**
     * @param child
     * @param attributeName
     */
    public ChildReference(
      IEntity<?>   				child
    , String                            attributeName 
    , ChildReference...                 nestedChildren
    )
    {
        this( child, attributeName );
        this.nested = nestedChildren;
    }

    /**
     * @return the attributeName
     */
    public String getAttributeName()
    {
        return attributeName;
    }

    /**
     * @return the child
     */
    public IEntity<?> getChild()
    {
        return child;
    }

    /**
     * @return the nested
     */
    public ChildReference[] getNested()
    {
        return nested;
    }

    /**
     * @return the owned
     */
    public boolean isOwned()
    {
        return owned;
    }

    /**
     * @param attributeName the attributeName to set
     */
    public void setAttributeName( String attributeName )
    {
        this.attributeName = attributeName;
    }

    /**
     * @param child the child to set
     */
    public void setChild( IEntity<?> child )
    {
        this.child = child;
    }

    /**
     * @param nested the nested to set
     */
    public void setNested( ChildReference[] nested )
    {
        this.nested = nested;
    }

    /**
     * @param owned the owned to set
     */
    public void setOwned( boolean owned )
    {
        this.owned = owned;
    }

}
