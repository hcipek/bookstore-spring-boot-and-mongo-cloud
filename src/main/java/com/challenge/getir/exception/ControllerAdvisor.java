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
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request, HttpServletRequest httpRequest) {
        return getResponse(ex, httpRequest, HttpStatus.OK);
    }
    
    @ExceptionHandler(InsufficentStockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleBookNotFound(InsufficentStockException ex, HttpServletRequest httpRequest) {
        return getResponse(ex, httpRequest, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BadRequestException ex, HttpServletRequest httpRequest) {
        return getResponse(ex, httpRequest, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> getResponse(RuntimeException ex, HttpServletRequest req, HttpStatus status) {
        var errorResponse = new ErrorResponse();
        errorResponse.setCode(status.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setUrl(req.getRequestURI());
        return ResponseEntity.status(status).body(errorResponse);
    }
}