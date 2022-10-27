package com.db.referencedata.controller;

import com.db.referencedata.entity.ExceptionLog;
import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.service.ExceptionSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private final ExceptionSenderService exceptionSenderService;

    public ExceptionHandlerController(ExceptionSenderService exceptionSenderService) {
        this.exceptionSenderService = exceptionSenderService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HashMap> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        HashMap<String,Object> requestBody = new HashMap<>();
        requestBody.put("name",ex.getClass());
        requestBody.put("type",ex.getClass());
        requestBody.put("message",ex.getMessage());
        requestBody.put("trace",errors.getBuffer());
        requestBody.put("cobDate",new Date());

        sendExceptionToService(ex);

        return new ResponseEntity<HashMap>(requestBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionLog> globalExceptionHandler(Exception ex, WebRequest request) {
        ExceptionLog message = new ExceptionLog(ex.getClass().toString(),
                ex.getMessage(),
                ex.getStackTrace().toString(),
                new Date());

        return new ResponseEntity<ExceptionLog>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /*
    * ConstraintViolationException triggers when Validator found null element in entity.
    * Instead of returning 500 server error, returns 400 BAD REQUEST
    */
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException exception,
                                                   ServletWebRequest webRequest) throws IOException {
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    public void sendExceptionToService(Exception exception){
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        exceptionSenderService.sendException(
                exception.getClass().toString(),
                exception.getClass().toString(),
                exception.getMessage(),
                errors.getBuffer().toString()
                ,Date.from(Instant.now()));
    }


}