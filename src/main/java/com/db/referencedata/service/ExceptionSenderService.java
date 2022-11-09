package com.db.referencedata.service;

import com.db.referencedata.repository.ExceptionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionSenderService{

    ExceptionRepository exceptionRepository;

   /* public ExceptionSenderService(ExceptionRepository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }*/

    public void sendException(HashMap exceptionLog) {
        exceptionRepository.sendException(exceptionLog);
    }
}