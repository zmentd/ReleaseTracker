package com.fdc.boarding.core.constraint.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import com.fdc.boarding.core.constraint.EntityExistsValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER } )
@Retention( RUNTIME )
@Documented
@Constraint( validatedBy = { EntityExistsValidator.class } )
public @interface EntityExists {
	
	String message() default "{com.fdc.boarding.core.constraint.EntityExists.message}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };

	Class<?> value();
	
	/**
	 * Defines several {@link NotNull} annotations on the same element.
	 *
	 * @see javax.validation.constraints.NotNull
	 */
	@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER } )
	@Retention(RUNTIME)
	@Documented
	@interface List {

		EntityExists[] value();
	}

}
