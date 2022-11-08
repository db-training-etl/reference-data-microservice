package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.exception.ResourceNotFoundException;
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

    public Counterparty findById(int id) {
        return counterpartyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Counterparty not found: " + id));
    }

    public List<Counterparty> findAll() throws ListEmptyException {
        List<Counterparty> counterparties = counterpartyRepository.findAll();

        if(counterparties.isEmpty())
            throw new ListEmptyException("List of counterparties is empty");
        else{
            return counterparties;
        }
    }

    public Counterparty save(Counterparty counterparty){
        return counterpartyRepository.save(counterparty);
    }

    public List<Counterparty> saveAll(List<Counterparty> counterparties) throws ListEmptyException {
        return counterpartyRepository.saveAll(counterparties);
    }


}
