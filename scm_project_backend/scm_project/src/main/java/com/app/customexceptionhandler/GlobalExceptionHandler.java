package com.app.customexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.requestresponse.ErrorResponse;

@ControllerAdvice // to tell the sc that this class have all global excep handling methods
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// to catch custom exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException excp) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(excp.getMessage()));
	}

	// for all other exceptions : add common exc handling method. equivalent to
	// catch-all
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		
		ErrorResponse resp = new ErrorResponse("Something went wrong : " + e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}
}
