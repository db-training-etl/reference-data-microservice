package com.db.referencedata.service;

import com.db.referencedata.entity.ExceptionLog;
import com.db.referencedata.repository.ExceptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExceptionSenderService{

    ExceptionRepository exceptionRepository;

    public void sendException(ExceptionLog exceptionLog){
        exceptionRepository.sendException(exceptionLog);
    }
}