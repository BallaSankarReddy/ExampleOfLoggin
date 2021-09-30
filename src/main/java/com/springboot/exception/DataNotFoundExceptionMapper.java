package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseStatus
public class DataNotFoundExceptionMapper extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<?> getDataNotFoundException(ApplicationException ex, WebRequest request) throws Exception{
		//ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "documentation", HttpStatus.NOT_FOUND.value());
		
		if(ex instanceof DataNotFoundException) {
			
			return ResponseEntity.status(((DataNotFoundException) ex).getErrorMessage().getErrorCode()).body(((DataNotFoundException) ex).getErrorMessage());
		}else if(ex instanceof BusinessValidationException) {
		
			return ResponseEntity.status(((BusinessValidationException) ex).getErrorMessage().getErrorCode()).body(((BusinessValidationException) ex).getErrorMessage());
		}
		
		
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please try agin...., thanks");
		}
		
	}
}
