package com.fdc.boarding.core.service;

import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.core.persistence.AbstractEntity;

/**
 *
 */
public abstract class AbstractEntityServiceAdapter<T extends AbstractEntity<?>> implements IEntityServiceListener<T>
{
    protected LoggerProxy               logger              = LoggerProxy.getLogger( getClass() );

    /**
     * 
     */
    public AbstractEntityServiceAdapter(
    )
    {
        super();
    }

    @Override
    public <C extends AbstractEntity<?>> void onPostAddChild(
      T                                 parent
    , C                                 child
    )
    {
        //
        // Derived override as needed.
        //
    }

    @Override
    public void onPostDelete( 
      T                                 busn 
    )
    {
        //
        // Derived override as needed.
        //
    }

    @Override
    public void onPostInsert( 
      T                                 busn 
    )
    {
        //
        // Derived override as needed.
        //
    }

    @Override
    public <C extends AbstractEntity<?>> void onPostRemoveChild(
      T                                 parent
    , C                                 child
    )
    {
        //
        // Derived override as needed.
        //
    }

    /* (non-Javadoc)
     * @see net.intersystems.iqs.service.IBusnObjServiceListener#onPostUpdate(com.bhmi.application.busnobj.AbstractPersistentBusnObj)
     */
    @Override
    public void onPostUpdate( 
      T                                 busn 
    )
    {
        //
        // Derived override as needed.
        //
    }

    @Override
    public <C extends AbstractEntity<?>> boolean onPreAddChild(
      T                                 parent
    , C                                 child
    )
    {
        //
        // Derived override as needed.
        //
        return true;
    }
    
    @Override
    public boolean onPreDelete( 
      T                                 busn 
    )
    {
        //
        // Derived override as needed.
        //
        return true;
    }
  
    @Override
    public boolean onPreInsert( 
      T                                 busn 
    )
    {
        //
        // Derived override as needed.
        //
        return true;
    }
    
    @Override
    public <C extends AbstractEntity<?>> boolean onPreRemoveChild(
      T                                 parent
    , C                                 child
    )
    {
        //
        // Derived override as needed.
        //
        return true;
    }
  
    @Override
    public boolean onPreUpdate(
      T                                 busn 
    )
    {
        //
        // Derived override as needed.
        //
        return true;
    }
}
