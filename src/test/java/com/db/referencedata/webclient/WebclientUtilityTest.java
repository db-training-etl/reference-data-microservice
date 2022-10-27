package com.db.referencedata.webclient;

import com.db.referencedata.service.ExceptionSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

        expectedResponse = new HashMap<>(getExampleExceptionLog());
    }

    @AfterAll
    void tearDown() throws IOException {
        mockExceptionMicroservice.shutdown();
    }


    @Test
    public void sendExceptionToExceptionMicroserviceTest() throws JsonProcessingException, InterruptedException {
        mockExceptionMicroservice.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(getExampleExceptionLog()))
        );

        HashMap exceptionInService = webclientUtility.sendException(getExampleExceptionLog());

        assertEquals(expectedResponse, exceptionInService);

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
