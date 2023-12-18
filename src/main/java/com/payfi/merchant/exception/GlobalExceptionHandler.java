package com.payfi.merchant.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.payfi.merchant.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler( ResourceNotFoundException ex ){
		String message = ex.getMessage();
		ApiResponse  response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceMismatchException.class)
	public ResponseEntity<ApiResponse> resourceMismatchExceptionHandler( ResourceMismatchException ex ){
		String message = ex.getMessage();
		ApiResponse  response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String,String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach( (error) ->{
		   String field = ( (FieldError) error).getField();
		   String message = error.getDefaultMessage();
		   resp.put(field, message);
		});		
	    return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
}
