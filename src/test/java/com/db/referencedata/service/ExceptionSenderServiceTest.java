package com.db.referencedata.service;

import com.db.referencedata.entity.ExceptionLog;
import com.db.referencedata.repository.ExceptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExceptionSenderServiceTest {

    @Mock
    ExceptionRepository exceptionRepository;

    ExceptionSenderService exceptionSenderService;


    @BeforeEach
    public void setup(){
        exceptionSenderService = new ExceptionSenderService(exceptionRepository);
    }


    @Test
    public void sendExceptionTest() throws Exception {

        exceptionSenderService.sendException(getExampleExceptionLog());

        verify(exceptionRepository,times(1)).sendException(any());
    }


    private ExceptionLog getExampleExceptionLog() {
        ExceptionLog exceptionLog = new ExceptionLog(1, "exceptionName", "exceptionType","message","stackTrace",new Date());

        return exceptionLog;
    }

}