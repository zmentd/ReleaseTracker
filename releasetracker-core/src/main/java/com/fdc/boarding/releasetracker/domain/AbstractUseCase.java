package com.fdc.boarding.releasetracker.domain;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.log.LoggerProxy;

public abstract class AbstractUseCase {
	protected LoggerProxy				logger		= LoggerProxy.getLogger();
	
	@Inject
	protected Validator					validator;

	public AbstractUseCase(){
		super();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean validate( IEntity entity, AbstractResponse response ){
		boolean								validated	= true;
		ValidationFailure					vf;
		Set<ConstraintViolation<IEntity>>	violations;
	
		violations	= validator.validate( entity );
		if( !violations.isEmpty() ){
			for( ConstraintViolation<?> cv : violations ){
				vf	= new ValidationFailure();	
				vf.setMessage( cv.getMessage() );
				vf.setPath( cv.getPropertyPath().toString() );
				vf.setValidator( cv.getConstraintDescriptor().getAnnotation().annotationType().getName() );
				response.addValidationFailure( vf );
			}
			response.setSuccess( false );
			response.setMessage( "Validation failed." );
			validated	= false;
		}

		return validated;
	}
}
