package com.fdc.boarding.core.service;

import com.fdc.boarding.core.persistence.AbstractEntity;

/**
 *
 */
public interface IEntityServiceListener<T extends AbstractEntity<?>>
{
    public <C extends AbstractEntity<?>> void onPostAddChild(
      T                                 parent
    , C                                 child
    );
    
    public void onPostDelete(
      T                                 busn
    );
    
    public void onPostInsert(
      T                                 busn
    );
  
    public <C extends AbstractEntity<?>> void onPostRemoveChild(
      T                                 parent
    , C                                 child
    );
   
    public void onPostUpdate(
      T                                 busn
    );
  
    public <C extends AbstractEntity<?>> boolean onPreAddChild(
      T                                 parent
    , C                                 child
    );

    
    public boolean onPreDelete(
      T                                 busn
    );
  
    public boolean onPreInsert(
      T                                 busn
    );
    
    public <C extends AbstractEntity<?>> boolean onPreRemoveChild(
      T                                 parent
    , C                                 child
    );
  
    public boolean onPreUpdate(
      T                                 busn
    );
}
