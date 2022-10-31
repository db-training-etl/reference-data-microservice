package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.repository.CounterpartyRepository;
import com.db.referencedata.webclient.WebclientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExceptionSenderServiceTest {

    @Mock
    WebclientRepository webclientRepository;

    ExceptionSenderService exceptionSenderService;


    @BeforeEach
    public void setup(){
        exceptionSenderService = new ExceptionSenderService(webclientRepository);
    }


    @Test
    public void sendExceptionTest() throws Exception {

        exceptionSenderService.sendException(getExampleExceptionLog());

        verify(webclientRepository,times(1)).sendException(any());
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