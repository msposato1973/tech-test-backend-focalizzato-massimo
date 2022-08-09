package com.gocity.demo.exception;

import com.gocity.demo.schema.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/***
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomNotFoundException(CustomNotFoundException ex, WebRequest request) {
      ErrorResponse errorResponse  = new ErrorResponse("404", ex.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
	
	/***
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = CustomForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleCustomForbiddenException(CustomForbiddenException exception, WebRequest request) {
      ErrorResponse errorResponse  = new ErrorResponse("403", ExceptionsTemplate.FORBIDDEN);
      return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
	
	/***
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> exception(Exception ex,  WebRequest request) {
	  ErrorResponse errorDetails = new ErrorResponse("500", ExceptionsTemplate.ERROR);
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/***
	 * @paramMethodArgumentNotValidException ex, 
	 * @param HttpHeaders headers, 
     * @param HttpStatus status, 
     * @param WebRequest request
	 */
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {

		ErrorResponse errorDetails = new ErrorResponse("400", ExceptionsTemplate.BED_REQUEST);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
