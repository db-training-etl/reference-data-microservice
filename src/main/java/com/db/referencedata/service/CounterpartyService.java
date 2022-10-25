package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.NoValuesFoundException;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public Counterparty findById(int id) throws Exception {
        return counterpartyRepository.findById(id).orElseThrow(() -> new Exception("Counterparty not found: " + id));
        //call function in exception service to send to Exception Api
    }

    public List<Counterparty> findAll() throws NoValuesFoundException{
        if(counterpartyRepository.findAll().isEmpty())
            throw new NoValuesFoundException("List of counterparties is empty");
            //call function in exception service to send to Exception Api
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

/*    public void sendExceptionToService(String name,String type, String message,String trace, Date cobDate){
        exceptionSenderService.sendException(name,type,message,trace,cobDate);
    }*/
}
