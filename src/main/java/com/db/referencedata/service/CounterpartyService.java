package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.repository.CounterpartyRepository;
import org.springframework.stereotype.Service;

@Service
public class CounterpartyService {

    CounterpartyRepository counterpartyRepository;


    public String save(Counterparty counterparty) {
        counterpartyRepository.save(counterparty);
        return "";
    }
}
