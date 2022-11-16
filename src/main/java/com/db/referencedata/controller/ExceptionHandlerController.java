package com.db.referencedata.controller;

import com.db.referencedata.entity.ExceptionLog;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.service.ExceptionSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private ExceptionSenderService exceptionSenderService;

    @ExceptionHandler(value = {ResourceNotFoundException.class, NullPointerException.class, ConstraintViolationException.class})
    public ResponseEntity<ExceptionLog> handleBadRequestException(RuntimeException exception) {
        ExceptionLog exceptionLog = convertExceptionToExceptionObject(exception);
        exceptionSenderService.sendException(exceptionLog);
        return new ResponseEntity<>(exceptionLog, HttpStatus.BAD_REQUEST);
    }

/*    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<HashMap<String, Object>> handleNoContentException(ListEmptyException exception) {

        //HashMap<String, Object> exceptionLog = convertExceptionToLog(exception);

        //sendExceptionLogToService(exceptionLog);

        return new ResponseEntity<>(exceptionLog, HttpStatus.NO_CONTENT);
    }*/


   /* public HashMap<String, Object> convertExceptionToLog(Exception exception){
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));

        HashMap<String,Object> exceptionLog = new HashMap<>();
        exceptionLog.put("name",exception.getClass());
        exceptionLog.put("type",exception.getClass());
        exceptionLog.put("message",exception.getMessage());
        exceptionLog.put("trace",errors.getBuffer());
        exceptionLog.put("cobDate",new Date());

        return exceptionLog;
    }*/

    public ExceptionLog convertExceptionToExceptionObject(Exception exception){
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));

        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setId(1);
        exceptionLog.setName(exception.getClass().toString());
        exceptionLog.setType(exception.getClass().toString());
        exceptionLog.setMessage(exception.getMessage());
        exceptionLog.setTrace("trace");
        exceptionLog.setCobDate(new Date());

        return exceptionLog;
    }

}