package com.fdc.boarding.core.constraint;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fdc.boarding.core.constraint.annotation.EntityExists;
import com.fdc.boarding.core.domain.IEntity;
import com.fdc.boarding.core.service.IEntityReaderSvc;

public class EntityExistsValidator implements ConstraintValidator<EntityExists, Long> {

	@Inject
	private IEntityReaderSvc				reader;

    private Class<? extends IEntity<Long>>	entityClass;

    @SuppressWarnings("unchecked")
	public void initialize( EntityExists constraintAnnotation) {
        this.entityClass = ( Class<? extends IEntity<Long>> )constraintAnnotation.value();
    }

	@Override
	public boolean isValid( Long value, ConstraintValidatorContext context ) {
		boolean									validated	= true;
		IEntity<Long>							entity		= null;

		if( value != null ){
			try {
				entity	= reader.findByKey( entityClass, value );
			} catch (Exception e) {
			}
			validated	= entity != null;
		}
		
		return validated;
	}

}