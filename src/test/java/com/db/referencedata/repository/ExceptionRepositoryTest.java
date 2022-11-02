package com.db.referencedata.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExceptionRepositoryTest {

    ExceptionRepository exceptionRepository;
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
        exceptionRepository = new ExceptionRepository(mockExceptionMicroservice.url("/").url().toString());

        objectMapper = new ObjectMapper();

        expectedResponse = new HashMap<>(getExampleExceptionLog());
    }

    @AfterAll
    void tearDown() throws IOException {
        mockExceptionMicroservice.shutdown();
    }


    @Test
    public void initializeWebclientWithoutURLTest(){
        exceptionRepository = new ExceptionRepository();
    }

    @Test
    public void sendExceptionToExceptionMicroserviceTest() throws JsonProcessingException, InterruptedException {
        mockExceptionMicroservice.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(getExampleExceptionLog()))
        );

        HashMap exceptionInService = exceptionRepository.sendException(getExampleExceptionLog());

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
