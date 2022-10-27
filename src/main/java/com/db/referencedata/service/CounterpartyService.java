package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.NoValuesFoundException;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CounterpartyService {

    CounterpartyRepository counterpartyRepository;
    ExceptionSenderService exceptionSenderService;

    public CounterpartyService(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    public Optional<Counterparty> findById(int id) {
        return counterpartyRepository.findById(id);
        //call function in exception service to send to Exception Api
    }

    public List<Counterparty> findAll() {
        if(counterpartyRepository.findAll().isEmpty())
            try {
                //call function in exception service to send to Exception Api
                NoValuesFoundException exception = new NoValuesFoundException("List of counterparties is empty");
                sendExceptionToService(exception);
                throw exception;
            } catch (NoValuesFoundException e) {
                throw new RuntimeException(e);
            }
        else{
            return counterpartyRepository.findAll();
        }
    }

    public Counterparty save(Counterparty counterparty){
        return counterpartyRepository.save(counterparty);
    }

    public List<Counterparty> saveAll(List<Counterparty> counterparties) throws NoValuesFoundException {
        if(counterpartyRepository.saveAll(counterparties).isEmpty()){
            throw new NoValuesFoundException("List of counterparties is empty");
        }
        return counterpartyRepository.saveAll(counterparties);
    }

    public void sendExceptionToService(Exception exception){
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        exceptionSenderService.sendException(exception.getClass().toString(),exception.getCause().toString(),exception.getMessage(),errors.toString(),Date.from(Instant.now()));
    }
}
