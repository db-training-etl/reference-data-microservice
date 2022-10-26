package com.db.referencedata.service;

import com.db.referencedata.webclient.WebclientRepository;

import java.util.Date;
import java.util.HashMap;

public class ExceptionSenderService{

    WebclientRepository webclientRepository;

    public ExceptionSenderService(WebclientRepository webclientRepository) {
        this.webclientRepository = webclientRepository;
    }

    public HashMap sendException(String exceptionName, String cause, String message, String trace, Date date) {
        return webclientRepository.sendException(exceptionName,cause,message,trace,date);
    }
}