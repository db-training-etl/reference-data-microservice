package com.db.referencedata.webclient;

import com.db.referencedata.service.ExceptionSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebclientUtilityTest {

    WebclientUtility webclientUtility;

    public MockWebServer mockExceptionMicroservice;

    ObjectMapper objectMapper;

    HashMap<String, Object> expectedResponse;


    @BeforeAll
    public void setup() throws IOException{
        mockExceptionMicroservice = new MockWebServer();
        mockExceptionMicroservice.start();
    }

    @BeforeEach
    public void initialize(){
        webclientUtility = new WebclientUtility(mockExceptionMicroservice.url("/").url().toString());

        objectMapper = new ObjectMapper();

        expectedResponse = new HashMap<>();
        expectedResponse.put("exceptionName", "");
        expectedResponse.put("cobdate", "");
    }

    @AfterAll
    void tearDown() throws IOException {
        mockExceptionMicroservice.shutdown();
    }


    @Test
    public void sendExceptionToExceptionMicroserviceTest() throws JsonProcessingException {
        mockExceptionMicroservice.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse))
        );

        HashMap<String, Object> exceptionLog = getExampleExceptionLog();

        HashMap<String, Object> actual = exceptionSenderService.sendException(exceptionLog);

        assertEquals(expectedResponse.toString(), actual.toString());

    }

    private HashMap<String, Object> getExampleExceptionLog() {
        HashMap<String, Object> exceptionLog = new HashMap<>();
        exceptionLog.put("name","exceptionName");
        exceptionLog.put("type","exceptionType");
        exceptionLog.put("message","message");
        exceptionLog.put("trace", "stackTrace");
        exceptionLog.put("cobDate","date");

        return exceptionLog;
    }

}
