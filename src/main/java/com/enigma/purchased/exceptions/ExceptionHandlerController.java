package com.enigma.purchased.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(com.enigma.purchased.exceptions.NotFoundException.class)
    public ResponseEntity<com.enigma.purchased.exceptions.CustomErrorResponse> customHandleNotFound(Exception e, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        com.enigma.purchased.exceptions.CustomErrorResponse error = new com.enigma.purchased.exceptions.CustomErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setPath(path);

        return new ResponseEntity<com.enigma.purchased.exceptions.CustomErrorResponse>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<com.enigma.purchased.exceptions.CustomErrorResponse> customHandleBadRequest(Exception e, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        com.enigma.purchased.exceptions.CustomErrorResponse error = new com.enigma.purchased.exceptions.CustomErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setPath(path);

        return new ResponseEntity<com.enigma.purchased.exceptions.CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

        //Get all errors
        List<String> message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("message", message);

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<com.enigma.purchased.exceptions.CustomErrorResponse> customHandleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        com.enigma.purchased.exceptions.CustomErrorResponse error = new com.enigma.purchased.exceptions.CustomErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setPath(path);

        return new ResponseEntity<com.enigma.purchased.exceptions.CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }


}
