package com.fdc.boarding.core.intercept.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import javax.enterprise.util.Nonbinding;

@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceCall {
	@Nonbinding
	Class<?>[] validationGroups() default {};
}
