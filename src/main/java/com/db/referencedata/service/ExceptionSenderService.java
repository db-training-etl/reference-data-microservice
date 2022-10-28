package com.db.referencedata.service;

import com.db.referencedata.webclient.WebclientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionSenderService{

    WebclientRepository webclientRepository;


/*    public ExceptionSenderService() {

    }*/

/*
    public ExceptionSenderService(WebclientRepository webclientRepository) {
        this.webclientRepository = webclientRepository;
    }
*/


    public HashMap sendException(HashMap exceptionLog) {
        return webclientRepository.sendException(exceptionLog);
    }
}