package com.db.referencedata.controller;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("counterparties")
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    public CounterpartyController(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @GetMapping("")
    public Iterable<Counterparty> findAll(){
        return counterpartyService.findAll();
    }

    @PutMapping("")
    public Counterparty save(@RequestBody Counterparty counterparty) {
        return counterpartyService.save(counterparty);
    }

    @PutMapping("bulk")
    public Iterable<Counterparty> saveAll(@RequestBody List<Counterparty> counterparties) {
        return counterpartyService.saveAll(counterparties);
    }
}
