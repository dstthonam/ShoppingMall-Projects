package com.thoany.dst.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.thoany.dst.member.exception.MemberNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(
	        MethodArgumentNotValidException e) {

	    ErrorCode errorCode = ErrorCode.INVALID_INPUT;

	    String message = e.getBindingResult()
	            .getFieldError()
	            .getDefaultMessage();

	    ErrorResponse response = ErrorResponse.builder()
	            .status(errorCode.getHttpStatus().value())
	            .code(errorCode.getCode())
	            .message(message)
	            .build();

	    return ResponseEntity
	            .status(errorCode.getHttpStatus())
	            .body(response);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(
	        MethodArgumentNotValidException e) {

	    ErrorCode errorCode = ErrorCode.INVALID_INPUT;

	    String message = e.getBindingResult()
	            .getFieldError()
	            .getDefaultMessage();

	    ErrorResponse response = ErrorResponse.builder()
	            .status(errorCode.getHttpStatus().value())
	            .code(errorCode.getCode())
	            .message(message)
	            .build();

	    return ResponseEntity
	            .status(errorCode.getHttpStatus())
	            .body(response);
	}
        
}