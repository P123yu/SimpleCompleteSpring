package com.simple.Simple.exception;



import com.simple.Simple.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ResponseUtil<Object>> buildResponse(String message, int status, HttpStatus httpStatus) {

        ResponseUtil<Object> response = ResponseUtil.builder()
                .status(status)
                .success(false)
                .message(message)
                .data(null)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseUtil<Object>> handleNoSuchElement(NoSuchElementException ex) {
        return buildResponse("Element not found: " + ex.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseUtil<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildResponse("Bad input: " + ex.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseUtil<Object>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return buildResponse("Method not allowed: " + ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseUtil<Object>> handleInvalidJson(HttpMessageNotReadableException ex) {
        return buildResponse("Malformed JSON request", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ResponseUtil<Object>> handleRestClientException(RestClientResponseException  ex) {
        return buildResponse("Exception coming while requesting rest client", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseUtil<Object>> handleGenericException(Exception ex) {
        return buildResponse("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),  HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // custom exception
//
//    @ExceptionHandler(ResourceAlreadyExists.class)
//    public ResponseEntity<ResponseUtil<Object>> handleResourceAlreadyExists(ResourceAlreadyExists ex) {
//        return buildResponse(ex.getMessage(), HttpStatus.FOUND.value(),HttpStatus.FOUND);
//    }


    // handle spring validation

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseUtil<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildResponse("Validation failed: " + errorMsg, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

}