package com.db.referencedata.service;

import com.db.referencedata.repository.WebclientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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


    public void sendException(HashMap exceptionLog) {
        webclientRepository.sendException(exceptionLog);
    }
}