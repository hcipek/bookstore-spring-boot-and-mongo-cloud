package com.challenge.getir.exception;

import com.challenge.getir.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request, HttpServletRequest httpRequest) {
        var errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.OK.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setUrl(httpRequest.getRequestURI());
        return ResponseEntity.ok(errorResponse);
    }
    
    @ExceptionHandler(InsufficentStockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleBookNotFound(InsufficentStockException ex, HttpServletRequest httpRequest) {
        var errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setUrl(httpRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
    }
}