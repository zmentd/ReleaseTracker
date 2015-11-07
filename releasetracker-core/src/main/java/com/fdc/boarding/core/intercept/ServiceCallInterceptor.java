package com.fdc.boarding.core.intercept;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.jboss.weld.bean.proxy.ProxyObject;

import com.fdc.boarding.core.intercept.annotation.ServiceCall;
import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.releasetracker.common.cdi.CDIContext;
import com.fdc.boarding.releasetracker.usecase.IServiceResponse;
import com.fdc.boarding.releasetracker.usecase.ValidationFailure;

@Interceptor
@ServiceCall
public class ServiceCallInterceptor {

	@Inject
	protected Validator					validator;

	@AroundInvoke
	public Object aroundInvoke(
	  InvocationContext 				invocationContext
	) throws Exception {
		Object 								result;
		LoggerProxy							logger;
		Class<?>							service;
		boolean								validated	= true;
		ValidationFailure					vf;
		Set<ConstraintViolation<Object>>	violations;
		Set<ValidationFailure>				failures;
		Class<?>							returnType;
		ServiceCall							anno;
		
		anno	= invocationContext.getMethod().getAnnotation( ServiceCall.class ); 
		service = getService( invocationContext.getTarget() );
		logger	= LoggerProxy.getLogger( service );
		logger.info( "Service call "
				   , service.getName()
				   , ".", invocationContext.getMethod().getName()
				   , " begin execution." 
		);
		//
		// Validate the request.
		//
		failures	= new HashSet<>();
		for( Object p : invocationContext.getParameters() ){
			violations	= validator.validate( p, anno.validationGroups() );
			if( !violations.isEmpty() ){
				for( ConstraintViolation<?> cv : violations ){
					vf	= new ValidationFailure();	
					vf.setMessage( cv.getMessage() );
					vf.setPath( cv.getPropertyPath().toString() );
					vf.setValidator( cv.getConstraintDescriptor().getAnnotation().annotationType().getName() );
					failures.add( vf );
				}
				validated	= false;
			}
		}
		if( !validated ){
			returnType	= invocationContext.getMethod().getReturnType();
			if( IServiceResponse.class.isAssignableFrom( returnType ) ){
				result	= CDIContext.getInstance().getBean( returnType );
				( ( IServiceResponse )result ).setFailures( failures );
				( ( IServiceResponse )result ).setSuccess( false );
				( ( IServiceResponse )result ).setMessage( "Validation failed." );
			}
			else{
				logger.info( "Service call "
						   , service.getName()
						   , ".", invocationContext.getMethod().getName()
						   , " completed execution with error, request validation failed and the return type does not implement " + IServiceResponse.class.getName() + "." 
				);
				throw new ValidationException( "Validation failed for Service call " + service.getName() + "." + invocationContext.getMethod().getName() );
			}
		}
		else{
			result 	= invocationContext.proceed();
		}
		logger.info( "Service call "
				   , service.getName()
				   , ".", invocationContext.getMethod().getName()
				   , " completed execution." 
		);

		return result;
	}
	
	protected Class<?> getService( Object target ){
		if( target instanceof ProxyObject ){
			return ( ( ProxyObject )target ).getClass().getSuperclass();
		}
		else{
			return target.getClass();
		}
	}
}
