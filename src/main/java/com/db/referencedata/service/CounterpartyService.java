package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
    }

    public Iterable<Counterparty> findAll() {
        return counterpartyRepository.findAll();
    }

    public Counterparty save(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty);
    }

    public Iterable<Counterparty> saveAll(List<Counterparty> counterparties) {
        return counterpartyRepository.saveAll(counterparties);
    }
}
