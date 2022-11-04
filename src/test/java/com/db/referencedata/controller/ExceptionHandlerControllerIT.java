package com.db.referencedata.controller;


import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.service.ExceptionSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionHandlerControllerIT {


    private ExceptionSenderService exceptionSenderService;

    private ExceptionHandlerController exceptionHandlerController;


/*
    public void handleCustomException_ResourceNotFound_Test() {

        exceptionHandlerController.handleCustomException(new ResourceNotFoundException(""));

        new ResponseEntity<>(exceptionLog, exception.getHttpStatus());

        assertEquals(exceptionLog.get("name"), );
    }
*/
    /*
     * ConstraintViolationException triggers when Validator found null element in entity.
     * Instead of returning 500 server error, returns 400 BAD REQUEST
     */

    public void handleConstraintViolationExceptionTest()  {

    }


    public void globalExceptionHandler() {

    }
}
