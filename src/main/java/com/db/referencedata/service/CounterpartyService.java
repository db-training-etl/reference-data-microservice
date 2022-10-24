package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.NoValuesFoundException;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CounterpartyService {

    CounterpartyRepository counterpartyRepository;

    public CounterpartyService(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    public Counterparty findById(int id) throws Exception {
        return counterpartyRepository.findById(id).orElseThrow(() -> new Exception("Counterparty not found: " + id));
    }

    public List<Counterparty> findAll() throws NoValuesFoundException{
        if(counterpartyRepository.findAll().isEmpty())
            throw new NoValuesFoundException("List of counterparties is empty");
        else{
            return counterpartyRepository.findAll();
        }
    }

    public Counterparty save(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty);
    }

    public List<Counterparty> saveAll(List<Counterparty> counterparties) {
        return counterpartyRepository.saveAll(counterparties);
    }
}
