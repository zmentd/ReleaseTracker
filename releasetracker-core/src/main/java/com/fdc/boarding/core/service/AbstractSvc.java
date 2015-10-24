package com.fdc.boarding.core.service;

import java.io.Serializable;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.fdc.boarding.core.log.LoggerProxy;

/**
 *
 */
public abstract class AbstractSvc implements Serializable
{
    // ~ Static fields/initializers *******************************************
    private static final long           serialVersionUID = 1L;
    
    // ~ Instance fields ******************************************************
    @Inject
    private Validator					validator;
    
    /*
     * Logger object
     */
    protected LoggerProxy logger = LoggerProxy.getLogger( getClass() );

    // ~ Constructor **********************************************************
    /**
     * Default constructor
     */
    public AbstractSvc(
    )
    {
        /* do nothing */
    }
  
    /**
     * @param obj
     * @param methodName
     */
    protected <T extends Object> void validate(
      T                            		obj     
    , String                            methodName
    )
    {
    	Set<ConstraintViolation<T>> 	violations;
    	
    	violations	= validator.validate( obj );
    	if( !violations.isEmpty() ){
    		throw new ConstraintViolationException( "Validation failed " + methodName + ":", violations );
    	}
    }
}
