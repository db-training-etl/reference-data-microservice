package com.db.referencedata.webclient;

import com.db.referencedata.service.ExceptionSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebclientUtilityTest {

    ExceptionSenderService exceptionSenderService;

    public MockWebServer mockExceptionMicroservice;

    ObjectMapper objectMapper;

    HashMap<String, Object> expectedResponse;


    @BeforeEach
    public void setup(){
        mockExceptionMicroservice = new MockWebServer();

        exceptionSenderService = new ExceptionSenderService(mockExceptionMicroservice.url("/").url().toString());

        objectMapper = new ObjectMapper();

        expectedResponse = new HashMap<>();
        expectedResponse.put("exceptionName", "");
        expectedResponse.put("cobdate", "");
    }

    @AfterEach
    void tearDown() throws IOException {
        mockExceptionMicroservice.shutdown();
    }


    @Test
    public void sendExceptionToExceptionMicroserviceTest() throws JsonProcessingException {
        mockExceptionMicroservice.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse))
        );

        Exception exception = new Exception();

        HashMap<String, Object> actual = exceptionSenderService.sendException(exception.getClass().getName());
        System.out.println(expectedResponse.toString());

        assertEquals(expectedResponse.toString(), actual.toString());

    }

}
