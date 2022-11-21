package com.db.referencedata.controller;


import com.db.referencedata.entity.ExceptionLog;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.service.ExceptionSenderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExceptionHandlerControllerTest {

    @MockBean
    private ExceptionSenderService exceptionSenderService;

    private ExceptionHandlerController exceptionHandlerController;


    @BeforeAll
    public void setup(){
        exceptionSenderService = mock(ExceptionSenderService.class);
        exceptionHandlerController = new ExceptionHandlerController(exceptionSenderService);
    }



    @Test
    public void recieveExceptionAndRespondWithBadRequest_Test() throws Exception{
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("");

        ResponseEntity<ExceptionLog> actualResponse = exceptionHandlerController.handleBadRequestException(resourceNotFoundException);

        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
    }


    @Test
    public void recieveExceptionAndRespondWithNoContent_Test() throws Exception{
        ListEmptyException listEmptyException = new ListEmptyException("");

        ResponseEntity<ExceptionLog> actualResponse = exceptionHandlerController.handleNoContentException(listEmptyException);

        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
    }
}
