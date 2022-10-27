package com.db.referencedata.handler;

import com.db.referencedata.entity.ExceptionLog;
import com.db.referencedata.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionLog> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ExceptionLog message = new ExceptionLog(ex.getClass().toString(),
                ex.getMessage(),
                ex.getStackTrace().toString(),
                new Date());

        return new ResponseEntity<ExceptionLog>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionLog> globalExceptionHandler(Exception ex, WebRequest request) {
        ExceptionLog message = new ExceptionLog(ex.getClass().toString(),
                ex.getMessage(),
                ex.getStackTrace().toString(),
                new Date());

        return new ResponseEntity<ExceptionLog>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}