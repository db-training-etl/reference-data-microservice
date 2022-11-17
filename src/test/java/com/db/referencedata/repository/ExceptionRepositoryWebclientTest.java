package com.db.referencedata.repository;

import com.db.referencedata.entity.ExceptionLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Date;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExceptionRepositoryWebclientTest {

    ExceptionRepositoryWebClient exceptionRepositoryWebclient;
    public MockWebServer mockExceptionMicroservice;

    ObjectMapper objectMapper;

    ExceptionLog expectedResponse;


    @BeforeAll
    public void setup() throws IOException{
        mockExceptionMicroservice = new MockWebServer();
        mockExceptionMicroservice.start();
    }

    @BeforeEach
    public void initialize(){
        exceptionRepositoryWebclient = new ExceptionRepositoryWebClient(mockExceptionMicroservice.url("/").url().toString());

        objectMapper = new ObjectMapper();

        expectedResponse =getExampleExceptionLog();
    }

    @AfterAll
    void tearDown() throws IOException {
        mockExceptionMicroservice.shutdown();
    }


    @Test
    public void initializeWebclientWithoutURLTest(){
        exceptionRepositoryWebclient = new ExceptionRepositoryWebClient();
    }

    @Test
    public void sendExceptionToExceptionMicroserviceTest() throws JsonProcessingException, InterruptedException {
        mockExceptionMicroservice.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(getExampleExceptionLog()))
        );

        exceptionRepositoryWebclient.sendException(getExampleExceptionLog());

    }

    private ExceptionLog getExampleExceptionLog() {
        ExceptionLog exceptionLog = new ExceptionLog(1, "exceptionName", "exceptionType","message","stackTrace",new Date());

        return exceptionLog;
    }

}
