package com.fdc.boarding.releasetracker.usecase;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.fdc.boarding.core.log.LoggerProxy;

public abstract class AbstractUseCase {
	protected LoggerProxy				logger		= LoggerProxy.getLogger();
	
	@Inject
	protected Validator					validator;

	public AbstractUseCase(){
		super();
	}
	
	public boolean validate( Object entity, IServiceResponse response, Class<?>... groups ){
		boolean								validated	= true;
		ValidationFailure					vf;
		Set<ConstraintViolation<Object>>	violations;
	
		violations	= validator.validate( entity, groups );
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
