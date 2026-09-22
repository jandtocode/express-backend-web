package com.jandtocode.express.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleResponseStatusException(ResponseStatusException exc) {
        // Extrae solo el mensaje sin el status
        String message = exc.getReason();
        HttpStatus status = HttpStatus.valueOf(exc.getStatusCode().value());
        return buildResponseEntity(message, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception exc) {
        return buildResponseEntity(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(String message, HttpStatus status) {
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(status.value());
        error.setMessage(message);
        return new ResponseEntity<>(error, status);
    }
}