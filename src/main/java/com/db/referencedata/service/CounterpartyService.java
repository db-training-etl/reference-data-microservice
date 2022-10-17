package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterpartyService {

    CounterpartyRepository counterpartyRepository;

    public CounterpartyService(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    public Iterable<Counterparty> list() {
        return counterpartyRepository.findAll();
    }

    public Counterparty save(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty);
    }

    public Iterable<Counterparty> saveAll(List<Counterparty> counterparties) {
        return counterpartyRepository.saveAll(counterparties);
    }
}
