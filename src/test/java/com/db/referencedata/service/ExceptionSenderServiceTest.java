package com.db.referencedata.service;

import com.db.referencedata.repository.WebclientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

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