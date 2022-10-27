package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CounterpartyService {

    CounterpartyRepository counterpartyRepository;

    public CounterpartyService(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    public Optional<Counterparty> findById(int id) {
        return counterpartyRepository.findById(id);
        //call function in exception service to send to Exception Api
    }

    public List<Counterparty> findAll() throws ListEmptyException {
        if(counterpartyRepository.findAll().isEmpty())
            throw new ListEmptyException("List of counterparties is empty");
        else{
            return counterpartyRepository.findAll();
        }
    }

    public Counterparty save(Counterparty counterparty){
        return counterpartyRepository.save(counterparty);
    }

    public List<Counterparty> saveAll(List<Counterparty> counterparties) throws ListEmptyException {
        if(counterpartyRepository.saveAll(counterparties).isEmpty()){
            throw new ListEmptyException("List of counterparties is empty");
        }
        return counterpartyRepository.saveAll(counterparties);
    }


}
