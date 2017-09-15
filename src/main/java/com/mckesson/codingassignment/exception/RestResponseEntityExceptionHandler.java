package com.mckesson.codingassignment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is exception handler class which catches and handles exceptions occurred in application
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * This method handles PasswordValidationException
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(PasswordValidationException.class)
    protected ResponseEntity<Object> handleException(PasswordValidationException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * This method handles when exception when request body is null
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, "request body can not be null", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
