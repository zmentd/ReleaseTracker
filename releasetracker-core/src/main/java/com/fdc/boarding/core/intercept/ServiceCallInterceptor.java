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
import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.fdc.boarding.core.intercept.annotation.ServiceCall;
import com.fdc.boarding.core.log.LoggerProxy;
import com.fdc.boarding.core.util.DateTimeUtil;
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
    	DateTime							start;
    	DateTime							end;
    	Duration							duration;
    	String								sduration;
    
    	//
    	// Log entry
    	//
    	start	= new DateTime();
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
			//
			// Failed validation, create a response and do not proceed.
			//
			returnType	= invocationContext.getMethod().getReturnType();
			if( IServiceResponse.class.isAssignableFrom( returnType ) ){
				result	= CDIContext.getInstance().getBean( returnType );
				( ( IServiceResponse )result ).setFailures( failures );
				( ( IServiceResponse )result ).setSuccess( false );
				( ( IServiceResponse )result ).setMessage( "Validation failed." );
			}
			else{
		    	end			= new DateTime();
		    	duration	= new Duration( start, end );
		    	sduration	= DateTimeUtil.getPeriodFormatter().print( duration.toPeriod() );
				logger.info( "Service call "
						   , service.getName()
						   , ".", invocationContext.getMethod().getName()
						   , " completed execution with error in "
						   , sduration
						   , ", request validation failed and the return type does not implement " 
						   , IServiceResponse.class.getName() 
						   , "." 
				);
				throw new ValidationException( "Validation failed for Service call " + service.getName() + "." + invocationContext.getMethod().getName() );
			}
		}
		else{
			//
			// Validation succeeded, proceed
			//
			result 	= invocationContext.proceed();
		}
		
		//
		// Log completion
		//
    	end			= new DateTime();
    	duration	= new Duration( start, end );
    	sduration	= DateTimeUtil.getPeriodFormatter().print( duration.toPeriod() );
		logger.info( "Service call "
				   , service.getName()
				   , ".", invocationContext.getMethod().getName()
				   , " completed execution in "
				   , sduration
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
