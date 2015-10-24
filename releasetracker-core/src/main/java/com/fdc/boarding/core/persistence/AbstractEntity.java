package com.fdc.boarding.core.persistence;

import com.fdc.boarding.core.domain.IEntity;


public abstract class AbstractEntity<T extends Object> implements IEntity<T>
{
    private static final long           serialVersionUID    = 1L;

    /**
     * 
     */
    public AbstractEntity(
    )
    {
        super();
    }
}
