package com.db.referencedata.controller;

import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.service.ExceptionSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private final ExceptionSenderService exceptionSenderService;


    public ExceptionHandlerController(ExceptionSenderService exceptionSenderService) {
        this.exceptionSenderService = exceptionSenderService;
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> resourceNotFoundException(ResourceNotFoundException exception) {

        HashMap<String, Object> exceptionLog = convertExceptionToLog(exception);

        sendExceptionLogToService(exceptionLog);

        return new ResponseEntity<>(exceptionLog, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap<String, Object>> globalExceptionHandler(Exception exception) {

        HashMap<String, Object> exceptionLog = convertExceptionToLog(exception);

        sendExceptionLogToService(exceptionLog);

        return new ResponseEntity<>(exceptionLog, HttpStatus.INTERNAL_SERVER_ERROR);
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


    public HashMap<String, Object> convertExceptionToLog(Exception exception){
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));

        HashMap<String,Object> exceptionLog = new HashMap<>();
        exceptionLog.put("name",exception.getClass());
        exceptionLog.put("type",exception.getClass());
        exceptionLog.put("message",exception.getMessage());
        exceptionLog.put("trace",errors.getBuffer());
        exceptionLog.put("cobDate",new Date());

        return exceptionLog;
    }

    public void sendExceptionLogToService(HashMap<String, Object> exceptionLog){
        exceptionSenderService.sendException(exceptionLog);
    }

}