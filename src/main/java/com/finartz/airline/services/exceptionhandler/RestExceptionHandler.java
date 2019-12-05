package com.finartz.airline.services.exceptionhandler;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiError> handleSqlIntegrityConstraintException(ConstraintViolationException ex) {
		String message = ex.getCause().getLocalizedMessage();
		String[] arr = message.split(":", 2);
		message = arr.length == 2 ? arr[0] : ex.getMessage();
		
		// got the exception string since there is no time for me to create new
		// exceptions and handle it here
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(message);
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}
}
